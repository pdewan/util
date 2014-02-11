package util.annotations;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface EditablePropertyNames {
	String[] value() default {};
}
