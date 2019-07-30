package com.ove.commandhandler;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.ove.simulator.Blocker;
import com.ove.simulator.Canvas;
import com.ove.simulator.Coordinates;

public class BlockHandler {

	private static final Logger LOG = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	private Blocker blocker;
	private Canvas canvas;
	
	public BlockHandler(Blocker blocker, Canvas canvas){
		this.blocker = blocker;
		this.canvas = canvas;
	}
	
	/**
     * Run bunch of methods.
     * checks if the index is available.
     * if yes updateCanvasArray with the code at the coordinates.
     * else log the message index is not available
     */
	public void start() {
		Coordinates coordinate = blocker.getCoordinates();
		if(canvas.indexIsAvailable(coordinate)) {
			canvas.updateCanvasArray(blocker);
		}else {
			LOG.log(Level.INFO,"Index is not available");
		}
		
		
	}
}
