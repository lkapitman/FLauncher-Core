package net.tpc.mcdownloader;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import net.tpc.mcdownloader.ProgressListener.StoppingReason;

import org.apache.commons.io.input.ReaderInputStream;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class MinecraftDownloader extends Thread {

	private static MinecraftDownloader			instance;

	private static ArrayList<ProgressListener>	progressChangedListener	= new ArrayList<ProgressListener>();

	private static File							outputPath				= new File(System.getenv("AppData"), ".minecraft/");

	private static boolean						downloading;
	private static boolean						abort;
	private static Throwable					lastException;

	private static Map<String, String>			mapFiles				= new HashMap<String, String>();
	static {
		// Launcher
		mapFiles.put("launcher/Minecraft.exe", "Minecraft.exe");
		mapFiles.put("launcher/minecraft.jar", "minecraft.jar");
		mapFiles.put("launcher/Minecraft.zip", "Minecraft.zip");

		// Minecraft
		mapFiles.put("minecraft.jar", "bin/minecraft.jar");

		// Minecraft Server
		mapFiles.put("launcher/minecraft_server.jar", "minecraft_server.jar");

		// Library
		mapFiles.put("lwjgl.jar", "bin/lwjgl.jar");
		mapFiles.put("jinput.jar", "bin/jinput.jar");
		mapFiles.put("lwjgl_util.jar", "bin/lwjgl_util.jar");
		mapFiles.put("jutils.jar", "bin/jutils.jar");

		// Natives
		mapFiles.put("linux_natives.jar", "bin/natives/linux_natives.jar");
		mapFiles.put("macosx_natives.jar", "bin/natives/macosx_natives.jar");
		mapFiles.put("windows_natives.jar", "bin/natives/windows_natives.jar");
		mapFiles.put("solaris_natives.jar", "bin/natives/solaris_natives.jar");
	}

	private static String						host					= "s3.amazonaws.com";
	private static String						downloadPath			= "MinecraftDownload";
	private static String						resourcePath			= "MinecraftResources";

	public static boolean isAborted() {
		return abort;
	}

	public static void addProgressListener(ProgressListener listener) {
		progressChangedListener.add(listener);
	}

	public static File getOutputPath() {
		return outputPath;
	}

	public static boolean isDownloading() {
		return downloading;
	}

	public static boolean isStopped() {
		return !downloading;
	}

	protected static void onProgressChanged(String value, float f) {
		for (ProgressListener listener : progressChangedListener) {
			if (listener != null) {
				listener.onProgressChanged(value, f);
			}
		}
	}

	private static void onStopped(StoppingReason reason) {
		downloading = false;
		for (ProgressListener listener : progressChangedListener) {
			if (listener != null) {
				listener.onStopped(reason);
			}
		}
	}

	public static void removeProgressListener(ProgressListener listener) {
		progressChangedListener.remove(listener);
	}

	public static void setOutputPath(File outputPath) {
		MinecraftDownloader.outputPath = outputPath;
	}

	public static void startDownloading() {
		if (downloading) {
			return;
		}
		downloading = true;
		abort = false;
		lastException = null;

		new MinecraftDownloader().start();
	}

	public static void stopDownloading() {
		if (downloading) {
			abort = true;
		}
	}

	public static MinecraftDownloader getInstance() {
		return instance;
	}

	public MinecraftDownloader() {
		instance = this;
	}

	public static Throwable getLastException() {
		return lastException;
	}

	@Override
	public void run() {
		for (Entry<String, String> file : mapFiles.entrySet()) {
			try {
				URL url = new URL("http://" + host + "/" + downloadPath + "/" + file.getKey());
				FileDownloader.download(url, new File(outputPath, file.getValue()));
			} catch (MalformedURLException | NullPointerException e) {
				lastException = e;
				onStopped(StoppingReason.Exception);
			}
			if (isAborted()) {
				onStopped(StoppingReason.Aborted);
				return;
			}
		}

		try {
			URL resourceUrl = new URL("http://" + host + "/" + resourcePath + "/");
			String buffer = FileDownloader.downloadText(resourceUrl);
			if (buffer == null) {
				lastException = new RuntimeException("Could not download the resource list!");
				onStopped(StoppingReason.Exception);
				return;
			}
			List<Resource> resources = null;
			try {
				resources = createResourceList(buffer);
			} catch (ParserConfigurationException | SAXException | IOException | NullPointerException e) {
				lastException = e;
				onStopped(StoppingReason.Exception);
				return;
			}
			for (Resource resource : resources) {
				if (resource == null) {
					continue;
				}

				if (resource.isDirectory()) {
					new File(outputPath, "resources/" + resource.getName()).mkdirs();
					continue;
				}
				URL url = new URL("http://" + host + "/" + resourcePath + "/" + resource.getName().replaceAll(" ", "%20"));
				FileDownloader.download(url, new File(outputPath, "resources/" + resource.getName()));

				if (isAborted()) {
					onStopped(StoppingReason.Aborted);
					return;
				}
			}
		} catch (MalformedURLException | NullPointerException e) {
			lastException = e;
			onStopped(StoppingReason.Exception);
			return;
		}

		onStopped(abort ? StoppingReason.Aborted : StoppingReason.Finished);
	}

	private List<Resource> createResourceList(String buffer) throws ParserConfigurationException, SAXException, IOException {
		List<Resource> resources = new ArrayList<Resource>();

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(new ReaderInputStream(new StringReader(buffer)));

		NodeList list = document.getElementsByTagName("ListBucketResult");
		if (list.getLength() != 1) {
			throw new RuntimeException("Could not parse resource list");
		}

		NodeList contentList = list.item(0).getChildNodes();
		for (int index = 0; index < contentList.getLength(); index++) {

			Node node = contentList.item(index);
			if (!node.getNodeName().equals("Contents")) {
				continue;
			}

			resources.add(createResourceElement(node));
		}

		return resources;
	}

	private Resource createResourceElement(Node node) {
		NodeList childs = node.getChildNodes();
		int size = 0;
		String name = null;
		for (int child = 0; child < childs.getLength(); child++) {
			Node property = childs.item(child);
			if (property.getNodeName().equals("Size")) {
				size = Integer.parseInt(property.getTextContent());
				continue;
			}
			if (property.getNodeName().equals("Key")) {
				name = property.getTextContent();
				continue;
			}
		}
		return new Resource(name, size);
	}

}
