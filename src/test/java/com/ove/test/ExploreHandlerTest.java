package com.ove.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.ove.commandhandler.ExplorerHandler;
import com.ove.simulator.Canvas;
import com.ove.simulator.Coordinates;
import com.ove.simulator.Explorer;
import com.ove.simulator.HolderType;

public class ExploreHandlerTest {

	
	
	@Test(expected = NullPointerException.class)
	public void testShortestPath_ForNullCoordinates() {
		
		Canvas canvas = new Canvas();
		
		ExplorerHandler exploreHandler = new ExplorerHandler(new Explorer(new HolderType("EXPLORE","d"),null), canvas);
		exploreHandler.start();
		
	}
	
	@Test(expected = NullPointerException.class)
	public void testShortestPath_ForNullCanvas() {

		
		ExplorerHandler exploreHandler = new ExplorerHandler(new Explorer(new HolderType("EXPLORE","d"),new Coordinates(0,0)), null);
		exploreHandler.start();
		
	}
	
	@Test
	public void testShortestPath_ForNoExplorer() {
		
		String[][] array = { 
				 { "*", "*", "b", "b", "*" },

				 { "*", "*", "*", "b", "*" },

				 { "*", "*", "*", "*", "*" },

				 { "*", "*", "*", "*", "*" },

				 { "*", "*", "*", "p", "*" } };
		
		Canvas canvas = new Canvas();
		
		ExplorerHandler exploreHandler = new ExplorerHandler(new Explorer(new HolderType("EXPLORE","d"),new Coordinates(0,0)), canvas);
		int distance = exploreHandler.findShortestPath(array);
		assertEquals(distance,-1);
	}
	
	@Test
	public void testShortestPath_ForCorrectInput() {
		
	String[][] array = {
				 { "*", "*", "b", "d", "*" },

				 { "*", "*", "*", "b", "*" },

				 { "*", "*", "*", "*", "*" },

				 { "*", "*", "*", "*", "*" },

				 { "*", "*", "*", "p", "*" } };
		
		Canvas canvas = new Canvas();
		
		ExplorerHandler exploreHandler = new ExplorerHandler(new Explorer(new HolderType("EXPLORE","d"),new Coordinates(0,0)), canvas);
		exploreHandler.markVisted(array);
		int distance = exploreHandler.findShortestPath(array);
		assertEquals(distance,6);
	}

}
