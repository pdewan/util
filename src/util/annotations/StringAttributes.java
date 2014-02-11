package util.annotations;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface StringAttributes {
	String[] names();

	String[] values();
}
