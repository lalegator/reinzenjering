package model;

import java.awt.Graphics;

public class Pravougaonik extends Kvadrat implements Comparable{
	private int visina;


	public Pravougaonik(Tacka igoreLevo, int isirina, int ivisina){
		super(igoreLevo, isirina);
		visina = ivisina;
	}
	public Pravougaonik(Tacka igoreLevo, int isirina, int ivisina, String iboja){
		this(igoreLevo, isirina, ivisina);
		setBoja(iboja);
	}
	public void crtajSe(Graphics g) {
		int ddx = goreLevo.getX() + stranica;
		int ddy = goreLevo.getY() + visina;
		if (stranica > 0) {
			g.setColor(pronadjiBoju(getBoja()));
			g.drawLine(goreLevo.getX(), goreLevo.getY(), ddx, goreLevo.getY());
			g.drawLine(goreLevo.getX(), goreLevo.getY(), goreLevo.getX(), ddy);
			g.drawLine(goreLevo.getX(), ddy, ddx, ddy);
			g.drawLine(ddx, goreLevo.getY(), ddx, ddy);
			if (getBojaUnutrasnosti() != null)
				popuni(g);
			if (isSelektovan()){
				selektovan(g);
			}
		}
	}
	public void popuni (Graphics g){
		g.setColor(pronadjiBoju(getBojaUnutrasnosti()));
		g.fillRect(goreLevo.getX() + 1, goreLevo.getY() + 1, stranica - 1, visina - 1);
	}
	public boolean sadrzi (int x, int y){
		if (x >= goreLevo.getX() &&  x <= goreLevo.getX() + stranica 
				&& y >= goreLevo.getY() &&  y <= goreLevo.getY() + visina)
			return true;
		else 
			return false;
	}

	public void selektovan (Graphics g){
		new Linija(goreLevo, new Tacka(goreLevo.getX()+stranica, goreLevo.getY())).selektovan(g);;
		new Linija(goreLevo, new Tacka(goreLevo.getX(), goreLevo.getY()+visina)).selektovan(g);;
		new Linija( new Tacka(goreLevo.getX(), goreLevo.getY()+visina), new Tacka(goreLevo.getX()+stranica, goreLevo.getY()+visina)).selektovan(g);;
		new Linija(new Tacka(goreLevo.getX()+stranica, goreLevo.getY()+visina),new Tacka(goreLevo.getX()+stranica, goreLevo.getY())).selektovan(g);	;


	}

	public int compareTo (Object obj){
		Pravougaonik drugi = (Pravougaonik) obj;
		double d = povrsina() - drugi.Povrsina();
		int c = (int) d;
		return c;
	}
	public boolean equals (Object obj){
		if (obj instanceof Pravougaonik){
			Pravougaonik pomocni = (Pravougaonik) obj;
			if (goreLevo.equals(pomocni.getGoreLevo()) && visina == pomocni.getVisina() && 
					stranica == pomocni.getStranica())
				return true;
			else
				return false;
		}
		return false;
	}

	public Linija dijagonalaPravougaonika (){
		int x= goreLevo.getX() + stranica;
		int y = goreLevo.getY() + visina;
		Tacka doleDesno = new Tacka (x,y);
		Linija d = new Linija (goreLevo, doleDesno);
		return d;	
	}
	public Tacka centralnaTacka (){
		Tacka srednja = dijagonalaPravougaonika().sredisnjaTackaLinije();		
		return srednja;
	}
	public String toString (){
		String s = "gornji levi ugao="+goreLevo+ ", sirina="+ stranica + ", visina="+visina;
		return s;
	}
	public Tacka getGoreLevo() {
		return goreLevo;
	}
	public void setGoreLevo(Tacka goreLevo) {
		this.goreLevo = goreLevo;
	}
	public int getSirina() {
		return stranica;
	}
	public void setSirina(int sirina) {
		this.stranica = sirina;
	}
	public int getVisina() {
		return visina;
	}
	public void setVisina(int visina) {
		this.visina = visina;
	}

	public double Povrsina(){         /*ne pise se get, nego nam to govori da ova metoda nesto vraca*/
		return  stranica * visina;
	}
	public double Obim(){
		return 2*stranica + 2*visina;

	}


}
