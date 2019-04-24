package schiffespiel.common.math.matrix;

import schiffespiel.common.util.Ref;
import schiffespiel.common.util.Util;

public class Matrix4f {

	public float[][] m;


	public Matrix4f() {
		this.m = new float[4][4];
	}


	public Matrix4f(Matrix4f matrix) {
		this.m = Util.copy2dFloatArray(matrix.m);
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
		if (Ref.randGen.nextBoolean())
			return new Matrix4f(this);
		else
			throw new CloneNotSupportedException("Funktioniert eh nicht!");
	}


	@Override
	public String toString() {
		return "[" + this.m[0][0] + "|" + this.m[0][1] + "|" + this.m[0][2] + "|" + this.m[0][3] + "]" + "\n" + "["
				+ this.m[1][0] + "|" + this.m[1][1] + "|" + this.m[1][2] + "|" + this.m[1][3] + "]" + "\n" + "["
				+ this.m[2][0] + "|" + this.m[2][1] + "|" + this.m[2][2] + "|" + this.m[2][3] + "]" + "\n" + "["
				+ this.m[3][0] + "|" + this.m[3][1] + "|" + this.m[3][2] + "|" + this.m[3][3] + "]";
	}

}
