package schiffespiel.client.net;

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

/**
 * Behandelt die Verbindung zum Server
 * @author Niklas
 */
public class ConnectionHandler implements Runnable, DatapacketSender {

	private final Socket serverSocket;
	private final String hostIp;
	private final ObjectOutputStream objectOut;
	private final ObjectInputStream objectIn;
	private final Thread thread;
	
	
	private ConnectionHandler(String hostIp) throws IOException {
		this.hostIp = hostIp;
		this.serverSocket = new Socket(hostIp, Ref.STANDARD_HOST_PORT);
		this.objectOut = new ObjectOutputStream(getServerSocket().getOutputStream());
		this.objectOut.flush();
		this.objectIn = new ObjectInputStream(getServerSocket().getInputStream());
		this.thread = new Thread(this);
		this.thread.start();
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
			if (dp.getType().getSenderSide() != Side.SERVER) {
				Ref.LOGGER.warning("Nicht-Serverseitiges Datenpaket empfangen, wird ignoriert!");
				continue;
			}
			
			NetEventDistributor.addEventToQueue(new NetEvent(this, dp));
		}
	}
	
	
	/**
	 * Sendet ein Datenpaket zum Server
	 * 
	 * @param dp zu sendendes Datenpaket
	 * @throws IOException falls das Senden fehlschlägt
	 */
	public void sendDatapacket(Datapacket dp) throws IOException {
		dp.send(this.objectOut);
	}


	public Socket getServerSocket() {
		return this.serverSocket;
	}


	public String getHostIp() {
		return this.hostIp;
	}
	
}
