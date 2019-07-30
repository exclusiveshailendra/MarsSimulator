package com.ove.simulator;

public class CoordinateCheck {

	private static int XMax = 5;
	private static int YMax = 5;
	
	public static boolean check(InputCommands command) {
		
		return command.getxAxis() < XMax && command.getyAxis() < YMax
                && command.getxAxis() >= 0 && command.getyAxis() >= 0;
	}
}
