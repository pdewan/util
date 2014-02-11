package util.assertions;

public class AnAssertionFailedException extends RuntimeException {
	public AnAssertionFailedException() {
	};

	public AnAssertionFailedException(String initValue) {
		super(initValue);
	}

}