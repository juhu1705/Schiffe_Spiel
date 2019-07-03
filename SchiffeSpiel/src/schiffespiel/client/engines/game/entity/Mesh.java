package schiffespiel.client.engines.game.entity;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_INT;
import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL11.glDrawElements;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_ELEMENT_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL15.glDeleteBuffers;
import static org.lwjgl.opengl.GL15.glGenBuffers;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glDeleteVertexArrays;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

public class Mesh {

	private int vao, vertexCount, iBufferVBO;
	private boolean useIBuffer;
	private int[] vbos;

	public Mesh(float[] positions) {
		this.vao = glGenVertexArrays();
		glBindVertexArray(this.vao);

		int positionVBO = this.addStaticAttribute(0, positions, 3);
		this.vbos = new int[] { positionVBO };
		this.vertexCount = positions.length / 3;
		this.useIBuffer = false;
	}

	public Mesh(int[] indices, float[] positions) {
		this.vao = glGenVertexArrays();
		glBindVertexArray(this.vao);

		this.iBufferVBO = this.attashIBuffer(indices);
		int positionVBO = this.addStaticAttribute(0, positions, 3);
		this.vbos = new int[] { positionVBO };
		this.vertexCount = indices.length;
		this.useIBuffer = true;
	}

	public Mesh(int[] indices, float[] positions, float[] textureCoordinates) {
		this.vao = glGenVertexArrays();
		glBindVertexArray(this.vao);

		this.iBufferVBO = this.attashIBuffer(indices);
		int positionVBO = this.addStaticAttribute(0, positions, 3);
		int textureCoordsVBO = this.addStaticAttribute(1, textureCoordinates, 2);

		this.vbos = new int[] { positionVBO, textureCoordsVBO };
		this.vertexCount = indices.length;
		this.useIBuffer = true;
	}

	public Mesh(float[] positions, float[] textureCoordinates) {
		this.vao = glGenVertexArrays();
		glBindVertexArray(this.vao);

		int positionVBO = this.addStaticAttribute(0, positions, 3);
		int textureCoordsVBO = this.addStaticAttribute(1, textureCoordinates, 2);
		this.vbos = new int[] { positionVBO, textureCoordsVBO };
		this.vertexCount = positions.length / 3 + textureCoordinates.length / 2;
		this.useIBuffer = false;
	}

	public void render() {
		glBindVertexArray(this.vao);
		for (int i = 0; i < this.vbos.length; i++) {
			glEnableVertexAttribArray(i);
		}
		if (this.useIBuffer)
			glDrawElements(GL_TRIANGLES, this.vertexCount, GL_UNSIGNED_INT, 0);
		else
			glDrawArrays(GL_TRIANGLES, 0, this.vertexCount);
		for (int i = 0; i < this.vbos.length; i++) {
			glDisableVertexAttribArray(i);
		}
	}

	public void delete() {
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		glDeleteVertexArrays(this.vao);
		for (int id : this.vbos) {
			glDeleteBuffers(id);
		}
		if (this.useIBuffer)
			glDeleteBuffers(this.iBufferVBO);
	}

	private int addStaticAttribute(int index, float[] data, int dataSize) {
		int vbo = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		glBufferData(GL_ARRAY_BUFFER, data, GL_STATIC_DRAW);
		glVertexAttribPointer(index, dataSize, GL_FLOAT, false, 0, 0);
		return vbo;
	}

	private int attashIBuffer(int[] indices) {
		int vbo = glGenBuffers();
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, vbo);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, indices, GL_STATIC_DRAW);
		return vbo;
	}

}
