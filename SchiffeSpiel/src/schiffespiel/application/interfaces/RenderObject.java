package schiffespiel.application.interfaces;

public interface RenderObject {

	/**
	 * Wird jeden RenderTick aufgerufen, hier sollte das Objekt gemalt werden
	 */
	public default void render() {

	};

	/**
	 * Wird jeden GameTick aufgerufen, hier sollten Positionen und ähnliches
	 * erneuert werden.
	 */
	public default void update() {

	};

	/**
	 * Wird einmal am anfang des Spieles aufgerufen. Hier sollten die Objekte
	 * Initialisiert werden.
	 */
	public default void init() {

	};

	/**
	 * Wird am Ende des Spiels aufgerufen, hier sollten die Objekte gelöscht werden.
	 */
	public default void delete() {

	};
}
