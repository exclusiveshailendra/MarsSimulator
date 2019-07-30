package com.ove.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.ove.commandhandler.BlockHandler;
import com.ove.simulator.Blocker;
import com.ove.simulator.Canvas;
import com.ove.simulator.Coordinates;
import com.ove.simulator.HolderType;

public class BlockerHandlerTest {

	@Test(expected = NullPointerException.class)
	public void testShortestPath_ForNullCoordinates() {
		
		Canvas canvas = new Canvas();
		
		BlockHandler blockHandler = new BlockHandler(new Blocker(new HolderType("BLOCK","b"),null), canvas);
		blockHandler.start();
		
	}
	
	@Test(expected = NullPointerException.class)
	public void testShortestPath_ForNullCanvas() {
		
		
		BlockHandler blockHandler = new BlockHandler(new Blocker(new HolderType("BLOCK","b"),new Coordinates(0, 1)), null);
		blockHandler.start();
		
	}
	
	@Test
	public void placeHandler_ForCorrectInput() {

		Canvas canvas = new Canvas();

		canvas.initializeCanvasArray();
		BlockHandler blockHandler = new BlockHandler(new Blocker(new HolderType("BLOCK", "b"), new Coordinates(2, 3)),
				canvas);
		blockHandler.start();
		String[][] array = canvas.getCanvasArray();

		assertTrue(array[2][3].equals("b"));
	}
}
