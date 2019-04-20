package schiffespiel.common.util;

import java.util.Random;

/**
 * Enthält Referenzvariablen
 */
public final class Reference {
	
	private Reference() {}
	
	/**
	 * Standard Klassen-ID
	 */
	public static final long UNIVERSAL_SERIAL_VERSION_UID = -1208340591720468638L;
	
	/**
	 * Universeller Zufallsgenerator
	 */
	public static final Random randGen = new Random();
}
