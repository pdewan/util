package util.annotations;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Keywords {
	String[] value() default { "ObjectEditor" };
}
