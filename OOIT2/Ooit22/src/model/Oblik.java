package model;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Oblik {
	private String boja;
	private boolean selektovan;
	
	public boolean isSelektovan() {
		return selektovan;
	}
	public void setSelektovan(boolean selektovan) {
		this.selektovan = selektovan;
	}
	public Oblik (){
	}
	public Oblik (String iboja){
		boja=iboja;
	}
	public abstract void crtajSe (Graphics g);
	
	public abstract boolean sadrzi (int x, int y);
	
	public static Color pronadjiBoju (String boja){
		if (boja == null)
			return Color.black;
			else if ( boja.equalsIgnoreCase("crna"))
			return Color.BLACK;
		else if (boja.equalsIgnoreCase("bela"))
			 return Color.WHITE;
		else if (boja.equalsIgnoreCase("crvena"))
			 return Color.RED;
		else if (boja.equalsIgnoreCase("plava"))
			 return Color.BLUE;
		else if (boja.equalsIgnoreCase("zuta"))
			return Color.YELLOW;
		else if (boja.equalsIgnoreCase("zelena"))
			return Color.GREEN;
		else
			return Color.BLACK;				
	}
	
	public String getBoja() {
		return boja;
	}

	public void setBoja(String boja) {
		this.boja = boja;
	}
	
	}



