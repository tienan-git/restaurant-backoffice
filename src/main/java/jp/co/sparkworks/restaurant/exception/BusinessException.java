package jp.co.sparkworks.restaurant.exception;

/**
 * 業務例外
 */
public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BusinessException(String message) {
		super(message);
	}

}
