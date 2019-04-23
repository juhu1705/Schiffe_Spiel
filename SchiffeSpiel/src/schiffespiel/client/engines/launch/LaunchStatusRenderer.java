package schiffespiel.client.engines.launch;

import static org.lwjgl.glfw.GLFW.glfwTerminate;

import schiffespiel.application.gui.Window;

public class LaunchStatusRenderer implements Runnable {

	private static float fps;
	private static float framerate = 200, frameTime = 1.0f / framerate;
	private boolean isRunning;
	
	@Override
	public void run() {
		this.isRunning = true;
		
		int frames = 0;
		long frameCounter = 0, lastTime = System.nanoTime();
		double unprocessedTime = 0;
		
		//GameLoop for Rendering
		while(this.isRunning) {
			boolean render = false;
			
			long startTime = System.nanoTime(), passedTime = startTime - lastTime;
			lastTime = startTime;
			
			unprocessedTime += passedTime / (double) 1000000000; //TODO Constants.nanoseconds
			frameCounter += passedTime;
			
			while(unprocessedTime > frameTime) {
				render = true;
				unprocessedTime -= frameTime;
				
				if(Window.getInstance().isCloseRequested()) this.stop();
				
				this.update();
				
				if(frameCounter >= 1000000000) { //TODO Constants.nanoseconds
					fps = frames;
					frames = 0;
					frameCounter = 0;
				}
			}
			
			if(render) {
				this.render();
				frames++;
			} else {
				try {
					Thread.sleep(10);
				} catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private void stop() {
		this.isRunning = !this.isRunning ? isRunning : false;
	}
	
	private void render() {
		
	}
	
	private void update() {
		//TODO Input.getInstance().update();
	}
	
	private void close() {
		Window.getInstance().dispose();
		glfwTerminate();
	}

}
