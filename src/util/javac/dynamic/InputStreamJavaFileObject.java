package util.javac.dynamic;

import java.io.InputStream;
import java.net.URI;

import javax.tools.SimpleJavaFileObject;

public class InputStreamJavaFileObject extends SimpleJavaFileObject {

    /**
    * CharSequence representing the source code to be compiled
    */
    private InputStream content;

    /**
    * This constructor will store the source code in the
    * internal "content" variable and register it as a
    * source code, using a URI containing the class full name
    *
    * @param className
    *            name of the public class in the source code
    * @param content
    *            source code to compile
    */
    public InputStreamJavaFileObject(String fileName,
        InputStream content) {
        super(URI.create("string:///" + fileName
            ), Kind.SOURCE);
        this.content = content;
    }

    /**
    * Answers the CharSequence to be compiled. It will give
    * the source code stored in variable "content"
    */
    @Override
    public InputStream openInputStream() {
        return content;
    }
}
