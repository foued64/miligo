package fr.miligo.exceptions;

@SuppressWarnings("serial")
public class MiligoException extends Exception {

	public MiligoException() {
		super();
	}

	public MiligoException(Throwable cause) {
		super(cause);
	}

	public MiligoException(String message) {
		super(message);
	}

	public MiligoException(String message, Throwable cause) {
		super(message, cause);
	}

}
