package util.annotations;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface WebDocuments {
	String[] value() default { "http://www.cs.unc.edu/~dewan/comp114/f11/" };
}
