package schiffespiel.client.engines.game.entity;

import static org.lwjgl.opengl.GL20.GL_COMPILE_STATUS;
import static org.lwjgl.opengl.GL20.GL_FRAGMENT_SHADER;
import static org.lwjgl.opengl.GL20.GL_LINK_STATUS;
import static org.lwjgl.opengl.GL20.GL_VALIDATE_STATUS;
import static org.lwjgl.opengl.GL20.GL_VERTEX_SHADER;
import static org.lwjgl.opengl.GL20.glAttachShader;
import static org.lwjgl.opengl.GL20.glCompileShader;
import static org.lwjgl.opengl.GL20.glCreateProgram;
import static org.lwjgl.opengl.GL20.glCreateShader;
import static org.lwjgl.opengl.GL20.glGetProgramInfoLog;
import static org.lwjgl.opengl.GL20.glGetProgrami;
import static org.lwjgl.opengl.GL20.glGetShaderInfoLog;
import static org.lwjgl.opengl.GL20.glGetShaderi;
import static org.lwjgl.opengl.GL20.glGetUniformLocation;
import static org.lwjgl.opengl.GL20.glLinkProgram;
import static org.lwjgl.opengl.GL20.glShaderSource;
import static org.lwjgl.opengl.GL20.glUniform1f;
import static org.lwjgl.opengl.GL20.glUniform1fv;
import static org.lwjgl.opengl.GL20.glUniform1i;
import static org.lwjgl.opengl.GL20.glUniform2f;
import static org.lwjgl.opengl.GL20.glUniform3f;
import static org.lwjgl.opengl.GL20.glUniform4f;
import static org.lwjgl.opengl.GL20.glUniformMatrix4fv;
import static org.lwjgl.opengl.GL20.glUseProgram;
import static org.lwjgl.opengl.GL20.glValidateProgram;
import static org.lwjgl.opengl.GL30.glBindFragDataLocation;
import static org.lwjgl.opengl.GL31.glGetUniformBlockIndex;
import static org.lwjgl.opengl.GL31.glUniformBlockBinding;
import static org.lwjgl.opengl.GL32.GL_GEOMETRY_SHADER;
import static org.lwjgl.opengl.GL40.GL_TESS_CONTROL_SHADER;
import static org.lwjgl.opengl.GL40.GL_TESS_EVALUATION_SHADER;
import static org.lwjgl.opengl.GL43.GL_COMPUTE_SHADER;
import static schiffespiel.common.util.Ref.LOGGER;

import java.util.HashMap;
import java.util.logging.Level;

import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector2f;
import org.joml.Vector3f;

import schiffespiel.common.util.Util;

public class Shader {

	private int ID;
	private HashMap<String, Integer> uniforms;

	public Shader() {
		this.ID = glCreateProgram();
		this.uniforms = new HashMap<>();

		if (this.ID == 0) {
			LOGGER.log(Level.SEVERE, "Can't create shader! Does the System exit?", new Exception());
			System.exit(1);
		}

	}

	public void bind() {
		glUseProgram(this.ID);
	}

	public void addUniform(String uniform) {
		int uniformLocation = glGetUniformLocation(this.ID, uniform);

		if (uniformLocation == 0xFFFFFFFF) {
			LOGGER.log(Level.SEVERE, this.getClass().getName() + " Error: Could not find uniform: " + uniform,
					new Exception());
			return;
		}

		uniforms.put(uniform, uniformLocation);
	}

	public void addUniformBlock(String uniform) {
		int uniformLocation = glGetUniformBlockIndex(this.ID, uniform);
		if (uniformLocation == 0xFFFFFFFF) {
			LOGGER.log(Level.SEVERE, this.getClass().getName() + " Error: Could not find uniform: " + uniform,
					new Exception());
			return;
		}

		uniforms.put(uniform, uniformLocation);
	}

	public void addVertexShader(String text) {
		addProgram(text, GL_VERTEX_SHADER);
	}

	public void addGeometryShader(String text) {
		addProgram(text, GL_GEOMETRY_SHADER);
	}

	public void addFragmentShader(String text) {
		addProgram(text, GL_FRAGMENT_SHADER);
	}

	public void addTessellationControlShader(String text) {
		addProgram(text, GL_TESS_CONTROL_SHADER);
	}

	public void addTessellationEvaluationShader(String text) {
		addProgram(text, GL_TESS_EVALUATION_SHADER);
	}

	public void addComputeShader(String text) {
		addProgram(text, GL_COMPUTE_SHADER);
	}

	public void compileShader() throws CompileException {
		glLinkProgram(this.ID);

		if (glGetProgrami(this.ID, GL_LINK_STATUS) == 0) {
			throw new CompileException(this.getClass().getName() + " " + glGetProgramInfoLog(this.ID, 1024));
		}

		glValidateProgram(this.ID);

		if (glGetProgrami(this.ID, GL_VALIDATE_STATUS) == 0) {
			throw new CompileException(this.getClass().getName() + " " + glGetProgramInfoLog(this.ID, 1024));
		}
	}

	public class CompileException extends Exception {

		public CompileException(String string) {
			super(string);
		}

	}

	private void addProgram(String text, int type) {
		int shader = glCreateShader(type);

		if (shader == 0) {
			LOGGER.severe(this.getClass().getName() + " Shader creation failed");
			System.exit(1);
		}

		glShaderSource(shader, text);
		glCompileShader(shader);

		if (glGetShaderi(shader, GL_COMPILE_STATUS) == 0) {
			LOGGER.severe(this.getClass().getName() + " " + glGetShaderInfoLog(shader, 1024));
			System.exit(1);
		}

		glAttachShader(this.ID, shader);
	}

	public void setUniformi(String uniformName, int value) {
		glUniform1i(uniforms.get(uniformName), value);
	}

	public void setUniformf(String uniformName, float value) {
		glUniform1f(uniforms.get(uniformName), value);
	}

	public void setUniform(String uniformName, Vector2f value) {
		glUniform2f(uniforms.get(uniformName), value.x, value.y);
	}

	public void setUniform(String uniformName, Vector3f value) {
		glUniform3f(uniforms.get(uniformName), value.x, value.y, value.z);
	}

	public void setUniform(String uniformName, Quaternionf value) {
		glUniform4f(uniforms.get(uniformName), value.x, value.y, value.z, value.w);
	}

	public void setUniform(String uniformName, Matrix4f value) {
		glUniformMatrix4fv(uniforms.get(uniformName), true, Util.createFlippedBuffer(value));
	}

	public void setUniform(String uniformName, float[] value) {
		glUniform1fv(this.uniforms.get(uniformName), value);
	}

	public void bindUniformBlock(String uniformBlockName, int uniformBlockBinding) {
		glUniformBlockBinding(this.ID, uniforms.get(uniformBlockName), uniformBlockBinding);
	}

	public void bindFragDataLocation(String name, int index) {
		glBindFragDataLocation(this.ID, index, name);
	}

	public int getProgram() {
		return this.ID;
	}

	public void unbind() {
		glUseProgram(0);
	}

}
