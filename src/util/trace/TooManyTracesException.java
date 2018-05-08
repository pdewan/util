package util.trace;

public class TooManyTracesException extends RuntimeException{
	public TooManyTracesException(String aMessage) {
		super(aMessage);
	}

}
