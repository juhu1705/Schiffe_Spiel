package schiffespiel.client.engines.game.entity;

import org.eclipse.jdt.annotation.Nullable;
import org.joml.Vector3f;

import schiffespiel.client.engines.game.Transform;
import schiffespiel.client.interfaces.IModel;

public abstract class Entity implements IModel {

	protected Shader shader;
	protected Mesh mesh;

	protected Transform transform;

	public Entity(@Nullable Shader shader, @Nullable Transform transform, Mesh mesh) {
		if (shader == null)
			shader = new Shader();
		if (transform == null)
			transform = new Transform();

		this.mesh = mesh;
		this.transform = transform;
		this.shader = shader;

		this.transform.setScaling(new Vector3f(0.5f, 0.5f, 0.5f));
	}

	public void initialTransformUniformsToShader() {
		this.shader.addUniform("projection_matrix");
		this.shader.addUniform("model_matrix");
		this.shader.addUniform("view_matrix");
	}

	public void setTransformUniformsToShader() {
		this.shader.setUniform("projection_matrix", this.transform.getProjectionMatrix());
		this.shader.setUniform("model_matrix", this.transform.getModelMatrix());
		this.shader.setUniform("view_matrix", this.transform.getViewMatrix());
	}

	@Override
	public Mesh getMesh() {
		return this.mesh;
	}

	@Override
	public Shader getShader() {
		return this.shader;
	}

	public void renderUsingShader() {
		this.shader.bind();
		this.setTransformUniformsToShader();
		this.render();
		this.shader.unbind();
	}

	public void render() {
		this.mesh.render();
	}

}
