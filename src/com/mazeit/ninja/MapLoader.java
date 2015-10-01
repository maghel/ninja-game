package com.mazeit.ninja;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MapLoader {
	
	private ArrayList<Tile> tilearray = new ArrayList<Tile>();

	public static Image tilegrassTop, tilegrassBot, tilegrassLeft,
		tilegrassRight, tiledirt;

	private StartingClass startingClass;
	
	public ArrayList<Tile> getTilearray() {
		return tilearray;
	}

	public MapLoader(StartingClass startingClass) {
		super();
		this.startingClass = startingClass;
	}

	public void loadMap(String filename) throws IOException {
		loadImages();
		ArrayList lines = new ArrayList();
		int width = 0;
		int height = 0;

		BufferedReader reader = new BufferedReader(new FileReader(filename));
		while (true) {
			String line = reader.readLine();
			// no more lines to read
			if (line == null) {
				reader.close();
				break;
			}

			if (!line.startsWith("!")) {
				lines.add(line);
				width = Math.max(width, line.length());

			}
		}
		height = lines.size();

		for (int j = 0; j < 12; j++) {
			String line = (String) lines.get(j);
			for (int i = 0; i < width; i++) {

				if (i < line.length()) {
					char ch = line.charAt(i);
					Tile t = new Tile(i, j, Character.getNumericValue(ch));
					tilearray.add(t);
				}
		}
		}

	}

	private void loadImages() {
		tiledirt = startingClass.loadImage("tiledirt.png");
		tilegrassTop = startingClass.loadImage("tilegrasstop.png");
		tilegrassBot = startingClass.loadImage("tilegrassbot.png");
		tilegrassLeft = startingClass.loadImage("tilegrassleft.png");
		tilegrassRight = startingClass.loadImage("tilegrassright.png");
	}

}
