package schiffespiel.application;

import schiffespiel.application.gui.Launch;
import schiffespiel.client.input.Input;
import schiffespiel.common.util.Ref;

public class Main {
	public static void main(String[] args) {
		new Thread(new Launch()).start();
	}
}
