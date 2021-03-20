package com.wsinservices.sysbanco.domain.exception;

public class NegocioException extends RuntimeException {
	
	private static final long serialVersionID = 1l;
	
	public NegocioException(String message) {
		super(message);
	}

}
