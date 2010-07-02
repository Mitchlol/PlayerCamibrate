package camibrate.gui.blobcreator;

import java.awt.image.BufferedImage;
import java.util.Vector;

import camibrate.CamibrateBlob;

public class BlobCreatorData {

	Vector<BufferedImage> images;
	Vector<CamibrateBlob> blobs;
	int imageAt;
	int blobAt;
	
	public BlobCreatorData(){
		images = new Vector<BufferedImage>();
		int imageAt = -1;
		blobs = new Vector<CamibrateBlob>();
	}
	
	public BlobCreatorData(Vector<BufferedImage> images){
		this.images = images;
		if(images.size() > 0){
			imageAt = 0;
		}else{
			imageAt = -1;
		}
		blobs = new Vector<CamibrateBlob>();
		int blobAt = -1;
	}
	
	public BlobCreatorData(Vector<BufferedImage> images, Vector<CamibrateBlob> blobs){
		this.images = images;
		if(images.size() > 0){
			imageAt = 0;
		}else{
			imageAt = -1;
		}
		this.blobs = blobs;
		if(blobs.size() > 0){
			blobAt = 0;
		}else{
			blobAt = -1;
		}
	}
	
	public BufferedImage getCurrentImage(){
		if(imageAt == -1){
			return null;
		}else{
			return images.get(imageAt);
		}
	}
	
	public Vector<BufferedImage> getImages(){
		return images;
	}
	
	public boolean nextImage(){
		if(imageAt < images.size()-1){
			imageAt ++;
			return true;
		}else{
			return false;
		}
	}
	
	public boolean previousImage(){
		if(imageAt > 0){
			imageAt--;
			return true;
		}else{
			return false;
		}
	}
	
	public boolean setImageAt(int imageAt){
		if(imageAt >= 0 && imageAt < images.size()){
			this.imageAt = imageAt;
			return true;
		}else{
			return false;
		}
	}
	
	public CamibrateBlob getCurrentBlob(){
		if(blobAt == -1){
			return null;
		}else{
			return blobs.get(blobAt);
		}
	}
	
	public Vector<CamibrateBlob> getBlobs(){
		return blobs;
	}
	
	public int addBlob(CamibrateBlob blob){
		blobs.add(blob);
		return blobs.indexOf(blob);
	}
	
	public void setBlobAt(int blobAt){
		this.blobAt = blobAt;
	}
	
	public void deleteBlob(int position){
		blobs.remove(position);
			
		if(blobs.size() > 0){
			blobAt = 0;
		}else{
			blobAt = -1;
		}
	}
}
