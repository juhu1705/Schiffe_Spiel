package schiffespiel.application.annotations;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(TYPE)
public @interface LoadingHandler {
	Priority priority();
	
	enum Priority {
		VERYLOW,
		LOW,
		MEDIUM,
		NORMAL,
		HIGH,
		VERYHIGH,
		EXTREMELYHIGH
	}
}
