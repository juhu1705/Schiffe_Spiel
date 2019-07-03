package schiffespiel.client.engines.game.entity;

import static schiffespiel.common.util.ResourceLoader.loadShader;

import schiffespiel.client.engines.game.entity.Shader.CompileException;

public class Shaders {

	public static final Shader BASIC_ENTITY;
	static {
		BASIC_ENTITY = new Shader();
		BASIC_ENTITY.addFragmentShader(loadShader("basicEntity/entity_fs.glsl"));
		BASIC_ENTITY.addVertexShader(loadShader("basicEntity/entity_vs.glsl"));

		try {
			BASIC_ENTITY.compileShader();
		} catch (CompileException e) {
			e.printStackTrace();
		}

		BASIC_ENTITY.addUniform("projection_matrix");
		BASIC_ENTITY.addUniform("model_matrix");
		BASIC_ENTITY.addUniform("view_matrix");

	}

}
