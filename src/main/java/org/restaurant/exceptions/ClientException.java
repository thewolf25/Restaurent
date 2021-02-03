package org.restaurant.exceptions;

import org.apache.logging.log4j.message.Message;

public class ClientException extends RuntimeException{

	public ClientException(String message) {
		super(message);
		
	}
	
	
}
