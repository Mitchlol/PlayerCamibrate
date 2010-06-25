package camibrate;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
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
import javax.swing.ImageIcon;

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
}

