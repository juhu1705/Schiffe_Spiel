package schiffespiel.common.util;

import java.nio.FloatBuffer;

import org.joml.Matrix4f;

/**
 * Enthält brauchbare, vom Rest des Projekts unabhängige Methoden
 */
public final class Util {

	private Util() {
	}

	/**
	 * Copy a 2-Dimensional float array
	 * 
	 * @param toCopy The float Array that should been copied
	 * @return A copy of the toCopy Array
	 * @author Juhu1705
	 */
	public static float[][] copy2dFloatArray(float[][] toCopy) {
		float[][] newArray = new float[toCopy.length][];
		for (int i = 0; i < toCopy.length; i++) {
			newArray[i] = new float[toCopy[i].length];
			for (int y = 0; y < toCopy[i].length; y++) {
				newArray[i][y] = toCopy[i][y];
			}
		}
		return newArray;
	}

	public static FloatBuffer createFlippedBuffer(Matrix4f value) {

		return null;
	}

}
