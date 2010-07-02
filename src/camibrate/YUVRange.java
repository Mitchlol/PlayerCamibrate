package camibrate;

import java.awt.Color;

public class YUVRange {
	private int minY, minU, minV, maxY, maxU, maxV;
	
	public YUVRange(){
		minY = 255;
		maxY = 0;
		minU = 255;
		maxU = 0;
		minV = 255;
		maxV = 0;
	}
	
	public void print(){
		System.out.println("YUV =("+minY+":"+maxY+","+minU+":"+maxU+","+minV+":"+maxV+")");
	}
	
	public YUVRange copy(){
		YUVRange newRange = new YUVRange();
		//this wont work as the default values max and min get reversed to make the range include everything
		//newRange.insertColor(this.getMinColor());
		//newRange.insertColor(this.getMaxColor());
		newRange.setMinY(minY);
		newRange.setMinU(minU);
		newRange.setMinV(minV);
		newRange.setMaxY(maxY);
		newRange.setMaxU(maxU);
		newRange.setMaxV(maxV);
		return newRange;
	}
	
/*	
	public boolean testColor(Color c){
		if(c.getRed() >= minR && c.getRed() <= maxR){
			if(c.getGreen() >= minG && c.getGreen() <= maxG){
				if(c.getBlue() >= minB && c.getBlue() <= maxB){
					return true;
				}
			}
		}
		return false;
	}
*/	
	//4 function calls instead of the previous 6 >.< I'm such a NEEERRRRDDDDD!!!!!
	public boolean testColor(Color yuv){
		//Color yuv = StaticFunctions.RGB2YUV(rgb);
		//the getRed,getGreen,getBlue functions will actual return YUV numbers
		return testColor(yuv.getRed(),yuv.getGreen(),yuv.getBlue());
	}
	
	private boolean testColor(int y, int u, int v){
		
		if(y >= minY && y <= maxY){
			if(u >= minU && u <= maxU){
				if(v >= minV && v <= maxV){
					return true;
				}
			}
		}
		return false;
	}
	//Color c must contain YUV values
	public void insertColor(Color c){
		//the getRed,getGreen,getBlue functions will actual return YUV numbers
		if(c.getRed() < minY){
			minY = c.getRed();
		}
		if(c.getRed() > maxY){
			maxY = c.getRed();
		}
		if(c.getGreen() < minU){
			minU = c.getGreen();
		}
		if(c.getGreen() > maxU){
			maxU = c.getGreen();
		}
		if(c.getBlue() < minV){
			minV = c.getBlue();
		}
		if(c.getBlue() > maxV){
			maxV = c.getBlue();
		}
	}

	public Color getMinColor(){
		return new Color(minY,minU,minV);
	}
	
	public Color getMaxColor(){
		return new Color(maxY,maxU,maxV);
	}
	
	public Color getAveredgeColor(){
		return new Color((minY+maxY)/2,(minU+maxU)/2,(minV+maxV)/2);
	}
	
	public int getMinY() {
		return minY;
	}

	public void setMinY(int minY) {
		this.minY = minY;
	}

	public int getMinU() {
		return minU;
	}

	public void setMinU(int minU) {
		this.minU = minU;
	}

	public int getMinV() {
		return minV;
	}

	public void setMinV(int minV) {
		this.minV = minV;
	}

	public int getMaxY() {
		return maxY;
	}

	public void setMaxY(int maxY) {
		this.maxY = maxY;
	}

	public int getMaxU() {
		return maxU;
	}

	public void setMaxU(int maxU) {
		this.maxU = maxU;
	}

	public int getMaxV() {
		return maxV;
	}

	public void setMaxV(int maxV) {
		this.maxV = maxV;
	}

}
