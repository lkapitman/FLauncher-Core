package net.tpc.mcdownloader;

public class Resource {

	private int		size;
	private String	name;

	public Resource(String name, int size) {
		this.name = name;
		this.size = size;
	}

	public String getName() {
		return name;
	}

	public int getSize() {
		return size;
	}

	public boolean isDirectory() {
		return size == 0;
	}
}
