package com.ove.simulator;

import java.util.logging.Level;
import java.util.logging.Logger;

public class HolderType{

	private static final Logger LOG = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	private String name;
	private String code;
	
	public HolderType(String name, String code){
		LOG.log(Level.INFO,"HolderType with name= "+name+" and code= "+code);
		this.name = name;
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public String getCode() {
		return code;
	}

	
	
}
