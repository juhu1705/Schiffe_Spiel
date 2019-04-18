package schiffespiel.common.math;

public class Vec3f {

	public float x, y, z;
	
	public Vec3f()	{
		this.x = 0.0f;
		this.y = 0.0f;
		this.z = 0.0f;
	}
	
	public Vec3f(float f)	{
		this.x = f;
		this.y = f;
		this.z = f;
	}
	
	public Vec3f(float x, float y, float z)	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public float getLength() {
		return (float)Math.sqrt(x * x + y * y + z * z);
	}
	
	public Vec3f normalize()	{
		float length = this.getLength();
		
		this.x /= length;
		this.y /= length;
		this.z /= length;
		
		return this;
	}
}
