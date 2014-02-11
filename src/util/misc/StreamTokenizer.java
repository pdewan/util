package util.misc;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

public class StreamTokenizer extends java.io.StreamTokenizer {
	public StreamTokenizer(InputStream is) {
		super(is);
	}

	public StreamTokenizer(Reader r) {
		super(r);
	}

	public String nextWord() {
		int nextToken;
		try {

			for (;;) {
				nextToken = this.nextToken();
				if (ttype == StreamTokenizer.TT_WORD)
					return (sval);
				System.out.println("Please enter a word");
			}
		} catch (IOException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			return (null);
		}
	}

	public String nextWordOrString() {
		int nextToken;
		try {
			for (;;) {
				nextToken = super.nextToken();
				if ((ttype == StreamTokenizer.TT_WORD) || (ttype == '"'))
					return (sval);
				System.out.println("Please enter a word or string");
			}
		} catch (IOException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			return (null);
		}
	}

	public double nextNumber() {
		int nextToken;
		try {
			for (;;) {
				nextToken = this.nextToken();
				if (ttype == StreamTokenizer.TT_NUMBER)
					return (nval);
				System.out.println("Please enter a number");
			}
		} catch (IOException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			return (0);
		}
	}

}
