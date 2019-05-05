package schiffespiel.client.engines.launch;

import static org.lwjgl.glfw.GLFW.glfwTerminate;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static schiffespiel.common.util.Constants.NANOSECONDS;

import schiffespiel.client.input.Input;
import schiffespiel.client.input.Window;

public class LaunchStatusRenderer {

	private static float fps;
	private static float framerate = 200, frameTime = 1.0f / framerate;
	private boolean isRunning;

	public void run() {
		this.isRunning = true;

		int frames = 0;
		long frameCounter = 0, lastTime = System.nanoTime();
		double unprocessedTime = 0;

		this.init();

		// GameLoop for Rendering
		while (this.isRunning) {
			boolean render = false;

			long startTime = System.nanoTime(), passedTime = startTime - lastTime;
			lastTime = startTime;

			unprocessedTime += passedTime / (double) NANOSECONDS;
			frameCounter += passedTime;

			while (unprocessedTime > frameTime) {
				render = true;
				unprocessedTime -= frameTime;

				if (Window.getInstance().isCloseRequested())
					this.stop();

				this.update();

				if (frameCounter >= NANOSECONDS) {
					fps = frames;
					frames = 0;
					frameCounter = 0;
				}
			}

			if (render) {
				this.render();
				frames++;
			} else {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		this.close();
	}

	public void stop() {
		this.isRunning = false;
	}

	private void init() {
		glClearColor(0, 0.3f, 0.9f, 1.0f);
	}

	private void render() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

		Window.getInstance().render();
	}

	private void update() {
		Input.getInstance().update();
	}

	private void close() {
		Window.getInstance().dispose();
		glfwTerminate();
	}

}
