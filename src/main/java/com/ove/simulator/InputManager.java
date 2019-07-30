package com.ove.simulator;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;



public class InputManager {

	private static final Logger LOG = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	private Queue<InputCommands> commandQueue = new LinkedList<InputCommands>();
	private List<InputCommands> commandList;
	
	public InputManager(List<InputCommands> commandList) {
		
		this.commandList = commandList;
	}
	

    /**
     * populateList populate the queue with the commands
     *
     */
	public void populateList() {
		LOG.log(Level.INFO,"Command list has been populated");
		commandQueue.addAll(commandList);
	}
	
	
	 /**
     * Check the head of the queue for PLACE 
     *
     *
     * @return true or throws an IllegalArgumentException.
     */
	public boolean checkQueueHeadForPlace() {
		
		while(!commandQueue.isEmpty())
		{if (!commandQueue.peek().getName().equals("PLACE")) {
			LOG.log(Level.WARNING,"command is not PLACE, removing the command");
			commandQueue.poll();
		}else {return true;}
		}
		throw new IllegalArgumentException("No Place Command is found");
	}
	
	
	
	/**
     * Returns the command queue 
     *
     *
     * @return queue.
     */
	public Queue<InputCommands> getQueue() {
        return this.commandQueue;
    }
}
