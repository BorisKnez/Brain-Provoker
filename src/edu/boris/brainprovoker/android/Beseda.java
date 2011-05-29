package edu.boris.brainprovoker.android;

import android.graphics.Paint;

public class Beseda {
	
	public String ime;
	public Paint barva;
	
	public Beseda(String i, Paint p)
	{
		this.ime=i;
		this.barva=p;
	}
	public Beseda(){}
	
	public String getBeseda()
	{
		return ime;
	}
	
	public Paint getBarva()
	{
		return barva;
	}

}
