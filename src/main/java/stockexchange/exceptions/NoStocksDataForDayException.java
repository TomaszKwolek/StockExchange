package stockexchange.exceptions;

public class NoStocksDataForDayException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoStocksDataForDayException(String message) {
		super(message);
	}
}
