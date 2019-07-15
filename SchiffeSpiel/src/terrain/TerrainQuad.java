package terrain;

import static java.lang.Math.floor;
import static java.lang.Math.min;
import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;
import static schiffespiel.common.util.ResourceLoader.loadShader;

import org.joml.SimplexNoise;
import org.joml.Vector2f;
import org.joml.Vector3f;

import schiffespiel.client.engines.game.Transform;
import schiffespiel.client.engines.game.entity.Mesh;
import schiffespiel.client.engines.game.entity.Shader;
import schiffespiel.client.engines.game.entity.Texture2D;
import schiffespiel.client.engines.game.entity.Shader.CompileException;

public class TerrainQuad {

	private int xSize = 10, zSize = 10;
	private Vector3f[] vertices;
	private int[] triangles;
	private float[][] height;
	private Mesh mesh;
	private Shader terrainShader;
	private Transform worldTransform;
	private Vector3f position;
	private Vector3f rotation;
	private Vector3f scale;
	private Texture2D texture;
	private final float factor = 0.08f;

	public TerrainQuad(int posX, int posY) {
		this.position = new Vector3f(posX, 0, posY);
		this.rotation = new Vector3f(360, 360, 360);
		this.scale = new Vector3f(1f, 1f, 1f);
	}

	public Vector3f getPosition() {
		return this.position;
	}

	public void init() {

		this.texture = new Texture2D("ground/ground2.png");
		this.texture.noFilter();

		this.worldTransform = new Transform();

		this.terrainShader = new Shader();
		this.terrainShader.addFragmentShader(loadShader("terrain/normal_fs.glsl"));
		this.terrainShader.addVertexShader(loadShader("terrain/normal_vs.glsl"));
		try {
			this.terrainShader.compileShader();
		} catch (CompileException e) {
			e.printStackTrace();
		}

		this.terrainShader.addUniform("normalmap");
		// this.terrainShader.addUniform("cameraPosition");
		this.terrainShader.addUniform("worldMatrix");
		this.terrainShader.addUniform("positionArray");
		this.terrainShader.addUniform("localMatrix");

		this.vertices = new Vector3f[(this.xSize + 1) * (this.zSize + 1)];
		this.height = new float[this.xSize + 1][this.zSize + 1];
		this.triangles = new int[this.xSize * this.zSize * 6];

		for (int index = 0, z = 0; z <= this.zSize; z++) {
			for (int x = 0; x <= this.xSize; x++) {
				float y = (SimplexNoise.noise((this.position.x() + x) * factor, (this.position.z() + z) * factor));
				vertices[index] = new Vector3f(x, y, z);
				this.height[x][z] = y;
				index++;
			}
		}

		float[] positions = new float[this.vertices.length * 3];

		for (int i0 = 0, i1 = 0; i0 < this.vertices.length; i0++) {
			positions[i1] = this.vertices[i0].x();
			i1++;
			positions[i1] = 0;
			i1++;
			positions[i1] = this.vertices[i0].z();
			i1++;
		}

		for (int vert = 0, tris = 0, z = 0; z < this.zSize; z++, vert++) {
			for (int x = 0; x < this.xSize; x++, vert++, tris += 6) {
				this.triangles[tris + 0] = vert + 0;
				this.triangles[tris + 1] = vert + this.xSize + 1;
				this.triangles[tris + 2] = vert + 1;
				this.triangles[tris + 3] = vert + 1;
				this.triangles[tris + 4] = vert + this.xSize + 1;
				this.triangles[tris + 5] = vert + this.xSize + 2;
			}
		}

		this.mesh = new Mesh(this.triangles, positions);
	}

	public float getHeight(float x, float z) {
		float terrX = x - this.position.x(), terrZ = z - this.position.z(),
				gridSquareSize = min(this.xSize, this.zSize) / (this.height.length - 1);

		int gridX = (int) floor(terrX / gridSquareSize), gridZ = (int) floor(terrZ / gridSquareSize);

		if (gridX >= this.height.length - 1 || gridX < 0 || gridZ >= this.height.length - 1 || gridZ < 0)
			return 0f;

		float xCoord = (terrX % gridSquareSize) / gridSquareSize, zCoord = (terrZ % gridSquareSize) / gridSquareSize,
				answer;

		if (xCoord <= (1 - zCoord)) {
			answer = barryCentricInterpolation(new Vector3f(0, this.height[gridX][gridZ], 0),
					new Vector3f(1, this.height[gridX + 1][gridZ], 0),
					new Vector3f(0, this.height[gridX][gridZ + 1], 1), new Vector2f(xCoord, zCoord));
		} else {
			answer = barryCentricInterpolation(new Vector3f(1, this.height[gridX + 1][gridZ], 0),
					new Vector3f(1, this.height[gridX + 1][gridZ + 1], 1),
					new Vector3f(0, this.height[gridX][gridZ + 1], 1), new Vector2f(xCoord, zCoord));
		}
		return answer;
	}

	private float toAdd = 0.000f;
	private float n = 0, m = 0;

	public void update() {
		this.n += 0.01;
		this.m -= 0.001;
		this.getWorldTransform().setPosition(this.position);
		this.getWorldTransform().setRotation(this.rotation);
		this.getWorldTransform().setScaling(this.scale);

		// this.terrainShader.setUniform("cameraPosition",
		// Camera.getInstance().getPosition());

		float[] positions = new float[this.vertices.length * 3];

		for (int i0 = 0, i1 = 0; i0 < this.vertices.length; i0++) {
			positions[i1] = this.vertices[i0].x();
			i1++;
//			if(this.vertices[i0].x() == 5 && this.vertices[i0].z() == 5) {
//				if(Math.abs(this.vertices[i0].y() + toAdd) > 25)	{
//					toAdd *= -1;
//					
//				}
//				this.vertices[i0].setY(this.vertices[i0].y() + toAdd);
//			}
			positions[i1] = (this.height[(int) this.vertices[i0].x()][(int) this.vertices[i0]
					.z()] /** + SimplexNoise.noise(n, m) */
			);
			i1++;
			positions[i1] = this.vertices[i0].z();
			i1++;
		}

		this.terrainShader.setUniform("positionArray", positions);

		glActiveTexture(GL_TEXTURE0);
		this.texture.bind();
		this.terrainShader.setUniformi("normalmap", 0);

		this.terrainShader.setUniform("localMatrix", this.getWorldTransform().getWorldMatrix());
		this.terrainShader.setUniform("worldMatrix", this.getWorldTransform().getViewProjectionMatrix());
		// System.out.println(this.getWorldTransform().getViewProjectionMatrix());
	}

	private Transform getWorldTransform() {
		return this.worldTransform;
	}

	public void render() {
		this.terrainShader.bind();
		this.update();
		this.mesh.render();
		this.terrainShader.unbind();
	}

	public void delete() {
		this.mesh.delete();
	}

	public static float barryCentricInterpolation(Vector3f p1, Vector3f p2, Vector3f p3, Vector2f pos) {
		float det = (p2.x() - p3.z()) * (p1.x() - p3.x()) + (p3.x() - p2.x()) * (p1.z() - p3.z());
		float l1 = ((p2.z() - p3.z()) * (pos.x() - p3.x()) + (p3.x() - p2.x()) * (pos.y() - p3.z())) / det;
		float l2 = ((p3.z() - p1.z()) * (pos.x() - p3.x()) + (p1.x() - p3.x()) * (pos.y() - p3.z())) / det;
		float l3 = 1.0f - l1 - l2;
		return l1 * p1.y() + l2 * p2.y() + l3 * p3.y();
	}
}
