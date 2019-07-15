package schiffespiel.client.engines.camera;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

import schiffespiel.client.input.Input;
import schiffespiel.client.input.Window;

public class FPCamera extends Camera {

	public static void useFPCamera() {
		Camera.setCamera(new FPCamera());
		Camera.getInstance().init();
	}

	public Vector3f direction;

	@Override
	public void init() {
		super.fov = 60f;
		super.zNear = 0.01f;
		super.zFar = 100f;
		super.aspectRatio = Window.getInstance().getWidth() / Window.getInstance().getHeight();
		super.position = new Vector3f(10, 10, 10);
		this.direction = new Vector3f(360.0f, 360.0f, 360.0f);

		super.viewMatrix = new Matrix4f().identity().lookAt(new Vector3f(this.position).mul(-1),
				new Vector3f(this.position).add(this.direction), super.UP);
		super.projectionMatrix = new Matrix4f().identity().perspective(super.fov, super.aspectRatio, super.zNear,
				super.zFar);
	}

	@Override
	public void update() {
		if (Input.getInstance().isKeyHold(GLFW.GLFW_KEY_W))
			this.position.x += 1;
		if (Input.getInstance().isKeyHold(GLFW.GLFW_KEY_S))
			this.position.x -= 1;

		if (Input.getInstance().isKeyHold(GLFW.GLFW_KEY_A))
			this.position.z += 1;
		if (Input.getInstance().isKeyHold(GLFW.GLFW_KEY_D))
			this.position.z -= 1;

		if (Input.getInstance().isKeyHold(GLFW.GLFW_KEY_SPACE))
			this.position.y += 1;
		if (Input.getInstance().isKeyHold(GLFW.GLFW_KEY_LEFT_SHIFT))
			this.position.y -= 1;

		super.viewMatrix = new Matrix4f().identity().lookAt(this.position,
				new Vector3f(this.position).add(this.direction), super.UP);
		super.projectionMatrix = new Matrix4f().identity().perspective(super.fov, super.aspectRatio, super.zNear,
				super.zFar);
	}

	@Override
	public Vector3f getPosition() {
		return this.position;
	}
}
