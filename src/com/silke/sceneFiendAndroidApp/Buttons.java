package com.silke.sceneFiendAndroidApp;

import android.content.Context;

import android.util.AttributeSet;
import android.widget.Button;

public class Buttons extends Button
{
	private static Context c;
	
//	public Buttons(String name)
//	{
//        this(name, R.color.title_glow);
//    }
//
//    public Buttons(String name, int col) 
//    {
//        this(name, col, con);
//    }
	
	public Buttons(Context context, AttributeSet attrs) 
	{
		super(context, attrs);
		// TODO Auto-generated constructor stub
		 setBackgroundResource(R.drawable.icon);
	     setTextSize(14);
	}
	
	
	
}
