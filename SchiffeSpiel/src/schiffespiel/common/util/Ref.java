package schiffespiel.common.util;

import java.util.Random;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Enthält Referenzvariablen
 */
public final class Ref {

	private Ref() {
	}

	/**
	 * Standard Seriellversion-ID
	 */
	public static final long UNIVERSAL_SERIAL_VERSION_UID = -1208340591720468638L;

	/**
	 * Universeller Zufallsgenerator
	 */
	public static final Random RAND_GEN = new Random();

	public static final int STANDARD_HOST_PORT = 18419;

	public static final int GRAV = 10;

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
