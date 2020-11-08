package com.voting.jpa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author alekhya
 *
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class VoteException extends RuntimeException {

	public VoteException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VoteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public VoteException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public VoteException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public VoteException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
