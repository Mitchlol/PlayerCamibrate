package camibrate.playerclient;

import java.awt.image.BufferedImage;
import java.util.Vector;

import javaclient3.BlobfinderInterface;
import javaclient3.CameraInterface;
import javaclient3.PlayerClient;
import javaclient3.structures.blobfinder.PlayerBlobfinderBlob;
import javaclient3.structures.blobfinder.PlayerBlobfinderData;
import camibrate.StaticFunctions;

public class CamibrateRobot {
	public PlayerClient			client = null;
	public CameraInterface		camera = null;
	public BlobfinderInterface	blobfinder = null;
	
	public Vector<BufferedImage> capturedImages = null;
	public BufferedImage currentImage = null;
	public PlayerBlobfinderData blobData = null;
	
	public int compressionMode = -1;
	int cameraWidth = -1;
	int cameraHeight = -1;
	
	UpdateThread mUpdateThread;
	
	CamibrateRobot(PlayerClient client, CameraInterface camera, BlobfinderInterface blobfinder){
		this.client = client;
		this.camera = camera;
		this.blobfinder = blobfinder;
		
		capturedImages = new Vector<BufferedImage>();
		
		while(!camera.isDataReady()){
        	
	    }
		compressionMode = camera.getData().getCompression();
		cameraWidth = camera.getData().getWidth();
		cameraHeight = camera.getData().getHeight();
		
		currentImage = getImage();
		System.out.println("got first image, starting thread");
		mUpdateThread = new UpdateThread();
		mUpdateThread.start();
		System.out.println("thread started, auto-refreshing!");
	}
	
	public BufferedImage getImage(){
		while(!camera.isDataReady()){
        	
	    }
		BufferedImage image;
		if(compressionMode == 0){
			image = StaticFunctions.RGBToImage(cameraWidth,cameraHeight,camera.getData().getImage());
		}else if(compressionMode == 1){
			image = StaticFunctions.JPEGToImage(camera.getData().getImage());
		}else{
			image = null;
		}
		return image;
	}
	
	public void captureImage(){
		capturedImages.add(currentImage);
	}
	
	public void getBlobData(){
		while(!blobfinder.isDataReady()){
			
		}
		blobData = blobfinder.getData();
	}
	
	class UpdateThread extends Thread{
		public void run() {
			while(true){
				System.out.println("update");
				currentImage = getImage();
				getBlobData();
				super.run();
			}
		}
	}
}
