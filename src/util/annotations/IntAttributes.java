package util.annotations;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface IntAttributes {
	String[] names();

	int[] values();
}
