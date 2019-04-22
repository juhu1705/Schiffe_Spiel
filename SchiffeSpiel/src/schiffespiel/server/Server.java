package schiffespiel.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

import schiffespiel.common.util.Ref;
import schiffespiel.server.net.ClientHandler;

/**
 * Enthält essenzielle Funktionen des Servers
 * @author Niklas
 */
public class Server {
	
	private static List<ClientHandler> clientHandlers;
	private static ServerSocket serverSocket;
	
	
	public void startServer() throws IOException {
		Server.clientHandlers = new ArrayList<ClientHandler>();
		Server.serverSocket = new ServerSocket(Ref.STANDARD_HOST_PORT);
	}
	
	
	/**
	 * @return eine Kopie der {@link ClientHandler}-Liste
	 * @see #getClientHandlerList()
	 */
	public static List<ClientHandler> getClientHandlers() {
		return new ArrayList<ClientHandler>(Server.clientHandlers);
	}
	
	
	/**
	 * @return modifizierbare Liste der {@link ClientHandler}
	 * @see #getClientHandlers()
	 */
	public static List<ClientHandler> getClientHandlerList() {
		return Server.clientHandlers;
	}
	
	
	public static ServerSocket getServerSocket() {
		return Server.serverSocket;
	}
}
