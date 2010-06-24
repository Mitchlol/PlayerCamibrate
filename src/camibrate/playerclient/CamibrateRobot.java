package camibrate.playerclient;

import java.awt.image.BufferedImage;
import java.util.Vector;

import javaclient3.BlobfinderInterface;
import javaclient3.CameraInterface;
import javaclient3.PlayerClient;

public class CamibrateRobot {
	public PlayerClient			client = null;
	public CameraInterface		camera = null;
	public BlobfinderInterface	blobfinder = null;
	
	public Vector<BufferedImage> capturedImages = null;
	
	CamibrateRobot(PlayerClient client, CameraInterface camera, BlobfinderInterface blobfinder){
		this.client = client;
		this.camera = camera;
		this.blobfinder = blobfinder;
		
		capturedImages = new Vector<BufferedImage>();
	}
}
