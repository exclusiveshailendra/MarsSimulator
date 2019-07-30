package com.ove.commandhandler;

import com.ove.simulator.Canvas;
import com.ove.simulator.Placer;

public class PlaceHandler {

	private Placer placer;
	private Canvas canvas;
	
	public PlaceHandler(Placer placer, Canvas canvas){
		this.placer = placer;
		this.canvas = canvas;
	}
	
	/**
     * Run bunch of methods.
     * initlializeCanvasArray for initialing the array whenever a PLACE command enters.
     * updateCanvasArray with the code at the coordinates.
     */
	public void start() {
		
		canvas.initializeCanvasArray();
		canvas.updateCanvasArray(placer);
	}
}
