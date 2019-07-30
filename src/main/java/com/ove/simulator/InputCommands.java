package com.ove.simulator;

public class InputCommands {

	private String name;
	private int xAxis;
	private int yAxis;
	
	public String getName() {
		return name;
	}

	public int getxAxis() {
		return xAxis;
	}

	public int getyAxis() {
		return yAxis;
	}

	public InputCommands(String name, int xAxis, int yAxis) {
		this.name = name;
		this.xAxis = xAxis;
		this.yAxis = yAxis;
	}
	public InputCommands(String name) {
		this.name = name;
	}
	
	

}
