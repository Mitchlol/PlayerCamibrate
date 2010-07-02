package camibrate.gui.blobcreator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Vector;

import javax.swing.JPanel;

import camibrate.CamibrateBlob;
import camibrate.StaticFunctions;

public class RGBSegmentedDisplay extends JPanel {
	public static final int MODE_RGB = 0;
	public static final int MODE_YUV = 1;
	
	int mode;
	Vector<CamibrateBlob> blobs;
	BufferedImage RGBImage;
	BufferedImage YUVImage;
	
	RGBSegmentedDisplay(Vector<CamibrateBlob> blobs, BufferedImage RGBImage){
		this.blobs = blobs;
		this.RGBImage = RGBImage;
		
		this.RGBImage = StaticFunctions.scaleImageToFit(RGBImage, 500, 400);
		this.setPreferredSize(new Dimension(this.RGBImage.getWidth(),this.RGBImage.getHeight()));
		this.YUVImage = StaticFunctions.RGBImageToYUVImage(this.RGBImage);
		
		int mode = MODE_RGB;
	}
	
	public void updateBlobs(Vector<CamibrateBlob> blobs){
		this.blobs = blobs;
		repaint();
	}
	
	public void updateImage(BufferedImage image){
		this.RGBImage = image;
		
		this.RGBImage = StaticFunctions.scaleImageToFit(image, 500, 500);
		this.setPreferredSize(new Dimension(this.RGBImage.getWidth(),this.RGBImage.getHeight()));
		this.YUVImage = StaticFunctions.RGBImageToYUVImage(RGBImage);
		
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		//for every pixel
		for(int x = 0; x < RGBImage.getWidth(); x++){
			for(int y = 0; y < RGBImage.getHeight(); y++){
				//fill black
				g.setColor(Color.black);
				g.drawRect(x, y, 1, 1);
				//then over write with color if it falls within blob range
				//for every blob
				for(int i = 0; i < blobs.size(); i++){
					//if rgb mode check rgb pixel with rgb range
					if(mode == MODE_RGB){
						Color c = new Color(RGBImage.getRGB(x, y));
						if(blobs.get(i).testRGBColor(c)){
							//System.out.println("Pixel("+x+","+y+") color("+c+") passed!---------------------");
							//image.setRGB(x, y, c.getRGB());
							g.setColor(blobs.get(i).getDisplayColor());
							g.drawLine(x, y, x, y);
						}else{
							//System.out.println("Pixel("+x+","+y+") color("+c+") FAILED!!!!");
						}
					//if YUV mode check yuv pixel for yuv range
					}else if(mode == MODE_YUV){
						Color c = new Color(YUVImage.getRGB(x, y));
						if(blobs.get(i).testYUVColor(c)){
							//System.out.println("Pixel("+x+","+y+") color("+c+") passed!---------------------");
							//image.setRGB(x, y, c.getRGB());
							g.setColor(blobs.get(i).getDisplayColor());
							g.drawLine(x, y, x, y);
						}else{
							//System.out.println("Pixel("+x+","+y+") color("+c+") FAILED!!!!");
						}
					}
				}
			}
		}
		
	}
	
	public void setMode(int mode){
		this.mode = mode;
		repaint();
	}
	public int getMode(){
		return this.mode;
	}
	
	

}
