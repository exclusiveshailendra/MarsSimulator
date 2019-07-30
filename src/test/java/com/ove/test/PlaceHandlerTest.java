package com.ove.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.ove.commandhandler.PlaceHandler;
import com.ove.simulator.Canvas;
import com.ove.simulator.Coordinates;
import com.ove.simulator.HolderType;
import com.ove.simulator.Placer;

public class PlaceHandlerTest {

	@Test(expected = NullPointerException.class)
	public void placeHandler_ForNullCoordinates() {

		Canvas canvas = new Canvas();

		PlaceHandler placeHandler = new PlaceHandler(new Placer(new HolderType("PLACE", "p"), null),
				canvas);
		placeHandler.start();
		canvas.getCanvasArray();

		
	}

	@Test(expected = NullPointerException.class)
	public void placeHandler_ForNullCanvas() {


		PlaceHandler placeHandler = new PlaceHandler(new Placer(new HolderType("PLACE", "p"), new Coordinates(2, 3)),
				null);
		placeHandler.start();

		
	}
	
	@Test
	public void placeHandler_ForCorrectInput() {

		Canvas canvas = new Canvas();

		PlaceHandler placeHandler = new PlaceHandler(new Placer(new HolderType("PLACE", "p"), new Coordinates(2, 3)),
				canvas);
		placeHandler.start();
		String[][] array = canvas.getCanvasArray();

		assertTrue(array[2][3].equals("p"));
	}

}
