package schiffespiel.client.engines.game;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import schiffespiel.client.engines.camera.Camera;

public class Transform {

	private Vector3f position, rotation, scaling;

	public Transform(Vector3f position, Vector3f rotation, Vector3f scaling) {
		this.position = position;
		this.rotation = rotation;
		this.scaling = scaling;
	}

	public Transform() {
		this(new Vector3f(0, 0, 0), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1));
	}

	public Matrix4f getModelMatrix() {
		Matrix4f positionMatrix = new Matrix4f().translate(this.position);
		Matrix4f rotationMatrix = new Matrix4f().rotateXYZ(this.rotation);
		Matrix4f scalingMatrix = new Matrix4f().scale(this.scaling);

		return positionMatrix.mul(rotationMatrix.mul(scalingMatrix));
	}

	public Matrix4f getViewMatrix() {
		return Camera.getInstance().getViewMatrix();
	}

	public Matrix4f getProjectionMatrix() {
		return Camera.getInstance().getProjectionMatrix();
	}

	/**
	 * @return the position
	 */
	public Vector3f getPosition() {
		return position;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(Vector3f position) {
		this.position = position;
	}

	/**
	 * @return the rotation
	 */
	public Vector3f getRotation() {
		return rotation;
	}

	/**
	 * @param rotation the rotation to set
	 */
	public void setRotation(Vector3f rotation) {
		this.rotation = rotation;
	}

	/**
	 * @return the scaling
	 */
	public Vector3f getScaling() {
		return scaling;
	}

	/**
	 * @param scaling the scaling to set
	 */
	public void setScaling(Vector3f scaling) {
		this.scaling = scaling;
	}

}
