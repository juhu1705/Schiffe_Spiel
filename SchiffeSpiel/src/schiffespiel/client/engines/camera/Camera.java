package schiffespiel.client.engines.camera;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import schiffespiel.common.util.Ref;

public abstract class Camera {

	public static final Vector3f UP = new Vector3f(0, 1, 0);

	protected Matrix4f viewMatrix, projectionMatrix;

	protected Vector3f position;

	protected float zNear, zFar, fov, aspectRatio;

	private static Camera camera;

	public static Camera getInstance() {
		return Camera.camera == null ? Camera.setCamera(new FPCamera()) : Camera.camera;
	}

	public static Camera setCamera(Camera camera) {
		return Camera.camera = camera;
	}

	public Matrix4f getViewMatrix() {
		return this.viewMatrix;
	}

	public Matrix4f getProjectionMatrix() {
		return this.projectionMatrix;
	}

	public Vector3f getPosition() {
		if (this.position == null)
			Ref.LOGGER.fine("h");
		return this.position;
	}

	public abstract void init();

	public abstract void update();

}
