package chess.unideb.hu.maven;

import java.io.File;

import javax.swing.SwingUtilities;

import org.slf4j.*;

/**
 * 
 * @author gergo0720
 * Main class of the game.
 */
public class GameMain {

	/**
	 * Logger to debug, log information and warnings.
	 */
	private static Logger logger = LoggerFactory.getLogger(GameMain.class);
	
	/**
	 * Main method, create the base xml file and waiting two players.
	 * @param args is program parameter, it is not needed now.
	 */
	public static void main(String[] args) {
		saveXml.dir = new File("SavedGames");
		if(!saveXml.dir.exists()) {
			saveXml.dir.mkdir();
			saveXml.setBaseXml();
		}
		Players wc = new Players();
		
		try {
			while(wc.getIsOkay()){
				Thread.sleep(1000);
			}
			wc.setIsOkay(false);
			wc.dispose();
		} catch (Exception e) {
			logger.info("Failed to interrupt");
		}
		SwingUtilities.invokeLater(new Runnable() {
			
			public void run() {
				new GUIchess(new Game("base.xml"));
				if (Game.isPlayer())
					Game.setActivePlayer(Players.getUserName() + " 's ");
				else
					Game.setActivePlayer(Players.getUserName2() + " 's ");
				GUIchess.setLabelMessage(Game.getActivePlayer() + " round!");
			}
		});
			
	}

}
