package schiffespiel.common.math.vec;

/**
 * Represents a 4-dimensional Vector
 * @category Math
 * @version 0.1
 * @author Juhu1705
 */
public class Vec4f {
	
	/**
	 * the x, y, z, w coordinates of the Vec4f
	 */
	public float x, y, z, w;
	
	/**
	 * Creates a Null-Vector
	 */
	public Vec4f()	{
		this.x = 0.0f;
		this.y = 0.0f;
		this.z = 0.0f;
		this.w = 0.0f;
	}
	
	/**
	 * x, y, z, w coordinates set to f
	 * @param f A float value
	 */
	public Vec4f(float f)	{
		this.x = f;
		this.y = f;
		this.z = f;
		this.w = f;
	}
	
	/**
	 * Sets x, y, z and w to x, y, z and w 
	 * @param x
	 * @param y
	 * @param z
	 * @param w
	 */
	public Vec4f(float x, float y, float z, float w)	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}
	
	/**
	 * Creating a vec3f by adding a w coordinate to a given vec3f
	 * @param vec3f
	 * @param w
	 */
	public Vec4f(Vec3f vec3f, float w)	{
		this.x = vec3f.x;
		this.y = vec3f.y;
		this.z = vec3f.z;
		this.w = w;
	}
	
	/**
	 * Creates a new Vec4f with the values of the given Vec4f
	 * @param vec4f A Vec4f
	 */
	public Vec4f(Vec4f vec4f) {
		this.x = vec4f.x;
		this.y = vec4f.y;
		this.z = vec4f.z;
		this.w = vec4f.w;
	}

	/**
	 * 
	 * @return Length of the vector
	 */
	public float getLength() {
		return (float)Math.sqrt(x * x + y * y + z * z + w * w);
	}
	
	/**
	 * Normalize the vector
	 * @return this
	 */
	public Vec4f normalize()	{
		float length = this.getLength();
		
		this.x /= length;
		this.y /= length;
		this.z /= length;
		this.w /= length;
		
		return this;
	}
	
	/**
	 * Multiplies this vector and the given Vec4f.
	 * @param vec4f The vector to multiply this vector with.
	 * @return A new Vec4f with the multiplied data.
	 */
	public Vec4f mul(Vec4f vec4f)	{
		return new Vec4f(x * vec4f.x, y * vec4f.y, z * vec4f.z, w * vec4f.w);
	}
	
	/**
	 * Multiplies this vector with the float f.
	 * @param f The float to multiply this vector with.
	 * @return A new Vec4f with the multiplied data.
	 */
	public Vec4f mul(float f)	{
		return new Vec4f(x * f, y * f, z * f, w * f);
	}
	
	/**
	 * Divided this with given Vec4f
	 * @param vec4f The Vec4f to divide this Vec4f with
	 * @return A new Vec4f with divided values
	 */
	public Vec4f div(Vec4f vec4f)	{
		return new Vec4f(x / vec4f.x, y / vec4f.y, z / vec4f.z, w / vec4f.w);
	}
	
	/**
	 * Divided this with given float
	 * @param f The float to divide this Vec4f with
	 * @return A new Vec4f with divided values
	 */
	public Vec4f div(float f)	{
		return new Vec4f(x / f, y / f, z / f, w / f);
	}
	
	/**
	 * Subtract given Vec4f from this
	 * @param vec4f The Vec4f to subtract this Vec4f with
	 * @return A new Vec4f with subtracted values
	 */
	public Vec4f sub(Vec4f vec4f)	{
		return new Vec4f(x - vec4f.x, y - vec4f.y, z - vec4f.z, w - vec4f.w);
	}
	
	/**
	 * Subtract given float from this
	 * @param f The float to subtract this Vec4f with
	 * @return A new Vec4f with subtracted values
	 */
	public Vec4f sub(float f)	{
		return new Vec4f(x - f, y - f, z - f, w - f);
	}
	
	/**
	 * Add given Vec4f to this
	 * @param vec4f The Vec4f to add to this Vec4f
	 * @return A new Vec4f with added values
	 */
	public Vec4f add(Vec4f vec4f)	{
		return new Vec4f(x + vec4f.x, y + vec4f.y, z + vec4f.z, w + vec4f.w);
	}
	
	/**
	 * Add given float to this
	 * @param f The float to add to this Vec4f
	 * @return A new Vec4f with added values
	 */
	public Vec4f add(float f)	{
		return new Vec4f(x + f, y + f, z + f, w + f);
	}
	
	/**
	 * Returns modulo
	 * @param vec4f The Vec4f to modulo this Vec4f with
	 * @return A new Vec4f with modulo values
	 */
	public Vec4f mod(Vec4f vec4f)	{
		return new Vec4f(x % vec4f.x, y % vec4f.y, z % vec4f.z, w % vec4f.w);
	}
	
	/**
	 * Returns modulo
	 * @param f The float to modulo this Vec4f with
	 * @return A new Vec4f with modulo values
	 */
	public Vec4f mod(float f)	{
		return new Vec4f(x % f, y % f, z % f, w % f);
	}
	
	@Override
	protected Vec4f clone() throws CloneNotSupportedException {
		return new Vec4f(this);
	}
	
	@Override
	public String toString() {
		return "[" + x + "|" + y + "|" + z + "|" + w + "]";
	}
}
