package camibrate;

import java.awt.Color;

public class CamibrateBlob {
	private RGBRange mRGBRange;
	private YUVRange mYUVRange;
	private Color displayColor;
	private int channel;
	private String name;
	//overriding to sting is important so it displays properly in many places....
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name;
	}

	public CamibrateBlob(String name){
		mRGBRange = new RGBRange();
		mYUVRange = new YUVRange();
		displayColor = Color.cyan;
		channel = -1;
		this.name = new String(name);
	}
	
	public boolean testRGBColor(Color c){
		return mRGBRange.testColor(c);
	}
	
	public boolean testYUVColor(Color c){
		return mYUVRange.testColor(c);
	}

	public RGBRange getRGBRange() {
		return mRGBRange;
	}
	
	public void setRGBRange(RGBRange mRGBRange) {
		this.mRGBRange = mRGBRange;
	}
	
	public YUVRange getYUVRange() {
		return mYUVRange;
	}
	
	public void setYUVRange(YUVRange mYUVRange) {
		this.mYUVRange = mYUVRange;
	}
	
	public Color getDisplayColor() {
		return displayColor;
	}
	public void setDisplayColor(Color displayColor) {
		this.displayColor = displayColor;
	}
	public int getChannel() {
		return channel;
	}
	public void setChannel(int channel) {
		this.channel = channel;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}