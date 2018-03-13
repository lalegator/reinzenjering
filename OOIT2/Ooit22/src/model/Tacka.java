package model;

import java.awt.Graphics;

public class Tacka extends Oblik implements Comparable {
	private int x;
	private int y;

	public Tacka(int ix, int iy){
		x=ix;
		y=iy;
	}
	public Tacka(int ix, int iy, String iboja){
		super(iboja);
		x=ix;
		y=iy;	
	}

	public boolean sadrzi (int x, int y){
		Tacka t = new Tacka(x, y);
		if (this.udaljenost(t)<=3 )
			return true;
		else
			return false;
	}


	public void crtajSe (Graphics g){
		g.setColor(pronadjiBoju(getBoja()));
		g.drawLine(x-1, y-1, x+2, y+1);
		g.drawLine(x+1, y-1, x-1, y+1);	
		if (isSelektovan())
			selektovan(g);	
	}
	public void selektovan(Graphics g){
		g.setColor(pronadjiBoju("plava"));
		g.drawRect(x-3,y-3 , 6, 6);

	}

	public int compareTo (Object obj){
		Tacka druga = (Tacka) obj;
		Tacka nula = new Tacka (0,0);
		double d = udaljenost (nula) - druga.udaljenost(nula);
		int c = (int) d;
		return c;
	}

	public boolean equals (Object obj){
		if (obj instanceof Tacka){
			Tacka pomocna = (Tacka) obj;
			if (this.x == pomocna.getX() && this.y == pomocna.getY() &&
					this.getBoja().equalsIgnoreCase(pomocna.getBoja()))
				return true;
			else
				return false;
		}
		return false;
	}

	public String toString (){
		String s = "(" + x +","+ y +")";
		return s;
	}


	public void setX (int novoX){
		this.x=novoX;
	}
	public int getX (){
		return this.x;
	}
	public void setY (int novoY){
		this.y=novoY;
	}
	public int getY (){
		return this.y;
	}
	public void pomeriNa (int novoX, int novoY){
		x=novoX;
		y=novoY;
	}
	public void pomeriZa (int poX, int poY){
		x=x+poX;
		y=y+poY;
	}
	public double udaljenost (Tacka t){
		int dx = this.x - t.x ;
		int dy = this.getY() - t.getY();
		double d = Math.sqrt(dx * dx + dy * dy);
		return d;

	}
}
