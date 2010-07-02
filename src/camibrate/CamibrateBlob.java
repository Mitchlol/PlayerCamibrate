package camibrate;

import java.awt.Color;
import java.util.Stack;

public class CamibrateBlob {
	private RGBRange mRGBRange;
	private YUVRange mYUVRange;
	private Stack<RGBRange> mRGBRangeStack;
	private Stack<YUVRange> mYUVRangeStack;
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
		mRGBRangeStack = new Stack<RGBRange>();
		mYUVRangeStack = new Stack<YUVRange>();
		displayColor = Color.white;
		channel = -1;
		this.name = new String(name);
	}
	
	public void addRGBRange(RGBRange range){
		mRGBRangeStack.push(mRGBRange.copy());
		mRGBRange.insertColor(range.getMinColor());
		mRGBRange.insertColor(range.getMaxColor());
	}
	
	public void addYUVRange(YUVRange range){
		mYUVRangeStack.push(mYUVRange.copy());
		mYUVRange.insertColor(range.getMinColor());
		mYUVRange.insertColor(range.getMaxColor());
	}
	//these 2 functions don't check if the stack is empty, at some point they should have error popup...
	public void undoLastRGBRange(){
		mRGBRange = mRGBRangeStack.pop();
	}
	
	public void undoLastYUVRange(){
		mYUVRange = mYUVRangeStack.pop();
	}
	
	public void clearRGBRange(){
		mRGBRangeStack.push(mRGBRange.copy());
		mRGBRange = new RGBRange();
	}
	
	public void clearYUVRange(){
		mYUVRangeStack.push(mYUVRange.copy());
		mYUVRange = new YUVRange();
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