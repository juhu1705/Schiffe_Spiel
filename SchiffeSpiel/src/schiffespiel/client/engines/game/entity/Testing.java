package schiffespiel.client.engines.game.entity;

public class Testing {

	/**
	 * Creates a standart test-entity
	 * 
	 * @return standart Entity Cube
	 */
	public static Entity getStandatTest() {
		return new Entity(Shaders.BASIC_ENTITY, null,
				new Mesh(
						new int[] { 0, 1, 3, 3, 1, 2, 4, 5, 7, 7, 5, 6, 8, 9, 11, 11, 9, 10, 12, 13, 15, 15, 13, 14, 16,
								17, 19, 19, 17, 18, 20, 21, 23, 23, 21, 22 },
						new float[] { -0.5f, 0.5f, -0.5f, -0.5f, -0.5f, -0.5f, 0.5f, -0.5f, -0.5f, 0.5f, 0.5f, -0.5f,
								-0.5f, 0.5f, 0.5f, -0.5f, -0.5f, 0.5f, 0.5f, -0.5f, 0.5f, 0.5f, 0.5f, 0.5f,

								0.5f, 0.5f, -0.5f, 0.5f, -0.5f, -0.5f, 0.5f, -0.5f, 0.5f, 0.5f, 0.5f, 0.5f,

								-0.5f, 0.5f, -0.5f, -0.5f, -0.5f, -0.5f, -0.5f, -0.5f, 0.5f, -0.5f, 0.5f, 0.5f,

								-0.5f, 0.5f, 0.5f, -0.5f, 0.5f, -0.5f, 0.5f, 0.5f, -0.5f, 0.5f, 0.5f, 0.5f,

								-0.5f, -0.5f, 0.5f, -0.5f, -0.5f, -0.5f, 0.5f, -0.5f, -0.5f, 0.5f, -0.5f, 0.5f })) {
			@Override
			public void update() {
				super.transform.getRotation().x += 0.1f;
				super.transform.getRotation().y += 0.1f;
				super.transform.getRotation().z += 0.1f;
				super.update();
			}
		};
	}

}
