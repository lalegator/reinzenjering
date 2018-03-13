package geometrija;

import java.awt.Graphics;

public class Linija extends Oblik implements Comparable{
	private Tacka tPocetna;
	private Tacka tKrajnja;

	public Linija (Tacka itPocetna, Tacka itKrajnja){
		tPocetna=itPocetna;
		tKrajnja = itKrajnja;
	}
	public Linija (Tacka itPocetna, Tacka itKrajnja, String iboja){
		super(iboja);
		tPocetna=itPocetna;
		tKrajnja = itKrajnja;	
	}
	public void crtajSe (Graphics g){
		g.setColor(pronadjiBoju(getBoja()));
		g.drawLine(tPocetna.getX(),tPocetna.getY(), tKrajnja.getX(), tKrajnja.getY());
		if (isSelektovan())
			selektovan(g);
	}
	/*	public void selektovan(Graphics g){
		g.setColor(pronadjiBoju("plava"));
		g.drawRect(tPocetna.getX()-3, tPocetna.getY()-3, 6, 6);
		g.drawRect(tKrajnja.getX()-3, tKrajnja.getY()-3, 6, 6);
		g.drawRect(sredisnjaTackaLinije().getX()-3, sredisnjaTackaLinije().getY()-3, 6, 6);
	}*/
	public void pomeriNa (int poX, int poY) {
		int razX = tKrajnja.getX() - tPocetna.getX();
		int razY = tKrajnja.getY() - tPocetna.getY();
		tPocetna.pomeriNa(poX, poY);
		tKrajnja.pomeriNa(poX + razX, poY + razY);
	}
	public void selektovan (Graphics g){
		tPocetna.selektovan(g);
		tKrajnja.selektovan(g);
		sredisnjaTackaLinije().selektovan(g);
	}

	public int compareTo (Object obj){
		Linija druga = (Linija) obj;
		double d = duzina() - druga.duzina();
		int c = (int) d;
		return c;
	}

	public boolean sadrzi (int x, int y){
		Tacka t = new Tacka(x, y);
		if (tPocetna.udaljenost(t) + tKrajnja.udaljenost(t) <= tPocetna.udaljenost(tKrajnja)+0.5)
			return true;
		else
			return false;
	}

	public boolean eguals (Object obj){
		if (obj instanceof Linija){
			Linija pomocna = (Linija) obj;
			if (tPocetna.equals(pomocna.tPocetna) && this.tKrajnja.equals(pomocna.gettKrajnja())
					&& this.getBoja().equalsIgnoreCase(pomocna.getBoja()))
				return true;
			else
				return false;	
		}
		return false;
	}


	public Tacka sredisnjaTackaLinije (){
		int x= (tPocetna.getX() + tKrajnja.getX())/2;
		int y= (tPocetna.getY() + tKrajnja.getY())/2;
		Tacka sredina = new Tacka (x, y);
		return sredina;	 
	}

	public String toString (){
		/*String s = "("+tPocetna.getX()+","+tPocetna.getY()+")"+"("+tKrajnja.getX()+","+tKrajnja.getY()+")";
				return s;*/
		String s = tPocetna + "-->" + tKrajnja;
		return s;
	}
	public Tacka gettPocetna() {
		return tPocetna;
	}
	public void settPocetna(Tacka tPocetna) {
		this.tPocetna = tPocetna;
	}
	public Tacka gettKrajnja() {
		return tKrajnja;
	}
	public void settKrajnja(Tacka tKrajnja) {
		this.tKrajnja = tKrajnja;
	}
	public double duzina(){
		double d = tPocetna.udaljenost(tKrajnja);
		return d;
	}
	public void pomeriZa (int poX, int poY){
		tPocetna.pomeriZa(poX, poY);
		tKrajnja.pomeriZa(poX, poY);
	}

}
