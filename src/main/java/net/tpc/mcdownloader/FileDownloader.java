package net.tpc.mcdownloader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;

import org.apache.commons.io.IOUtils;

public class FileDownloader {

	public static ByteBuffer download(URL url) {
		ByteBuffer buffer = null;

		URLConnection connection = null;
		InputStream in = null;

		try {
			connection = url.openConnection();
			in = connection.getInputStream();

			int size = connection.getContentLength();
			if (size < 0) {
				return null;
			}
			buffer = ByteBuffer.allocateDirect(size);

			byte[] byteBuffer = new byte[1024];
			int read = 0;
			int position = 0;
			while ((read = in.read(byteBuffer)) != -1) {
				position += read;
				MinecraftDownloader.onProgressChanged(url.getFile(), ((float) position) / ((float) size));
				buffer.put(byteBuffer, 0, read);

				if (MinecraftDownloader.isAborted()) {
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(in);
			IOUtils.close(connection);
		}

		return buffer;
	}

	public static void download(URL download, File file) {
		ByteBuffer buffer = download(download);
		buffer.position(0);

		if (file.exists()) {
			file.delete();
		}

		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}

		FileOutputStream out = null;
		try {
			out = new FileOutputStream(file);

			byte[] byteBuffer = new byte[1024];
			int read;
			while (buffer.hasRemaining()) {
				read = buffer.remaining();
				read = read > byteBuffer.length ? byteBuffer.length : read;

				buffer.get(byteBuffer, 0, read);
				out.write(byteBuffer, 0, read);

				if (MinecraftDownloader.isAborted()) {
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(out);
		}
	}

	public static String downloadText(URL url) {
		URLConnection connection = null;
		InputStream in = null;
		StringBuilder buffer = new StringBuilder();

		try {
			connection = url.openConnection();
			in = connection.getInputStream();

			byte[] byteBuffer = new byte[1024];
			int read = 0;
			while ((read = in.read(byteBuffer)) != -1) {

				MinecraftDownloader.onProgressChanged(url.getFile(), -1.0F);
				buffer.append(new String(byteBuffer, 0, read));

				if (MinecraftDownloader.isAborted()) {
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(in);
			IOUtils.close(connection);
		}

		return buffer.toString();
	}
}
