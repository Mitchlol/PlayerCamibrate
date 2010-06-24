package camibrate;

import java.awt.Color;

public class RGBRange {
	private int minR, minG, minB, maxR, maxG, maxB;

	public Color getMinColor(){
		return new Color(minR,minG,minB);
	}
	
	public Color getMaxColor(){
		return new Color(minR,minG,minB);
	}
	
	public Color getAveredgeColor(){
		return new Color((minR+maxR)/2,(minG+maxG)/2,(minB+maxB)/2);
	}
	
	public int getMinR() {
		return minR;
	}

	public void setMinR(int minR) {
		this.minR = minR;
	}

	public int getMinG() {
		return minG;
	}

	public void setMinG(int minG) {
		this.minG = minG;
	}

	public int getMinB() {
		return minB;
	}

	public void setMinB(int minB) {
		this.minB = minB;
	}

	public int getMaxR() {
		return maxR;
	}

	public void setMaxR(int maxR) {
		this.maxR = maxR;
	}

	public int getMaxG() {
		return maxG;
	}

	public void setMaxG(int maxG) {
		this.maxG = maxG;
	}

	public int getMaxB() {
		return maxB;
	}

	public void setMaxB(int maxB) {
		this.maxB = maxB;
	}

}
