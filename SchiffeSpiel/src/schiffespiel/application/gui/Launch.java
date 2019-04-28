package schiffespiel.application.gui;

import static org.lwjgl.glfw.GLFW.glfwSetErrorCallback;
import static org.lwjgl.glfw.GLFWErrorCallback.createPrint;
import static org.lwjgl.glfw.GLFW.glfwInit;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL31;
import org.lwjgl.opengl.GL40;
import org.lwjgl.opengl.GL43;

import schiffespiel.client.engines.launch.LaunchStatusRenderer;
import schiffespiel.client.input.Window;
import schiffespiel.common.util.Ref;

public class Launch implements Runnable {

	public static GLFWErrorCallback errorCallback;
	
	public static int status;
	
	public void createWindow(int width, int height) {
		glfwSetErrorCallback(errorCallback = createPrint(System.err));
		glfwInit();
		Window.getInstance().create(width, height);
		
		this.getDeviceProperties();
		
		new LaunchStatusRenderer().run();
	}
	
	
	
	private void getDeviceProperties(){
		Ref.LOGGER.info("OpenGL version: " + GL11.glGetString(GL11.GL_VERSION) + " bytes");
		Ref.LOGGER.info("Max Geometry Uniform Blocks: " + GL31.GL_MAX_GEOMETRY_UNIFORM_BLOCKS+ " bytes");
		Ref.LOGGER.info("Max Geometry Shader Invocations: " + GL40.GL_MAX_GEOMETRY_SHADER_INVOCATIONS + " bytes");
		Ref.LOGGER.info("Max Uniform Buffer Bindings: " + GL31.GL_MAX_UNIFORM_BUFFER_BINDINGS + " bytes");
		Ref.LOGGER.info("Max Uniform Block Size: " + GL31.GL_MAX_UNIFORM_BLOCK_SIZE + " bytes");
		Ref.LOGGER.info("Max SSBO Block Size: " + GL43.GL_MAX_SHADER_STORAGE_BLOCK_SIZE + " bytes");
	}



	@Override
	public void run() {
		Launch launch = new Launch();
		launch.createWindow(800, 800);
	}
	
	
	
}
