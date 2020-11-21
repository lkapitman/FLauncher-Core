package net.tpc.mcdownloader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;


public class JarExtractor {

	private File								file					= null;
	private JarFile								jarFile					= null;
	private boolean								stop					= false;
	private ArrayList<ProgressListener>	progressChangedListener	= new ArrayList<ProgressListener>();

	public JarExtractor(File jarFile) {
		this.file = jarFile;
		try {
			this.jarFile = new JarFile(this.file);
		} catch (IOException e) {
		}
	}

	public void addProgressListener(ProgressListener listener) {
		this.progressChangedListener.add(listener);
	}

	public boolean extract(File outputDirectory) {
		if ((this.jarFile == null) || (outputDirectory == null)) {
			return false;
		}
		Enumeration<JarEntry> entries = this.jarFile.entries();

		FileOutputStream fout = null;
		InputStream in = null;
		JarEntry entry = null;
		long size = 0;
		byte[] buffer = new byte[1024];
		int read = 0;
		long numread = 0;
		while (entries.hasMoreElements()) {
			try {
				entry = entries.nextElement();
				this.onProgressChanged("Extracting " + entry.getName(), -1.0f);
				File out = new File(outputDirectory.getAbsolutePath(), entry.getName());
				if (!out.getParentFile().exists()) {
					out.getParentFile().mkdirs();
				}
				size = entry.getSize();
				if (out.exists() && (out.length() == size)) {
					continue;
				}
				fout = new FileOutputStream(out);
				in = this.jarFile.getInputStream(entry);
				numread = 0;
				while (!this.stop) {
					read = in.read(buffer);
					if (read == -1) {
						break;
					}
					numread += read;
					this.onProgressChanged("Extracting " + entry.getName(), (float) numread
							/ (float) size);
					fout.write(buffer, 0, read);
				}
			} catch (IOException e) {
			} finally {
				try {
					fout.close();
				} catch (IOException | NullPointerException e) {
				}
			}
		}
		return true;
	}

	public int getSize() {
		return this.jarFile.size();
	}

	private void onProgressChanged(String value, float f) {
		for (ProgressListener listener : this.progressChangedListener) {
			if (listener != null) {
				listener.onProgressChanged(value, f);
			}
		}
	}

	public void removeProgressListener(ProgressListener listener) {
		this.progressChangedListener.remove(listener);
	}

	public void stopExtracting() {
		this.stop = true;
	}
}
