package com.ove.test;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.ove.simulator.InputCommands;
import com.ove.simulator.InputManager;

public class InputManagerTest {
	@Test(expected = IllegalArgumentException.class)
	public void testFirstElement_ForWrongInputs() {
		InputCommands command1 = new InputCommands("BLOCK", 0, 2);
		InputCommands command2 = new InputCommands("BLOCK", 0, 2);
		InputCommands command3 = new InputCommands("BLOCK", 0, 2);
		List<InputCommands> list = new ArrayList<InputCommands>();
		list.add(command1);
		list.add(command2);
		list.add(command3);
		InputManager manager = new InputManager(list);
		manager.populateList();

		manager.checkQueueHeadForPlace();

	}

	
	@Test(expected = IllegalArgumentException.class)
	public void testFirstElement_ForEmptyQueue() {
		
		List<InputCommands> list = new ArrayList<InputCommands>();
		InputManager manager = new InputManager(list);
		manager.populateList();

		manager.checkQueueHeadForPlace();

	}
	
	@Test
	public void testFirstElement_ForFirstValueAsPlaceInQueue() {
		
		InputCommands command1 = new InputCommands("PLACE", 0, 2);
		InputCommands command2 = new InputCommands("BLOCK", 1, 2);
		InputCommands command3 = new InputCommands("BLOCK", 2, 2);
		List<InputCommands> list = new ArrayList<InputCommands>();
		list.add(command1);
		list.add(command2);
		list.add(command3);
		InputManager manager = new InputManager(list);
		manager.populateList();

		assertTrue(manager.checkQueueHeadForPlace());

	}
	
	@Test
	public void testFirstElement_ForAnyValueAsPlaceInQueue() {
		
		InputCommands command1 = new InputCommands("BLOCK", 0, 2);
		InputCommands command2 = new InputCommands("BLOCK", 1, 2);
		InputCommands command3 = new InputCommands("BLOCK", 2, 2);
		InputCommands command4 = new InputCommands("PLACE", 2, 3);
		List<InputCommands> list = new ArrayList<InputCommands>();
		list.add(command1);
		list.add(command2);
		list.add(command3);
		list.add(command4);
		InputManager manager = new InputManager(list);
		manager.populateList();

		assertTrue(manager.checkQueueHeadForPlace());

	}
}
