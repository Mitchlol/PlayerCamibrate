package camibrate.playerclient;

import javaclient3.BlobfinderInterface;
import javaclient3.CameraInterface;
import javaclient3.PlayerClient;
import javaclient3.PlayerException;
import javaclient3.structures.PlayerConstants;
import camibrate.gui.Strings;
import camibrate.gui.camibrateviewer.CamibrateFrame;
import camibrate.gui.popupwindowutil.MessageOutputPopupWindow;

public class Client {
	static final String ClientError = "[PlayerClient]: Error in PlayerClient init: java.net.ConnectException: Connection refused";
	static final String CameraError = "[PlayerClient] Requested device camera:0 is not in the device list.";
	static final String BlobFinderError = "[PlayerClient] Requested device blobfinder:0 is not in the device list.";
	
	
	//player stuff
	static PlayerClient			client = null;
	static CameraInterface		camera = null;
	static BlobfinderInterface	blobfinder = null;
	
	static boolean connected;
	
	//gui stuff
	static CamibrateFrame myFrame = null;
	
	//my stuff
	static CamibrateRobot robot = null;
	
	public static void main(String[] args) {
		/*
		try {
			client = new PlayerClient                 ("localhost", 6665);	
			try {
				camera  = client.requestInterfaceCamera(0, PlayerConstants.PLAYER_OPEN_MODE);
				try {
					blobfinder = client.requestInterfaceBlobfinder(0, PlayerConstants.PLAYER_OPEN_MODE);
				} catch (PlayerException e) {
					System.err.println ("Javaclient: > Error connecting to BlobFinder interface: ");
					System.err.println ("    [ " + e.toString() + " ]");
					MessageOutputPopupWindow errorPopup;
					errorPopup = new MessageOutputPopupWindow(Strings.ERROR,Strings.ERROR_NO_IMAGES);
					errorPopup.setVisible(true);
					//System.exit (1);
				}
			} catch (PlayerException e) {
				System.err.println ("Javaclient: > Error connecting to camera interface: ");
				System.err.println ("    [ " + e.toString() + " ]");
				MessageOutputPopupWindow errorPopup;
				errorPopup = new MessageOutputPopupWindow(Strings.ERROR,Strings.ERROR_NO_IMAGES);
				errorPopup.setVisible(true);
				//System.exit (1);
			}
		} catch (PlayerException e) {
			System.err.println ("Javaclient: > Error connecting to Player: ");
			System.err.println ("    [ " + e.toString() + " ]");
			MessageOutputPopupWindow errorPopup;
			errorPopup = new MessageOutputPopupWindow(Strings.ERROR,"Strings.ERROR_NO_IMAGES");
			errorPopup.setVisible(true);
			//System.exit (1);
		}
		*/
		connected = false;
		try {
			client = new PlayerClient                 ("localhost", 6665);	
			camera  = client.requestInterfaceCamera(0, PlayerConstants.PLAYER_OPEN_MODE);
			//runs without blobfinder
			connected = true;
			blobfinder = client.requestInterfaceBlobfinder(0, PlayerConstants.PLAYER_OPEN_MODE);
		} catch (PlayerException e) {;
			String error;
			if(e.getMessage().equals(ClientError)){
				//System.out.println("YAY player");
				error = Strings.CONNECTION_ERROR_CLIENT;
			}else 
			if(e.getMessage().equals(CameraError)){
				//System.out.println("YAY camera");
				error = Strings.CONNECTION_ERROR_CAMERA;
			}else
			if(e.getMessage().equals(BlobFinderError)){
				//System.out.println("YAY blob");
				error = Strings.CONNECTION_ERROR_BLOBFINDER;
			}else{
				//System.out.println("YAY unknown error");
				error = Strings.CONNECTION_ERROR_UNKNOWN;
			}
			MessageOutputPopupWindow errorPopup;
			errorPopup = new MessageOutputPopupWindow(Strings.CONNECTION_ERROR,error);
			errorPopup.setVisible(true);
			
			System.err.println ("Javaclient test: > Error connecting to Player: ");
			System.err.println ("    [ " + e.toString() + " ]");
			//System.exit (1);
		}
		
		if(connected) {
			client.runThreaded (-1, -1);
			client.readAll ();
			while(!camera.isDataReady()){
	        	
	        }
	        
			robot = new CamibrateRobot(client,camera,blobfinder);
			
			myFrame = new CamibrateFrame(robot);
			myFrame.setVisible(true);
		}
		
	}

}
