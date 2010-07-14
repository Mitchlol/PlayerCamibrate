package camibrate.imgfileloader;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Vector;

import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import camibrate.gui.blobcreator.BlobCreatorData;
import camibrate.gui.blobcreator.BlobCreatorFrame;

import com.sun.image.codec.jpeg.*;



public class ImageFileLoader {

	public static void main(String[] args) {
		File mFile = getImgFileFromArgs(args);
		RandomAccessFile raf = openFile(mFile);
		//BufferedReader br = openFile(mFile);
		//BufferedReader br2 = openFile(mFile);
		File[] rgbImgFiles = null;
		File[] yuvImgFiles = null;
		try {
			rgbImgFiles = getRGBFiles(raf);
			yuvImgFiles = getYUVFiles(raf);
			//System.out.println(rgbImgFiles.length);
			//System.out.println(yuvImgFiles.length);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(rgbImgFiles == null && yuvImgFiles == null){
			System.out.println("No images found in config file, use a config with images!");
			System.exit(1);
		}else if(rgbImgFiles != null && yuvImgFiles == null){
			System.out.println(rgbImgFiles.length + " RGB images found, no yuv!");
		
			Vector<BufferedImage> rgbImages = getImagesFromFiles(rgbImgFiles);
			System.out.println(rgbImages.size() + " RGB actualy read...");
			BlobCreatorData data = new BlobCreatorData(rgbImages);
			new BlobCreatorFrame(data).setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
		}else if(rgbImgFiles == null && yuvImgFiles != null){
			System.out.println(yuvImgFiles.length + " YUV images found, no RGB!");
			
			System.out.println("This is unimplemented cus mitch is lazy");
		}else if(rgbImgFiles.length == yuvImgFiles.length){
			System.out.println(rgbImgFiles.length + " RGB & YUV images found.");
			
			Vector<BufferedImage> rgbImages = getImagesFromFiles(rgbImgFiles);
			System.out.println(rgbImages.size() + " RGB actualy read...");
			Vector<BufferedImage> yuvImages = getImagesFromFiles(yuvImgFiles);
			System.out.println(yuvImages.size() + " YUV actualy read...");
			BlobCreatorData data = new BlobCreatorData(rgbImages,yuvImages);
			new BlobCreatorFrame(data).setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}else{
			System.out.println("Uneven ammount of pictures, quiting....");
			System.exit(0);
		}
		
		
		

	}
	
	
	
	public static File getImgFileFromArgs(String[] args){
		if(args.length == 0){
			System.out.println("No arguments found, use \"java -jar camibrate.jar ImgFile.txt\".");
			System.exit(1);
		}
		System.out.println("Using imgfile \""+args[0]+"\".");
		return new File(args[0]);
		
	}
	
	public static RandomAccessFile openFile(File file){
		try {
			RandomAccessFile raf = new RandomAccessFile(file,"r");
			System.out.println("file found, and opened!");
			return raf;
		} catch (FileNotFoundException e) {
			//e.printStackTrace();
			System.out.println("File not found! please use a file that actualy exists.....       MORON!");
			System.exit(1);
		}
		//stupid compiler
		return null;
	}
	/*
	public static BufferedReader openFile(File mFile){
		FileReader fr;
		try {
			fr = new FileReader(mFile);
			System.out.print("file found... ");
			BufferedReader br = new BufferedReader(fr);
			System.out.println("file opened... ");
			return br;
		} catch (FileNotFoundException e) {
			//e.printStackTrace();
			System.out.println("File not found! please use a file that actualy exists.....       MORON!");
			System.exit(1);
		}
		//stupid compiler >.<
		return null;
	}
	*/
	public static File[] getRGBFiles(RandomAccessFile raf) throws IOException{
		String line;
		Boolean inCorrectSection = false;
		Vector<String> rgbFiles = new Vector<String>();
		raf.seek(0);
		while((line = raf.readLine())!= null){
			if(!line.startsWith("#")){
				
				if(line.equals("[RGB]")){
					inCorrectSection = true;
				}else if(line.equals("[YUV]")){
					inCorrectSection = false;
				}else if(inCorrectSection && !line.equals("")){
					//System.out.println("RGB file = " + line);
					rgbFiles.add(line);
				}
				
				
			}else{
				//ignore comment
			}
			
		}
		if(rgbFiles.size() > 0){
			File[] files = new File[rgbFiles.size()];
			for(int i = 0; i < rgbFiles.size(); i++){
				files[i] = new File(rgbFiles.get(i));
			}
			return files;
		}else{
			return null;
		}
	}
	
	public static File[] getYUVFiles(RandomAccessFile raf) throws IOException{
		String line;
		Boolean inCorrectSection = false;
		Vector<String> yuvFiles = new Vector<String>();
		raf.seek(0);
		while((line = raf.readLine())!= null){
			if(!line.startsWith("#")){
				
				if(line.equals("[YUV]")){
					inCorrectSection = true;
				}else if(line.equals("[RGB]")){
					inCorrectSection = false;
				}else if(inCorrectSection && !line.equals("")){
					//System.out.println("YUV file = " + line);
					yuvFiles.add(line);
				}
				
				
			}else{
				//ignore comment
			}
			
		}
		if(yuvFiles.size() > 0){
			File[] files = new File[yuvFiles.size()];
			for(int i = 0; i < yuvFiles.size(); i++){
				files[i] = new File(yuvFiles.get(i));
			}
			return files;
		}else{
			return null;
		}
	}
	
	public static Vector<BufferedImage> getImagesFromFiles(File[] files){
		Vector<BufferedImage> images = new Vector<BufferedImage>();
		for(int i = 0; i < files.length; i++){
			System.out.println("in for " + i+ "  omg  "+files.length);
			try {
				images.add(ImageIO.read(files[i]));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		
		return images;
	}
	
	

}