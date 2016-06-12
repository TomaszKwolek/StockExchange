package stockexchange.exceptions;

public class WrongParameterException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WrongParameterException(String message) {
		super(message);
	}
}
