package schiffespiel.application.gui;

import static org.lwjgl.glfw.GLFW.GLFW_CONTEXT_VERSION_MAJOR;
import static org.lwjgl.glfw.GLFW.GLFW_CONTEXT_VERSION_MINOR;
import static org.lwjgl.glfw.GLFW.GLFW_CURSOR;
import static org.lwjgl.glfw.GLFW.GLFW_CURSOR_HIDDEN;
import static org.lwjgl.glfw.GLFW.GLFW_CURSOR_NORMAL;
import static org.lwjgl.glfw.GLFW.GLFW_OPENGL_CORE_PROFILE;
import static org.lwjgl.glfw.GLFW.GLFW_OPENGL_PROFILE;
import static org.lwjgl.glfw.GLFW.GLFW_RESIZABLE;
import static org.lwjgl.glfw.GLFW.glfwCreateCursor;
import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwDestroyWindow;
import static org.lwjgl.glfw.GLFW.glfwGetPrimaryMonitor;
import static org.lwjgl.glfw.GLFW.glfwGetVideoMode;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.glfw.GLFW.glfwSetCursor;
import static org.lwjgl.glfw.GLFW.glfwSetInputMode;
import static org.lwjgl.glfw.GLFW.glfwSetWindowIcon;
import static org.lwjgl.glfw.GLFW.glfwSetWindowSize;
import static org.lwjgl.glfw.GLFW.glfwShowWindow;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwSwapInterval;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;
import static org.lwjgl.opengl.GL11.GL_TRUE;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glViewport;

import java.nio.ByteBuffer;

import org.lwjgl.glfw.GLFWImage;
import org.lwjgl.opengl.GL;

import schiffespiel.common.util.ImageLoader;


public class Window {

private static Window instance;
	
	private long window, cursor;
	private int width, height;
	private boolean isFullScreen, isCursorHidden;
	
	public static Window getInstance() {
		return instance != null ? instance : (instance = new Window());
	}
	
	protected Window() {
		
	}
	
	public long getWindow() {
		return this.window;
	}
	
	public void setWindow(long window) {
		this.window = window;
	}
	
	public void init() {
		
	}
	
	public void setClearColor(float r, float g, float b, float alpha) {
        glClearColor(r, g, b, alpha);
    }
	
	public boolean isFullScreen() {
		return this.isFullScreen;
	}
	
	public int getWidth() {
		return !isFullScreen ? this.width : glfwGetVideoMode(glfwGetPrimaryMonitor()).width();
	}
	
	public int getHeight() {
		return !isFullScreen ? this.height : glfwGetVideoMode(glfwGetPrimaryMonitor()).height();
	}
	
	public void fullScreen() {
		this.isFullScreen = true;
		setWindowSize(getWidth(), getHeight());
	}
	
	public void resizeToNormal() {
		setSize(this.width, this.height);
	}
	
	public void setSize(int width, int height) {
		this.isFullScreen = false;
		setWindowSize(width, height);
	}
	
	public void create(int width, int height) {
		long startTime = System.nanoTime();
		this.width = width;
		this.height = height;
		this.isFullScreen = false;
		
		glfwWindowHint(GLFW_RESIZABLE, GL_TRUE); 
		glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 4); 
		glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3); 
		glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE); 
		
		window = glfwCreateWindow(width, height, "Five Swords", 0, 0);
		
		if(window == 0) {
		    throw new RuntimeException("Failed to create window");
		}
		
		ByteBuffer bufferedImage = ImageLoader.loadImageToByteBuffer("./resources/assets/textures/logo/logo.png");
		
		GLFWImage image = GLFWImage.malloc();
		
		image.set(16, 16, bufferedImage);
		
		GLFWImage.Buffer images = GLFWImage.malloc(1);
        images.put(0, image);
		
		glfwSetWindowIcon(window, images);
		
		glfwMakeContextCurrent(window);
		glfwSwapInterval(1);
		GL.createCapabilities();
		glfwShowWindow(window);
		glViewport(0, 0, width, height);
		
		ByteBuffer bufferedImage1 = ImageLoader.loadImageToByteBuffer("./resources/assets/textures/logo/cursor.png");
		
		GLFWImage image1 = GLFWImage.malloc();
		
		image1.set(32, 32, bufferedImage1);
		
		GLFWImage.Buffer images1 = GLFWImage.malloc(1);
        images1.put(0, image1);
		this.cursor = glfwCreateCursor(image1, 0, 0);
		glfwSetCursor(this.window, this.cursor);
		
		
		
		this.isCursorHidden = false;
		
		System.out.println("Create Window in " + (System.nanoTime() - startTime) + " nanoseconds.");
	}
	
	public void render() {
		glfwSwapBuffers(window);
	}
	
	public void dispose() {
		glfwDestroyWindow(window);
	}
	
	public boolean isCloseRequested() {
		return glfwWindowShouldClose(window);
	}
	
	public boolean isCursorShown() {
		return !this.isCursorHidden;
	}
	
	public boolean isCursorHidden() {
		return this.isCursorHidden;
	}
	
	public void setNoramlCursor() {
		this.isCursorHidden = false;
		glfwSetInputMode(window, GLFW_CURSOR, GLFW_CURSOR_NORMAL);
		glfwSetCursor(this.window, this.cursor);
	}
	
	public void hideCursor() {
		this.isCursorHidden = true;
		glfwSetInputMode(window, GLFW_CURSOR, GLFW_CURSOR_HIDDEN);
	}
	
	private void setWindowSize(int width, int height) {
		glfwSetWindowSize(window, width, height);
		glViewport(0, 0, width, height);
		glfwMakeContextCurrent(window);
		this.height = height;
		this.width = width;
	}
	
}
