package com.testing.model;

import java.util.StringTokenizer;

public class FileMeta {
	StringTokenizer st;
	double X,Y,Width,Height;
	String name,format,path;
	public FileMeta(String filename){
		st = new StringTokenizer(filename,".");
		path= st.nextToken();
		format = st.nextToken();
		
		st = new StringTokenizer(path,"_"); 
		name = st.nextToken();
		X = Double.parseDouble(st.nextToken());
		Y = Double.parseDouble(st.nextToken());
		Width = Double.parseDouble(st.nextToken());
		Height = Double.parseDouble(st.nextToken());
		
	}
	public double getX(){
		return X;
	}
	public double getY(){
		return Y;
	}
	public double getWidth(){
		return Width;
	}
	public double getHeight(){
		return Height;
	}
	public String getFormat(){
		return format;
	}
	public String getName(){
		return path;
	}
}
