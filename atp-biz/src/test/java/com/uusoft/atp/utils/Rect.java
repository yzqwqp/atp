package com.uusoft.atp.utils;

public class Rect {
	protected int width,height;
	Rect(int w, int h){
		width = w;
		height = h;
	}
	Rect(){
		width = 10;
		height = 10;
	}
	public int areaRect(){
		return width*height;
	}
	public int permeterRect(){
		return 2*(width+height);
	}
	public String toString(){
		String s = "the Rectangle is : ";
		s+= width + "," + height + " the area is " + areaRect();
		return s;
	}
	
}
