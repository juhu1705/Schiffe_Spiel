package schiffespiel.application.annotations;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import schiffespiel.application.enums.Priority;

/**
 * Methoden die mit @LoadingHandler gekenzeichnet sind werden ihrer
 * Priorit�tsstufe nach aufgerufen, noch bevor die initialisierung beginnt.
 * Hiermit k�nnen zu initialisierende Klassen in den Registries hinzugef�gt
 * werden.
 * 
 * @author Juhu1705
 *
 */
@Retention(RUNTIME)
@Target(METHOD)
public @interface LoadingHandler {
	Priority priority();
}
