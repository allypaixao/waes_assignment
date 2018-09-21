package alessandra.assignment.waesAssignment.support;

import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Target(TYPE)
public @interface TestClass {
	String value() default "No Name";
}
