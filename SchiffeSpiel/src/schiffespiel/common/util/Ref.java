package schiffespiel.common.util;

import java.util.Random;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Enth�lt Referenzvariablen
 */
public final class Ref {
	
	private Ref() {}
	
	/**
	 * Standard Klassen-ID
	 */
	public static final long UNIVERSAL_SERIAL_VERSION_UID = -1208340591720468638L;
	
	/**
	 * Universeller Zufallsgenerator
	 */
	public static final Random randGen = new Random();
	
	/**
	 * Universeller Logger. Diesen bitte statt System.out.println() benutzen.
	 */
	public static final Logger LOGGER;
	static {
		LOGGER = Logger.getLogger("schiffespiel");
		LOGGER.setUseParentHandlers(false);
		Handler handler = new LoggingHandler();
		handler.setFormatter(new LoggingFormatter());
		handler.setLevel(Level.ALL);
		LOGGER.addHandler(handler);
		LOGGER.setLevel(Level.ALL);
	}
	
}
