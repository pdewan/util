package util.annotations;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface PropertyNames {
	String[] value() default {};
}
