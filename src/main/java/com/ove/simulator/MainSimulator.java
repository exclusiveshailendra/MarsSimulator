package com.ove.simulator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.ove.commandhandler.BlockHandler;
import com.ove.commandhandler.ExplorerHandler;
import com.ove.commandhandler.PlaceHandler;
import com.ove.commandhandler.ReportHandler;

/**
 * A Main Class for Mars Simulator
 *
 */
public class MainSimulator {

	private static final Logger LOG = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	/**
	 * Main method for initiate execution.
	 * 
	 * @param args
	 * @throws IOException
	 * @throws IllegalAccessException
	 */
	public static void main(String[] args) throws IOException, IllegalAccessException {
		LOG.log(Level.INFO, "Mars Simulator Start");
		String fileName = null;
		MainSimulator sim = new MainSimulator();

		for (String arg : args) {
			if (fileName == null)
				fileName = arg;
		}
		if (fileName == null) {
			LOG.log(Level.WARNING, "File is not available");
			return;
		}

		List<String> contents = sim.readFile(new File(fileName));
		List<InputCommands> commandList = null;
		try {

			commandList = sim.commandMapper(contents);
		} catch (Exception e) {
			LOG.log(Level.SEVERE,
					"The command specified is invalid. Your command must be one of PLACE,BLOCK,EXPLORE or REPORT");
			return;
		}

		Canvas canvas = new Canvas();
		InputManager inputManager = new InputManager(commandList);
		inputManager.populateList();

		try {
			sim.validateQueueSize(inputManager);

		} catch (Exception e) {
			LOG.log(Level.SEVERE,
					"Execution failed: Queue size validation failed due to empty command list. Please provide valid commands: PLACE, BLOCK, EXPLORER, REPORT.",
					e);
			return;
		}

		// this validation is not handled while create command queue so no
		// further check needed.
		// inputManager.checkQueueHeadForPlace();

		try {
			sim.runSimulator(inputManager, canvas);
		} catch (Exception e) {
			LOG.log(Level.SEVERE, "Execution failed: unable to execute simulator.");
			return;
		}
	}

	/**
	 * Runs all the commands from the inputManager's queue.
	 *
	 * @param inputManager.
	 * @param canvas
	 *            canvas
	 *
	 */
	public void runSimulator(InputManager inputManager, Canvas canvas) {
		Queue<InputCommands> queue = getQueue(inputManager);
		LOG.log(Level.INFO, "Queue is initialized and head of the queue is PLACE");
		for (InputCommands inputCommands : queue) {
			if (CoordinateCheck.check(inputCommands)) {
				if (inputCommands.getName().equals("PLACE")) {
					LOG.log(Level.INFO, "PlaceHandler is initialized ");
					PlaceHandler placeHandler = new PlaceHandler(new Placer(new HolderType("PLACE", "p"),
							new Coordinates(inputCommands.getxAxis(), inputCommands.getyAxis())), canvas);
					placeHandler.start();
				}
				if (inputCommands.getName().equals("BLOCK")) {
					LOG.log(Level.INFO, "BlockHandler is initialized ");
					BlockHandler blockHandler = new BlockHandler(new Blocker(new HolderType("BLOCK", "b"),
							new Coordinates(inputCommands.getxAxis(), inputCommands.getyAxis())), canvas);
					blockHandler.start();
				}
				if (inputCommands.getName().equals("EXPLORER")) {
					LOG.log(Level.INFO, "ExplorerHandler is initialized ");
					ExplorerHandler explorerHandler = new ExplorerHandler(new Explorer(new HolderType("EXPLORE", "d"),
							new Coordinates(inputCommands.getxAxis(), inputCommands.getyAxis())), canvas);
					explorerHandler.start();
				}
				if (inputCommands.getName().equals("REPORT")) {
					LOG.log(Level.INFO, "ReportHandler is initialized ");
					ReportHandler reportHandler = new ReportHandler(canvas);
					reportHandler.start();
				}
			} else {
				LOG.log(Level.SEVERE, "Invalid Coordinates");
			}
		}
	}

	/**
	 * Retrieves the size of the queue and checks if it is greater than 0. If it
	 * is 0 or less then an {@link IllegalArgumentException} exception is
	 * thrown.
	 *
	 * @param inputManager.
	 *
	 * @return true or throws an exception.
	 */
	public boolean validateQueueSize(InputManager inputManager) {
		int size = inputManager.getQueue().size();
		if (size == 0) {
			LOG.log(Level.WARNING, "Command queue is empty");

			throw new IllegalArgumentException("Command queue is empty. Please initialize with some valid commands.");
		}
		return true;
	}

	/**
	 * Reads the data from the file and put that in a list of String
	 *
	 * @param file
	 *            contains the input.
	 *
	 * @return list of string which holds string input from a file.
	 * 
	 * @throws IOException
	 *             if the file is not present
	 */
	public List<String> readFile(final File file) {
		List<String> list = new ArrayList<String>();
		BufferedReader bufReader = null;
		try {
			bufReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			String line;

			while ((line = bufReader.readLine()) != null) {
				list.add(line);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				bufReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	/**
	 * Reads the commands from the contents list and convert the commands into
	 * the inputcommands format
	 *
	 * @param contents
	 *            which holds the commands as String.
	 *
	 * @return list of InputCommands containing the commands in required format.
	 * 
	 * @throws IllegalAccessException
	 *             if the input commands doesn't contain appropriate commands
	 */
	public List<InputCommands> commandMapper(List<String> contents) throws IllegalAccessException {
		List<InputCommands> commands = new ArrayList<InputCommands>();
		boolean isPlaceCmdAvailable = Boolean.FALSE;
		if (!contents.isEmpty()) {
			for (String line : contents) {
				line = line.trim();
				if (line.startsWith("PLACE") || line.startsWith("BLOCK") || line.startsWith("EXPLORE")) {

					isPlaceCmdAvailable = line.startsWith("PLACE");
					String[] rowCols = line.split("\\s++");
					String commandName = rowCols[0];
					String[] points = rowCols[1].split(",");
					commands.add(
							new InputCommands(commandName, Integer.parseInt(points[0]), Integer.parseInt(points[1])));
				} else if (line.startsWith("REPORT")) {
					commands.add(new InputCommands(line));
				} else {
					LOG.log(Level.WARNING, line + " -> Invalid command found, skipping it and proceeding futher.");
					/*
					 * throw new IllegalAccessException(
					 * "The command specified is invalid. Your command must be one of PLACE,BLOCK,EXPLORE or REPORT"
					 * );
					 */
				}
			}

			// Add default place command if not available already.
			if (!isPlaceCmdAvailable) {
				LOG.log(Level.WARNING, "place command not found, adding default COMMAND 'PLACE 0,0'");
				commands.add(0, new InputCommands("PLACE", 0, 0));
			}
		}
		return commands;
	}

	/**
	 * Retrieves the command queue from inputmanager.
	 *
	 * @param inputManager.
	 *
	 * @return command queue
	 */
	public Queue<InputCommands> getQueue(InputManager inputManager) {
		return inputManager.getQueue();
	}

}
