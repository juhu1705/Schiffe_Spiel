package schiffespiel.server.net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import schiffespiel.common.net.Side;
import schiffespiel.common.net.datapackets.Datapacket;
import schiffespiel.common.net.datapackets.DatapacketSender;
import schiffespiel.common.net.datapackets.NetEvent;
import schiffespiel.common.net.datapackets.NetEventDistributor;
import schiffespiel.common.util.Ref;
import schiffespiel.server.Server;

/**
 * Behandelt die serverseitige Verbindung zum Client
 * @author Niklas
 */
public class ClientHandler implements Runnable, DatapacketSender {
	
	private final Socket clientSocket;
	private final ObjectInputStream objectIn;
	private final ObjectOutputStream objectOut;
	private final Thread thread;
	
	
	private ClientHandler(Socket clientSocket) throws IOException {
		this.clientSocket = clientSocket;
		
		this.objectIn = new ObjectInputStream(this.clientSocket.getInputStream());
		this.objectOut = new ObjectOutputStream(this.clientSocket.getOutputStream());
		
		this.thread = new Thread(this);
		this.thread.start();
	}
	
	
	public static ClientHandler waitForClient() throws IOException {
		Socket cs = Server.getServerSocket().accept();
		return new ClientHandler(cs);
	}

	
	@Override
	public void run() {
		while (true) {
			Datapacket dp = null;
			try {
				dp = Datapacket.receive(this.objectIn);
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
			
			// ClientHandler entfernen, wenn Verbindung abgebrochen
			if (dp == null) {
				Server.getClientHandlerList().remove(this);
				return;
			}
			
			if (dp.getType().getSenderSide() != Side.CLIENT) {
				Ref.LOGGER.warning("Nicht-Clientseitiges Datenpaket empfangen, wird ignoriert!");
				continue;
			}
			
			NetEventDistributor.addEventToQueue(new NetEvent(this, dp));
		}
	}


	public ObjectInputStream getObjectInStream() {
		return this.objectIn;
	}


	public ObjectOutputStream getObjectOutStream() {
		return this.objectOut;
	}
	
}
