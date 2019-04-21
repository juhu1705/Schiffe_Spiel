package schiffespiel.common.net.datapackets;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;

import schiffespiel.common.util.Ref;

/**
 * Ermöglicht vereinfachte Behandlung von eintreffenden {@link Datapacket Datenpaketen}
 * Verwaltet {@link NetEventHandler} und die EventQueue
 * 
 * @author Niklas
 */
public final class NetEventDistributor {
	
	private static Map<Method, DatapacketType> eventHandlers = new HashMap<>();
	
	private static LinkedList<NetEvent> eventQueue = new LinkedList<>();
	
	private static Thread eventRunner;
	
	static {
		NetEventDistributor.eventRunner = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					if (!eventQueue.isEmpty()) {
						NetEvent event = eventQueue.removeFirst();
						
						try {
							NetEventDistributor.processEvent(event);
						} catch (Exception e) {
							Ref.LOGGER.log(Level.SEVERE, "Exception in EventHandler:", e);
						}
					}
				}
			}
		}, "eventRunner");
	}
	
	
	/**
	 * Nicht zu benutzender Dummy-Konstruktor, der den Default-Konstruktor verhindert
	 */
	private NetEventDistributor() {}
	
	
	/**
	 * Registriert alle mit {@link NetEventHandler} annotiierten Methoden der Klasse {@code eventListener}
	 * 
	 * @param eventListener Klasse, deren {@link NetEventHandler}-Methoden registriert werden sollen
	 * @see #registerEventHandler(Method)
	 */
	public static void registerEventHandlers(Class<?> eventListener) {
		for (Method m : eventListener.getMethods()) {
			if (m.getAnnotation(NetEventHandler.class) != null) {
				NetEventDistributor.registerEventHandler(m);
			}
		}
	}
	
	
	/**
	 * Registriert eine Methode als EventHandler. <br><br>
	 * 
	 * Diese muss folgende Bedingungen erfüllen:<br>
	 * - muss die Annotation {@link NetEventHandler} enthalten<br>
	 * - muss statisch sein<br>
	 * - muss exakt zwei Parameter enthalten:<br>
	 * <ol>
	 * <li>Ein Parameter, über den der Datenpaket-Wert ({@code value}) übergeben wird</li>
	 * <li>Ein Parameter, über den der {@link DatapacketSender} übergeben wird</li>
	 * </ol>
	 * 
	 * @param eventHandler EventHandler-Methode
	 * @throws IllegalArgumentException wenn eine der oben genannten Bedingungen nicht erfüllt ist
	 * @see #registerEventHandlers(Class)
	 */
	public static void registerEventHandler(Method eventHandler) {
		NetEventHandler annotation = eventHandler.getAnnotation(NetEventHandler.class);
		if (annotation == null) {
			throw new IllegalArgumentException("Angegebene Methode hat keine NetEventHandler-Annotation");
		}
		Class<?>[] paramTypes = eventHandler.getParameterTypes();
		DatapacketType type = annotation.type();
		
		if (!(paramTypes.length == 2 && paramTypes[0] == type.getRequiredValueType())) {
			throw new IllegalArgumentException(
					"Angegebene Methode muss genau zwei Parameter, einen vom Typ "
					+ type.getRequiredValueType().getName()
					+ " und einen vom Typ DatapacketSender enthalten");
		}
		
		if (!Modifier.isStatic(eventHandler.getModifiers())) {
			throw new IllegalArgumentException("Angegebene Methode muss statisch sein");
		}
		
		NetEventDistributor.eventHandlers.put(eventHandler, type);
	}
	
	
	/**
	 * Fügt ein {@link NetEvent} zur Warteschlange hinzu,
	 * sodass dieses vom eventRunner-Thread verarbeitet wird, sobald alle
	 * vorherigen {@link NetEvent NetEvents} verarbeitet wurden
	 * @param event das hinzuzufügende Event
	 */
	public static void addEventToQueue(NetEvent event) {
		// eventRunner starten, falls noch nicht gestartet
		if (!NetEventDistributor.eventRunner.isAlive()) NetEventDistributor.eventRunner.start();
		
		NetEventDistributor.eventQueue.addLast(event);
	}
	
	
	/**
	 * Ruft ein Datenpaket-Event auf
	 * 
	 * @param dp Datenpaket
	 * @param sender Datenpaketsender
	 */
	private static void processEvent(NetEvent event) {
		Datapacket dp = event.getDatapacket();
		
		for (Entry<Method, DatapacketType> e : NetEventDistributor.eventHandlers.entrySet()) {
			if (e.getValue() == dp.getType()) {
				try {
					e.getKey().invoke(null, dp.getType().getRequiredValueType().cast(dp.getValue()), event.getSender());
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException exc) {
					exc.printStackTrace();
				}
			}
		}
	}
	
}
