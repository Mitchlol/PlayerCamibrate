package camibrate.gui.blobcreator;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Vector;

import camibrate.CamibrateBlob;
import camibrate.StaticFunctions;

public class BlobCreatorData {

	Vector<BufferedImage> rgbImages;
	Vector<BufferedImage> yuvImages;
	Vector<CamibrateBlob> blobs;
	int imageAt;
	int blobAt;
	
	public BlobCreatorData(){
		rgbImages = new Vector<BufferedImage>();
		int imageAt = -1;
		blobs = new Vector<CamibrateBlob>();
	}
	
	public BlobCreatorData(Vector<BufferedImage> rgbImages){
		this.rgbImages = rgbImages;
		if(rgbImages.size() > 0){
			imageAt = 0;
		}else{
			imageAt = -1;
		}
		blobs = new Vector<CamibrateBlob>();
		int blobAt = -1;
		
		yuvImages = new Vector<BufferedImage>();
		for(int i = 0; i < rgbImages.size(); i++){
			yuvImages.add(StaticFunctions.RGBImageToYUVImage(this.rgbImages.get(i)));
		}
	}
	
	public BlobCreatorData(Vector<BufferedImage> rgbImages, Vector<BufferedImage> yuvImages){
		this.rgbImages = rgbImages;
		this.yuvImages = yuvImages;
		if(rgbImages.size() != yuvImages.size()){
			System.out.println("images uneven in construcor.");
			System.exit(1);
		}
		if(rgbImages.size() > 0){
			imageAt = 0;
		}else{
			imageAt = -1;
		}
		blobs = new Vector<CamibrateBlob>();
		int blobAt = -1;
	}
/*	
	public BlobCreatorData(Vector<BufferedImage> rgbImages, Vector<CamibrateBlob> blobs){
		this.rgbImages = rgbImages;
		if(rgbImages.size() > 0){
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
		
		for(int i = 0; i < rgbImages.size(); i++){
			yuvImages.add(StaticFunctions.RGBImageToYUVImage(rgbImages.get(i)));
		}
	}
*/	
	public BufferedImage getCurrentRGBImage(){
		if(imageAt == -1){
			return null;
		}else{
			return rgbImages.get(imageAt);
		}
	}
	
	public BufferedImage getCurrentYUVImage(){
		if(imageAt == -1){
			return null;
		}else{
			return yuvImages.get(imageAt);
		}
	}
	
	public Vector<BufferedImage> getImages(){
		return rgbImages;
	}
	
	public boolean nextImage(){
		if(imageAt < rgbImages.size()-1){
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
		if(imageAt >= 0 && imageAt < rgbImages.size()){
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
	
	public void print(){
		for(int i = 0; i < blobs.size(); i++){
			System.out.println(blobs.get(i).getName() + ", " + blobs.get(i).getDisplayColor());
			blobs.get(i).getRGBRange().print();
			blobs.get(i).getYUVRange().print();
			System.out.println();
		}
	}
	
	public void exportCMVColorfile(String filename){
         try{
        	 FileOutputStream out;
             PrintStream p;
             out = new FileOutputStream(filename);
             p = new PrintStream( out );

             p.println("[Colors]");
             CamibrateBlob blob;
             Color c;
             for(int i = 0; i < blobs.size(); i++){
            	 blob = blobs.get(i);
            	 c = blob.getDisplayColor();
            	 p.print("("+c.getRed()+","+c.getGreen()+","+c.getBlue()+")");
            	 p.print(" 0 10 ");//not sure what this "0 10" is but its needed
            	 p.print(blob.getName());
            	 p.println();
             }
             
             p.println();//cmvision wont load file without this blank line
             p.println("[Thresholds]");
             for(int i = 0; i < blobs.size(); i++){
            	 blob = blobs.get(i);
            	 p.println(blob.getYUVRange());
             }

             p.close();
             out.close();
         }catch (Exception e){
             System.err.println ("Error writing to file");
         }
	}
}
