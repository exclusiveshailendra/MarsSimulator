package com.ove.commandhandler;

import java.util.HashMap;
import java.util.Map;

import com.ove.simulator.Canvas;

public class ReportHandler {

	private Canvas canvas;
	
	public ReportHandler(Canvas canvas){
		this.canvas = canvas;
	}
	
	/**
     * Run bunch of methods.
     * 
     * Prints the coordinates of blocker and placer handler.
     */
	public void start() {
		Map<String, Object> outputMap =new HashMap<String, Object>();
		String ePosition = "";
		String bPosition="";
		
		String[][] array = canvas.getCanvasArray();
		
		for (int i = 0; i < array.length; i++) {
			for(int j =0; j< array[0].length; j++) {
				if(array[i][j]=="p")
				{ePosition+="("+i+","+j+")";}
				if(array[i][j]=="b")
				{bPosition+="("+i+","+j+")";}
			}
		}
		
		outputMap.put("e", ePosition);
		outputMap.put("b", bPosition);
		//System.out.println(outputMap);
		System.out.print("E:"+ePosition);
		System.out.println("B:"+bPosition);
	}
}
