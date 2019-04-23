package schiffespiel.application;

import schiffespiel.common.net.datapackets.Datapacket;
import schiffespiel.common.net.datapackets.DatapacketSender;
import schiffespiel.common.net.datapackets.DatapacketType;
import schiffespiel.common.net.datapackets.NetEvent;
import schiffespiel.common.net.datapackets.NetEventDistributor;
import schiffespiel.common.net.datapackets.NetEventHandler;
import schiffespiel.common.util.Ref;

public class Main {
	public static void main(String[] args) {
		Ref.LOGGER.info("Die Main-Klasse wurde ausgeführt. Wow.");
		
		NetEventDistributor.registerEventHandlers(Main.class);
		NetEventDistributor.startProcessing();
		NetEventDistributor.addEventToQueue(new NetEvent(null, new Datapacket(DatapacketType.TEST, "Ta-da!")));
		NetEventDistributor.stopProcessing();
	}
	
	@NetEventHandler(type=DatapacketType.TEST)
	public static void onTest(String value, DatapacketSender sender) {
		Ref.LOGGER.info(value);
		throw new IllegalArgumentException(":)");
	}
}
