package util.annotations;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface BooleanAttributes {
	String[] names();

	boolean[] values();
}
