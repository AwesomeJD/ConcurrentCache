package com.exercise.concurrentcache.exception;

public class NoEvictionStrategyConfiguredException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoEvictionStrategyConfiguredException(String message) {
		super(message);
	}

}
