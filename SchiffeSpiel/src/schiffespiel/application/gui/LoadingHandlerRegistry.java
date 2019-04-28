package schiffespiel.application.gui;

import java.lang.reflect.Method;
import java.util.ArrayList;

import schiffespiel.application.annotations.LoadingHandler;

public class LoadingHandlerRegistry {

	private static ArrayList<Class<?>> loadingHandlers = new ArrayList<Class<?>>();
	
	private static ArrayList<Class<?>> clientLoadingHandlers = new ArrayList<Class<?>>();
	
	public static void registerLoadingHandlerClass(Class<?> c) {
		LoadingHandler annotation = c.getAnnotation(LoadingHandler.class);
		if(annotation == null) throw new IllegalArgumentException("Angebene Klasse hat keine loadingHandler Annotation!");
		boolean isRunnable = false;
		for(Method m: c.getMethods()) {
			isRunnable = isRunnable || m.getName().equals("register");
		}
		if(!isRunnable) throw new IllegalArgumentException("Angegebene Klasse Verfügt nicht über benötigte Methoden!");
		LoadingHandlerRegistry.loadingHandlers.add(c);
	}
}
