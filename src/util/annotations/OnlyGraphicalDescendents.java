package util.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.*;
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface OnlyGraphicalDescendents {
	boolean value();
}
