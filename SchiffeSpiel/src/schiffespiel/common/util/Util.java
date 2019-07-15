package schiffespiel.common.util;

import java.nio.FloatBuffer;

import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;

/**
 * Enth�lt brauchbare, vom Rest des Projekts unabh�ngige Methoden
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

	public static FloatBuffer createFloatBuffer(int size) {
		return BufferUtils.createFloatBuffer(size);
	}

	public static FloatBuffer createFlippedBuffer(Matrix4f matrix) {
		FloatBuffer buffer = createFloatBuffer(4 * 4);

		float[] fa = new float[4 * 4];

		matrix.get(fa);

		for (float f : fa) {
			buffer.put(f);
		}

		buffer.flip();

		return buffer;
	}

}
