package com.lachesis.windranger.api.restful.exception;

public class RfException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RfException()
	{
		super();
	}
	
	public RfException(String msg)
	{
		super(msg);
	}
	
	public RfException(String format, Object... args) {
		
		super(String.format(format, args));
	}
	
}
