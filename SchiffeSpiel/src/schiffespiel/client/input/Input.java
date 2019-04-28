package schiffespiel.client.input;

import static org.lwjgl.glfw.GLFW.GLFW_CURSOR;
import static org.lwjgl.glfw.GLFW.GLFW_CURSOR_HIDDEN;
import static org.lwjgl.glfw.GLFW.GLFW_CURSOR_NORMAL;
import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSetCursorPos;
import static org.lwjgl.glfw.GLFW.glfwSetCursorPosCallback;
import static org.lwjgl.glfw.GLFW.glfwSetScrollCallback;
import static org.lwjgl.glfw.GLFW.glfwSetInputMode;
import static org.lwjgl.glfw.GLFW.glfwSetKeyCallback;
import static org.lwjgl.glfw.GLFW.glfwSetMouseButtonCallback;
import static org.lwjgl.glfw.GLFW.glfwSetFramebufferSizeCallback;

import java.util.ArrayList;

import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;
import org.lwjgl.glfw.GLFWScrollCallback;

import schiffespiel.common.math.vec.Vec2f;

import org.lwjgl.glfw.GLFWFramebufferSizeCallback;

/**
 * 
 * @author Juhu1705
 *
 */
public class Input {
	
	/**
	 * Die Generelle Instanz dieser Klasse die alles registriert.
	 */
	private static Input instance = null;

	/**
	 * Stellt alle Keyboard Aktionen zum abrufen bereit.
	 */
	private ArrayList<Integer> pushedKeys = new ArrayList<Integer>(), keysHolding = new ArrayList<Integer>(), releasedKeys = new ArrayList<Integer>();
	
	/**
	 * Stellt alle Maus Aktionen zum abrufen bereit.
	 */
	private ArrayList<Integer> pushedButtons = new ArrayList<Integer>(), buttonsHolding = new ArrayList<Integer>(), releasedButtons = new ArrayList<Integer>();
	
	/**
	 * Hält die Maus-Position fest.
	 */
	private Vec2f cursorPosition, lockedCursorPosition;
	
	/**
	 * Hält die Stellung des Mausrades fest.
	 */
	private float scrollOffset;
	
	private boolean pause = false;
	
	private GLFWKeyCallback keyCallback;
	
	private GLFWCursorPosCallback cursorPosCallback;
	
	private GLFWMouseButtonCallback mouseButtonCallback;

	private GLFWScrollCallback scrollCallback;
	
	private GLFWFramebufferSizeCallback framebufferSizeCallback;
	
	/**
	 * 
	 * @return Ein zu nutzende Instanz dieser Klasse
	 */
	public static Input getInstance() {
		return Input.instance == null ? Input.instance = new Input() : instance;
	}
	
	protected Input()
	{
		cursorPosition = new Vec2f();
		
		//Ermöglicht den Zugriff auf die Bildschirmgröße und seine Varianzänderung.
		glfwSetFramebufferSizeCallback(Window.getInstance().getWindow(), (framebufferSizeCallback = new GLFWFramebufferSizeCallback() {
		    @Override
		    public void invoke(long window, int width, int height) {
		        Window.getInstance().setSize(width, height);
		    }
		}));
		
		//Erfasst die Aktionen der Tastatur und hält speichert sie zur abrufbarkeit in den entsprechenden Listen ab.
		glfwSetKeyCallback(Window.getInstance().getWindow(), (keyCallback = new GLFWKeyCallback() {

            @Override
            public void invoke(long window, int key, int scancode, int action, int mods) {
            	if (action == GLFW_PRESS){
            		if (!pushedKeys.contains(key)){
            			pushedKeys.add(key);
            			keysHolding.add(key);
            		}
                }
            	
                if (action == GLFW_RELEASE){
                	keysHolding.remove((Integer) key);
                	releasedKeys.add(key);
                }
            }
        }));
		
		glfwSetMouseButtonCallback(Window.getInstance().getWindow(), (mouseButtonCallback = new GLFWMouseButtonCallback() {

            @Override
            public void invoke(long window, int button, int action, int mods) {
                if (action == GLFW_PRESS){
                	if (!pushedButtons.contains(button)){
                		pushedButtons.add(button);
                		buttonsHolding.add(button);
                	}
                }
                
                if (action == GLFW_RELEASE){
                	releasedButtons.add(button);
                	buttonsHolding.remove((Integer) button);
                }
            }
		}));
		
		glfwSetCursorPosCallback(Window.getInstance().getWindow(), (cursorPosCallback = new GLFWCursorPosCallback() {

            @Override
            public void invoke(long window, double xpos, double ypos) {
            	cursorPosition.x = (float) xpos;
            	cursorPosition.y = (float) ypos;
            }

		}));
		
		glfwSetScrollCallback(Window.getInstance().getWindow(), (scrollCallback = new GLFWScrollCallback() {
			
			@Override
			public void invoke(long window, double xoffset, double yoffset) {
				setScrollOffset((float) yoffset);
			}
		}));
	}
	
	/**
	 * Überprüft auf neue Eingabeaktionen
	 */
	public void update() {
		setScrollOffset(0);
		pushedKeys.clear();
		releasedKeys.clear();
		pushedButtons.clear();
		releasedButtons.clear();
		
		glfwPollEvents();
	}
	
	/**
	 * Überprüft, ob die eingegebene Taste gedrückt wird.
	 * @param key Die zu überprüfende Taste. Alle zu finden unter {@link org.lwjgl.glfw.GLFW}
	 * @return Ob sie gedrückt ist
	 */
	public boolean isKeyPushed(int key)	{
		
		return pushedKeys.contains(key);
	}
	
	/**
	 * Überprüft, ob die eingegebene Taste losgelassen wurde.
	 * @param key Die zu überprüfende Taste. Alle zu finden unter {@link org.lwjgl.glfw.GLFW}
	 * @return Ob sie losgelassen wurde
	 */
	public boolean isKeyReleased(int key)	{
		return releasedKeys.contains(key);
	}
	
	/**
	 * Überprüft, ob die eingegebene Taste gehalten wird.
	 * @param key Die zu überprüfende Taste. Alle zu finden unter {@link org.lwjgl.glfw.GLFW}
	 * @return Ob sie gehalten ist
	 */
	public boolean isKeyHold(int key)	{
		return keysHolding.contains(key);
	}
	
	/**
	 * Überprüft, ob der eingegebene Mausknopf gedrückt wird.
	 * @param key Der zu überprüfende Mausknopf. Alle zu finden unter {@link org.lwjgl.glfw.GLFW}
	 * @return Ob er gedrückt ist
	 */
	public boolean isButtonPushed(int key)	{
		return pushedButtons.contains(key);
	}
	
	/**
	 * Überprüft, ob der eingegebene Mausknopf losgelassen wurde.
	 * @param key Der zu überprüfende Mausknopf. Alle zu finden unter {@link org.lwjgl.glfw.GLFW}
	 * @return Ob er losgelassen wurde
	 */
	public boolean isButtonreleased(int key)	{
		return releasedButtons.contains(key);
	}
	
	/**
	 * Überprüft, ob der eingegebene Mausknopf gehalten wird.
	 * @param key Der zu überprüfende Mausknopf. Alle zu finden unter {@link org.lwjgl.glfw.GLFW}
	 * @return Ob er gehalten ist
	 */
	public boolean isButtonHolding(int key)	{
		return buttonsHolding.contains(key);
	}

	public boolean isPause() {
		return pause;
	}

	public void setPause(boolean pause) {
		this.pause = pause;
	}
	
	public Vec2f getCursorPosition() {
		return cursorPosition;
	}

	private void setCursorPosition(Vec2f cursorPosition) {
		this.cursorPosition = cursorPosition;
		
		glfwSetCursorPos(Window.getInstance().getWindow(),
				cursorPosition.x,
				cursorPosition.y);
	}

	public Vec2f getLockedCursorPosition() {
		return lockedCursorPosition;
	}

	public void setLockedCursorPosition(Vec2f lockedCursorPosition) {
		this.lockedCursorPosition = lockedCursorPosition;
	}
	
	public ArrayList<Integer> getPushedKeys() {
		return pushedKeys;
	}

	public ArrayList<Integer> getButtonsHolding() {
		return buttonsHolding;
	}

	public float getScrollOffset() {
		return scrollOffset;
	}

	public void setScrollOffset(float scrollOffset) {
		this.scrollOffset = scrollOffset;
	}

	public ArrayList<Integer> getKeysHolding() {
		return keysHolding;
	}

	public ArrayList<Integer> getPushedButtons() {
		return pushedButtons;
	}
}
