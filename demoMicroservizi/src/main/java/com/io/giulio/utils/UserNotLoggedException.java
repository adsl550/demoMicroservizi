package com.io.giulio.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class UserNotLoggedException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(UserNotLoggedException.class);
	public UserNotLoggedException() {
		// TODO Auto-generated constructor stub
	}

	public UserNotLoggedException(String message) {
		super(message);
	}

	public UserNotLoggedException(Throwable cause) {
		super(cause);
	}

	public UserNotLoggedException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public UserNotLoggedException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
