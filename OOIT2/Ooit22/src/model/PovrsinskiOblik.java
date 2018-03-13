package model;

import java.awt.Graphics;

public abstract class PovrsinskiOblik extends Oblik {
	private String bojaUnutrasnosti;

	public PovrsinskiOblik () {
		
	}
	public PovrsinskiOblik (String iBojaU){
		bojaUnutrasnosti = iBojaU;
	}
	
	public abstract void popuni (Graphics g);
	public abstract boolean sadrzi (int x, int y);
	public abstract double povrsina ();
	public abstract double obim ();
		
	
	public String getBojaUnutrasnosti() {
		return bojaUnutrasnosti;
	}

	public void setBojaUnutrasnosti(String bojaUnutrasnosti) {
		this.bojaUnutrasnosti = bojaUnutrasnosti;
	}
}
