package camibrate;

import java.awt.Color;

public class CamibrateBlob {
	private RGBRange mRGBRange;
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
		displayColor = new Color(0x00000000);
		channel = -1;
		this.name = new String(name);
	}
	
	public boolean testColor(Color c){
		return mRGBRange.testColor(c);
	}
	
	public boolean testColorToYUV(Color c){
		return mRGBRange.testColor(StaticFunctions.RGB2YUV(c));
	}

	public RGBRange getmRGBRange() {
		return mRGBRange;
	}
	public void setmRGBRange(RGBRange mRGBRange) {
		this.mRGBRange = mRGBRange;
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