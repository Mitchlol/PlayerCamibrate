package camibrate;

import java.awt.Color;

public class CamibrateBlob {
	private RGBRange mRGBRange;
	private Color displayColor;
	private int channel;
	private String name;

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