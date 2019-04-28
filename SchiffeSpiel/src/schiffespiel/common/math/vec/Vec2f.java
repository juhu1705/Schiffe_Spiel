package schiffespiel.common.math.vec;

import java.io.Serializable;

/**
 * Represents a 2-dimensional Vector
 * 
 * @category Math
 * @version 0.1
 * @author Juhu1705
 */
public class Vec2f implements Serializable {

	/**
	 * the x, y coordinates of the Vec2f
	 */
	public float x, y;

	/**
	 * Creates a Null-Vector
	 */
	public Vec2f() {
		this.x = 0.0f;
		this.y = 0.0f;
	}

	/**
	 * x, y coordinates set to f
	 * 
	 * @param f A float value
	 */
	public Vec2f(float f) {
		this.x = f;
		this.y = f;
	}

	/**
	 * Sets x, y to x, y
	 * 
	 * @param x
	 * @param y
	 */
	public Vec2f(float x, float y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Creates a new Vec2f with the values of the given Vec2f
	 * 
	 * @param vec2f A Vec2f
	 */
	public Vec2f(Vec2f vec2f) {
		this.x = vec2f.x;
		this.y = vec2f.y;
	}

	/**
	 *
	 * @return Length of the vector
	 */
	public float getLength() {
		return (float) Math.sqrt(x * x + y * y);
	}

	/**
	 * Normalize the vector
	 * 
	 * @return this
	 */
	public Vec2f normalize() {
		float length = this.getLength();

		this.x /= length;
		this.y /= length;

		return this;
	}

	/**
	 * Multiplies this vector and the given Vec2f.
	 * 
	 * @param vec2f The vector to multiply this vector with.
	 * @return A new Vec2f with the multiplied data.
	 */
	public Vec2f mul(Vec2f vec2f) {
		return new Vec2f(x * vec2f.x, y * vec2f.y);
	}

	/**
	 * Multiplies this vector with the float f.
	 * 
	 * @param f The float to multiply this vector with.
	 * @return A new Vec2f with the multiplied data.
	 */
	public Vec2f mul(float f) {
		return new Vec2f(x * f, y * f);
	}

	/**
	 * Divided this with given Vec2f
	 * 
	 * @param vec2f The Vec2f to divide this Vec2f with
	 * @return A new Vec2f with divided values
	 */
	public Vec2f div(Vec2f vec2f) {
		return new Vec2f(x / vec2f.x, y / vec2f.y);
	}

	/**
	 * Divided this with given float
	 * 
	 * @param f The float to divide this Vec2f with
	 * @return A new Vec2f with divided values
	 */
	public Vec2f div(float f) {
		return new Vec2f(x / f, y / f);
	}

	/**
	 * Subtract given Vec2f from this
	 * 
	 * @param vec2f The Vec2f to subtract this Vec2f with
	 * @return A new Vec2f with subtracted values
	 */
	public Vec2f sub(Vec2f vec2f) {
		return new Vec2f(x - vec2f.x, y - vec2f.y);
	}

	/**
	 * Subtract given float from this
	 * 
	 * @param f The float to subtract this Vec2f with
	 * @return A new Vec2f with subtracted values
	 */
	public Vec2f sub(float f) {
		return new Vec2f(x - f, y - f);
	}

	/**
	 * Add given Vec2f to this
	 * 
	 * @param vec2f The Vec2f to add to this Vec2f
	 * @return A new Vec2f with added values
	 */
	public Vec2f add(Vec2f vec2f) {
		return new Vec2f(x + vec2f.x, y + vec2f.y);
	}

	/**
	 * Add given float to this
	 * 
	 * @param f The float to add to this Vec2f
	 * @return A new Vec2f with added values
	 */
	public Vec2f add(float f) {
		return new Vec2f(x + f, y + f);
	}

	/**
	 * Returns modulo
	 * 
	 * @param vec2f The Vec2f to modulo this Vec2f with
	 * @return A new Vec2f with modulo values
	 */
	public Vec2f mod(Vec2f vec2f) {
		return new Vec2f(x % vec2f.x, y % vec2f.y);
	}

	/**
	 * Returns modulo
	 * 
	 * @param f The float to modulo this Vec2f with
	 * @return A new Vec2f with modulo values
	 */
	public Vec2f mod(float f) {
		return new Vec2f(x % f, y % f);
	}

	@Override
	protected Vec2f clone() throws CloneNotSupportedException {
		return new Vec2f(this);
	}

	@Override
	public String toString() {
		return "[" + x + "|" + y + "]";
	}
}
