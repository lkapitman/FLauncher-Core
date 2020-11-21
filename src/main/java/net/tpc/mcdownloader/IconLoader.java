package net.tpc.mcdownloader;

import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class IconLoader {

	private static int[]	iconSizes	= new int[] { 16, 24, 32, 48, 64, 128, 256 };

	public static List<Image> loadIcons(String path, String name) {
		ArrayList<Image> icons = new ArrayList<Image>();
		for (int index = 0; index < iconSizes.length; index++) {
			try {
				icons.add(ImageIO.read(IconLoader.class
						.getResource(path + "/" + name + "_" + iconSizes[index] + "x" + iconSizes[index] + ".png")));
			} catch (IOException | IllegalArgumentException e) {
			}
		}
		return icons;
	}
}
