package schiffespiel.common.math.matrix;

import java.io.Serializable;

import schiffespiel.common.math.vec.Vec3f;
import schiffespiel.common.util.Util;

/**
 * Represents a 4-dimensional Matrix
 * 
 * @category Math
 * @version 0.1
 * @author Juhu1705
 *
 */
public class Matrix4f implements Serializable {

	/**
	 * The Matrix
	 */
	public float[][] m;

	/**
	 * Instance a empty Matrix
	 */
	public Matrix4f() {
		this.m = new float[4][4];
	}

	/**
	 * 
	 * @param matrix
	 */
	public Matrix4f(Matrix4f matrix) {
		this.m = Util.copy2dFloatArray(matrix.m);
	}

	/**
	 * 
	 * @param target
	 * @param up
	 * @return
	 */
	public Matrix4f setView(Vec3f target, Vec3f up) {

		return this;
	}

	public Matrix4f zero() {

		this.m[0][0] = 0;
		this.m[0][1] = 0;
		this.m[0][2] = 0;
		this.m[0][3] = 0;
		this.m[1][0] = 0;
		this.m[1][1] = 0;
		this.m[1][2] = 0;
		this.m[1][3] = 0;
		this.m[2][0] = 0;
		this.m[2][1] = 0;
		this.m[2][2] = 0;
		this.m[2][3] = 0;
		this.m[3][0] = 0;
		this.m[3][1] = 0;
		this.m[3][2] = 0;
		this.m[3][3] = 0;

		return this;
	}

	public Matrix4f identity() {

		this.m[0][0] = 1;
		this.m[0][1] = 0;
		this.m[0][2] = 0;
		this.m[0][3] = 0;
		this.m[1][0] = 0;
		this.m[1][1] = 1;
		this.m[1][2] = 0;
		this.m[1][3] = 0;
		this.m[2][0] = 0;
		this.m[2][1] = 0;
		this.m[2][2] = 1;
		this.m[2][3] = 0;
		this.m[3][0] = 0;
		this.m[3][1] = 0;
		this.m[3][2] = 0;
		this.m[3][3] = 1;

		return this;

	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return new Matrix4f(this);
	}

	@Override
	public String toString() {
		return "[" + this.m[0][0] + "|" + this.m[0][1] + "|" + this.m[0][2] + "|" + this.m[0][3] + "]" + "\n" + "["
				+ this.m[1][0] + "|" + this.m[1][1] + "|" + this.m[1][2] + "|" + this.m[1][3] + "]" + "\n" + "["
				+ this.m[2][0] + "|" + this.m[2][1] + "|" + this.m[2][2] + "|" + this.m[2][3] + "]" + "\n" + "["
				+ this.m[3][0] + "|" + this.m[3][1] + "|" + this.m[3][2] + "|" + this.m[3][3] + "]";
	}

}
