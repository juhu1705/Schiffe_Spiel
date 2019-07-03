package schiffespiel.client.interfaces;

import schiffespiel.client.engines.game.entity.Mesh;
import schiffespiel.client.engines.game.entity.Shader;

public interface IModel {

	public Mesh getMesh();

	public Shader getShader();

}