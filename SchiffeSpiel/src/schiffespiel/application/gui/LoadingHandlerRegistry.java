package schiffespiel.application.gui;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import schiffespiel.application.annotations.LoadingHandler;
import schiffespiel.common.util.Ref;

public class LoadingHandlerRegistry {

	public static final ArrayList<Method> veryLow = new ArrayList<>();
	public static final ArrayList<Method> low = new ArrayList<>();
	public static final ArrayList<Method> medium = new ArrayList<>();
	public static final ArrayList<Method> normal = new ArrayList<>();
	public static final ArrayList<Method> high = new ArrayList<>();
	public static final ArrayList<Method> veryHigh = new ArrayList<>();
	public static final ArrayList<Method> extremelyHigh = new ArrayList<>();

	public static void registerLoadingHandlerMethod(Method m) {
		LoadingHandler annotation = m.getAnnotation(LoadingHandler.class);
		if (annotation == null)
			throw new IllegalArgumentException(
					"Angebene Methode hat keine loadingHandler Annotation und wird deshalb ignoriert!");
		if (m.getParameterCount() != 0)
			throw new IllegalArgumentException(
					"Angegebene Methode verlangt zu viele Parameter! Es können lediglich Methoden ohne Parameter verarbeitet werden! Die Methode wird daher ignoriert!");
		switch (annotation.priority()) {
		case EXTREMELYHIGH:
			LoadingHandlerRegistry.extremelyHigh.add(m);
			break;
		case HIGH:
			LoadingHandlerRegistry.high.add(m);
			break;
		case LOW:
			LoadingHandlerRegistry.low.add(m);
			break;
		case MEDIUM:
			LoadingHandlerRegistry.medium.add(m);
			break;
		case NORMAL:
			LoadingHandlerRegistry.normal.add(m);
			break;
		case VERYHIGH:
			LoadingHandlerRegistry.veryHigh.add(m);
			break;
		case VERYLOW:
			LoadingHandlerRegistry.veryLow.add(m);
			break;
		default:
			break;
		}
	}

	public static void registerLoadingHandlerClass(Class<?> c) {
		Method[] methods = c.getMethods();
		for (Method m : methods) {
			if (m.getAnnotation(LoadingHandler.class) != null)
				LoadingHandlerRegistry.registerLoadingHandlerMethod(m);
		}
		Ref.LOGGER.info("Added all registry Methods of " + c.getName() + " to the registry Handler.");
	}

	public static void startRegistry() {
		for (Method m : LoadingHandlerRegistry.extremelyHigh) {
			try {
				m.invoke(null);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}

		for (Method m : LoadingHandlerRegistry.veryHigh) {
			try {
				m.invoke(null);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}

		for (Method m : LoadingHandlerRegistry.high) {
			try {
				m.invoke(null);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}

		for (Method m : LoadingHandlerRegistry.normal) {
			try {
				m.invoke(null);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}

		for (Method m : LoadingHandlerRegistry.medium) {
			try {
				m.invoke(null);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}

		for (Method m : LoadingHandlerRegistry.low) {
			try {
				m.invoke(null);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}

		for (Method m : LoadingHandlerRegistry.veryLow) {
			try {
				m.invoke(null);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
	}
}
