package camibrate;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ComponentColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class StaticFunctions {
	public static BufferedImage RGBToImage(int w, int h, byte[] data) {
	    DataBuffer buffer = new DataBufferByte(data, w*h);
	 
	    int pixelStride = 3;// JP: it was 4; //assuming r, g, b, skip, r, g, b, skip...
	    int scanlineStride = 3*w; // JP: it was 4*w //no extra padding   
	    int[] bandOffsets = {0, 1, 2}; //r, g, b
	    WritableRaster raster = Raster.createInterleavedRaster(buffer, w, h, scanlineStride, pixelStride, bandOffsets, null);
	 
	    ColorSpace colorSpace = ColorSpace.getInstance(ColorSpace.CS_sRGB);
	    boolean hasAlpha = false;
	    boolean isAlphaPremultiplied = false;
	    int transparency = Transparency.OPAQUE;
	    int transferType = DataBuffer.TYPE_BYTE;
	    ColorModel colorModel = new ComponentColorModel(colorSpace, hasAlpha, isAlphaPremultiplied, transparency, transferType);
	 
	    return new BufferedImage(colorModel, raster, isAlphaPremultiplied, null);
	}
	
	public static BufferedImage JPEGToImage(byte[] data) {
		BufferedImage image = null;
		InputStream in = new ByteArrayInputStream(data);
		try {
			image = ImageIO.read(in);
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return image;
	}
	
	public static BufferedImage scaleImageToFit(BufferedImage image, int maxWidth, int maxHeight){
		int oldWidth = image.getWidth();
		int oldHeight = image.getHeight();
		int newWidth;
		int newHeight;
		//height fits based on max width scale then cale to max width
		if(maxHeight > oldHeight * ((double)maxWidth/oldWidth)){
			newWidth = maxWidth;
			newHeight = (int)(oldHeight * ((double)maxWidth/oldWidth));
		}else{
		//scale to max height
			newWidth = (int)(oldWidth * ((double)maxHeight/oldHeight));
			newHeight = maxHeight;
		} 
		//get scaled instance is from Image class so it returns an Image
		Image iImage = image.getScaledInstance(newWidth, newHeight, Image.SCALE_DEFAULT);
		//to convert an Image to a BufferedImage you need to create a graphics and draw into it, lame....
		int w = iImage.getWidth(null), h = iImage.getHeight(null);  
		int type = BufferedImage.TYPE_INT_RGB;  // many options  
		BufferedImage bImage = new BufferedImage(w, h, type);  
		Graphics2D g2 = bImage.createGraphics();  
		g2.drawImage(iImage, 0, 0, null); // DID YOU MEAN "src" OR "sc"??  
		g2.dispose();
		
		return bImage;
	}
	
	public static double getScaleFactor(int width,int  height, int maxWidth, int maxHeight){
		//if height fits based on max width scale then scale to max width
		if(maxHeight > height * ((double)maxWidth/width)){
			return (double)maxWidth/width;
		}else{
		//scale to max height
			return (double)maxHeight/height;
		}
	}
	
	public static RGBRange getRGBRange(int x,int y,int width, int height, BufferedImage image){
		/*
		//BufferedImage subimage = image.getSubimage(x, y, width, height);
		int[] RGBArray = new int[width*height];
		image.getRGB(x, y, width, height, RGBArray, 0, 3);
		System.out.println("x = " + x +", Y = " + y + ",Width = " + width + ",Height = " + height);
		for(int i = 0; i < RGBArray.length; i++){
			System.out.println(i + " = " + RGBArray[i]);
		}
		*/
		RGBRange newRange = new RGBRange();
		for(int w=0; w<width; w++){
			for(int h=0; h<height; h++){
				newRange.insertColor(new Color(image.getRGB(x+w, y+h)));
			}
		}
		//newRange.print();
		return newRange;
	}
	//this should somehow check that the image is YUV, i dunno how to do that?
	public static YUVRange getYUVRange(int x,int y,int width, int height, BufferedImage image){
		YUVRange newRange = new YUVRange();
		for(int w=0; w<width; w++){
			for(int h=0; h<height; h++){
				//image.getRGB actualy returns YUV if this is passed a yuv image
				newRange.insertColor(new Color(image.getRGB(x+w, y+h)));
			}
		}
		//newRange.print();
		return newRange;
	}
	
	public static Color RGB2YUV(Color c){
		  int y = (306*c.getRed() + 601*c.getGreen() + 117*c.getBlue())  >> 10;
		  int u = ((-172*c.getRed() - 340*c.getGreen() + 512*c.getBlue()) >> 10)  + 128;
		  int v = ((512*c.getRed() - 429*c.getGreen() - 83*c.getBlue()) >> 10) + 128;
		  y = y < 0 ? 0 : y;
		  u = u < 0 ? 0 : u;
		  v = v < 0 ? 0 : v;
		  y = y > 255 ? 255 : y;
		  u = u > 255 ? 255 : u;
		  v = v > 255 ? 255 : v;
		  return new Color(y,u,v);
	}
	
	public static BufferedImage RGBImageToYUVImage(BufferedImage RGBImage){
		int width = RGBImage.getWidth();
		int height = RGBImage.getHeight();
		//BufferedImage YUVImage = new BufferedImage(width, height, RGBImage.getType());
		BufferedImage YUVImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g = YUVImage.getGraphics();
		g.drawImage(RGBImage, 0, 0, null);
		for(int x = 0; x < width; x++){
			for(int y = 0; y <height; y++){
				//both the RGB and YUB images can call get RGB to get the RGB since it isn't set to YUV yet,,,,
				//these 2 lines could be one but they are broken down for readability!
				//if anyone ever reads this, add a comment saying you did and push to github :D:D:D
				Color YUVColor = RGB2YUV(new Color (YUVImage.getRGB(x, y)));
				YUVImage.setRGB(x, y, YUVColor.getRGB());
			}
		}
		return YUVImage;
	}
}

