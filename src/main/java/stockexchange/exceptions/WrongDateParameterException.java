package stockexchange.exceptions;

public class WrongDateParameterException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WrongDateParameterException(String message) {
		super(message);
	}
}
