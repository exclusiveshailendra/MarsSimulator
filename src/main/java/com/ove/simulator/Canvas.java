package com.ove.simulator;

/**
 * 
 * Canvas class to create 2d canvas that will further used to place blocks and
 * source and targets.
 *
 */
public class Canvas {

	static int canvasHeight = 5;
	static int canvasWidth = 5;
	private static String[][] canvasArray = new String[canvasHeight][canvasWidth];

	public void initializeCanvasArray() {
		for (int i = 0; i < canvasHeight; i++) {
			for (int j = 0; j < canvasWidth; j++) {
				canvasArray[i][j] = "*";
			}
		}
	}

	public String[][] getCanvasArray() {
		return canvasArray;
	}

	public void updateCanvasArray(CanvasArrayHolder canvas) {
		Coordinates coordinate = canvas.getCoordinates();
		HolderType holderType = canvas.getHolderType();
		if (canvasArray[coordinate.getxAxis()][coordinate.getyAxis()] == "*")
			canvasArray[coordinate.getxAxis()][coordinate.getyAxis()] = holderType.getCode();
	}

	public boolean indexIsAvailable(Coordinates coordinate) {
		String value = canvasArray[coordinate.getxAxis()][coordinate.getyAxis()];
		return value.equals("*");
	}

	public void updateCanvasArrayForExplorer() {

		for (int i = 0; i < canvasHeight; i++) {
			for (int j = 0; j < canvasWidth; j++) {
				if (canvasArray[i][j] == "p") {
					canvasArray[i][j] = "*";
				} else if (canvasArray[i][j] == "d")
					canvasArray[i][j] = "p";
			}
		}
	}
}
