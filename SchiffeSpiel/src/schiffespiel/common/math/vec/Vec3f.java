package schiffespiel.common.math.vec;

/**
 * Represents a 3-dimensional Vector
 * @category Math
 * @version 0.1
 * @author Juhu1705
 */
public class Vec3f {

	/**
	 * the x, y, z coordinates of the Vec3f
	 */
	public float x, y, z;
	
	/**
	 * Creates a Null-Vector
	 */
	public Vec3f() {
		this.x = 0.0f;
		this.y = 0.0f;
		this.z = 0.0f;
	}
	
	/**
	 * x, y, z coordinates set to f
	 * @param f A float value
	 */
	public Vec3f(float f) {
		this.x = f;
		this.y = f;
		this.z = f;
	}
	
	/**
	 * Sets x, y and z to x, y and z 
	 * @param x
	 * @param y
	 * @param z
	 */
	public Vec3f(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	/**
	 * Creates a vec3f by adding a z coordinate to the given vec2f
	 * @param vec2f
	 * @param z
	 */
	public Vec3f(Vec2f vec2f, float z) {
		this.x = vec2f.x;
		this.y = vec2f.y;
		this.z = z;
	}
	
	/**
	 * Creates a new Vec3f with the values of the given vec3f
	 * @param vec3f A Vec3f
	 */
	public Vec3f(Vec3f vec3f) {
		this.x = vec3f.x;
		this.y = vec3f.y;
		this.z = vec3f.z;
	}

	/**
	 * 
	 * @return Length of the vector
	 */
	public float getLength() {
		return (float)Math.sqrt(x * x + y * y + z * z);
	}
	
	/**
	 * Normalize the vector
	 * @return this
	 */
	public Vec3f normalize() {
		float length = this.getLength();
		
		this.x /= length;
		this.y /= length;
		this.z /= length;
		
		return this;
	}
	
	/**
	 * Multiplies this vector and the given vec3f.
	 * @param vec3f The vector to multiply this vector with.
	 * @return A new Vec3f with the multiplied data.
	 */
	public Vec3f mul(Vec3f vec3f) {
		return new Vec3f(x * vec3f.x, y * vec3f.y, z * vec3f.z);
	}
	
	/**
	 * Multiplies this vector with the float f.
	 * @param f The float to multiply this vector with.
	 * @return A new Vec3f with the multiplied data.
	 */
	public Vec3f mul(float f) {
		return new Vec3f(x * f, y * f, z * f);
	}
	
	/**
	 * Divided this with given vec3f
	 * @param vec3f The vec3f to divide this vec3f with
	 * @return A new vec3f with divided values
	 */
	public Vec3f div(Vec3f vec3f) {
		return new Vec3f(x / vec3f.x, y / vec3f.y, z / vec3f.z);
	}
	
	/**
	 * Divided this with given float
	 * @param f The float to divide this vec3f with
	 * @return A new vec3f with divided values
	 */
	public Vec3f div(float f) {
		return new Vec3f(x / f, y / f, z / f);
	}
	
	/**
	 * Subtract given vec3f from this
	 * @param vec3f The vec3f to subtract this vec3f with
	 * @return A new Vec3f with subtracted values
	 */
	public Vec3f sub(Vec3f vec3f) {
		return new Vec3f(x - vec3f.x, y - vec3f.y, z - vec3f.z);
	}
	
	/**
	 * Subtract given float from this
	 * @param f The float to subtract this vec3f with
	 * @return A new Vec3f with subtracted values
	 */
	public Vec3f sub(float f) {
		return new Vec3f(x - f, y - f, z - f);
	}
	
	/**
	 * Add given vec3f to this
	 * @param vec3f The vec3f to add to this vec3f
	 * @return A new Vec3f with added values
	 */
	public Vec3f add(Vec3f vec3f) {
		return new Vec3f(x + vec3f.x, y + vec3f.y, z + vec3f.z);
	}
	
	/**
	 * Add given float to this
	 * @param f The float to add to this vec3f
	 * @return A new Vec3f with added values
	 */
	public Vec3f add(float f) {
		return new Vec3f(x + f, y + f, z + f);
	}
	
	/**
	 * Returns modulo
	 * @param vec3f The vec3f to modulo this vec3f with
	 * @return A new Vec3f with modulo values
	 */
	public Vec3f mod(Vec3f vec3f) {
		return new Vec3f(x % vec3f.x, y % vec3f.y, z % vec3f.z);
	}
	
	/**
	 * Returns modulo
	 * @param f The float to modulo this vec3f with
	 * @return A new Vec3f with modulo values
	 */
	public Vec3f mod(float f) {
		return new Vec3f(x % f, y % f, z % f);
	}
	
	@Override
	protected Vec3f clone() throws CloneNotSupportedException {
		return new Vec3f(this);
	}
	
	@Override
	public String toString() {
		return "[" + x + "|" + y + "|" + z + "]";
	}
	
}
