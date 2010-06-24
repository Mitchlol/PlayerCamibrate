package camibrate.playerclient;

import javaclient3.BlobfinderInterface;
import javaclient3.CameraInterface;
import javaclient3.PlayerClient;
import javaclient3.PlayerException;
import javaclient3.structures.PlayerConstants;
import camibrate.gui.camibrateviewer.CamibrateFrame;

public class Client {
	//player stuff
	static PlayerClient			client = null;
	static CameraInterface		camera = null;
	static BlobfinderInterface	blobfinder = null;
	
	//gui stuff
	static CamibrateFrame myFrame = null;
	
	//my stuff
	static CamibrateRobot robot = null;
	
	public static void main(String[] args) {
		
		try {
			client = new PlayerClient                 ("localhost", 6665);
			camera  = client.requestInterfaceCamera(0, PlayerConstants.PLAYER_OPEN_MODE);
			blobfinder = client.requestInterfaceBlobfinder(0, PlayerConstants.PLAYER_OPEN_MODE);
			
		} catch (PlayerException e) {
			System.err.println ("Javaclient test: > Error connecting to Player: ");
			System.err.println ("    [ " + e.toString() + " ]");
			System.exit (1);
		}
		client.runThreaded (-1, -1);
		client.readAll ();
		while(!camera.isDataReady()){
        	
        }
        
		robot = new CamibrateRobot(client,camera,blobfinder);
		
		myFrame = new CamibrateFrame(robot);
		myFrame.setVisible(true);
		
		
	}

}
