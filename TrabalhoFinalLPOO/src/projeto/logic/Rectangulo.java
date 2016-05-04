package projeto.logic;


/**
 * @author David
 * @version 1.0
 * @created 03-mai-2016 15:30:43
 */
public class Rectangulo {

	private double height;
	private double width;
	private double xI;
	private double yI;
	private double xF;
	private double yF;

	public Rectangulo(){

	}

	public Rectangulo(double xCoord,double yCoord,double h, double w){//Para rectagunlos
		height = h;
		width = w;
		xI = xCoord - (w/2);
		yI = yCoord - (h/2);
		xF = xI + w;
		yF = yI + h;
	}
	
	public Rectangulo(double xCoord, double yCoord, double edge){//Para circulos (x e y do centro do circulo)
		height = edge;
		width = edge;
		xI = xCoord - (edge/2);
		yI = yCoord - (edge/2);
		xF = xI + edge;
		yF = yI + edge;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getxI() {
		return xI;
	}

	public void setxI(double xI) {
		this.xI = xI;
	}

	public double getyI() {
		return yI;
	}

	public void setyI(double yI) {
		this.yI = yI;
	}

	public double getxF() {
		return xF;
	}

	public void setxF(double xF) {
		this.xF = xF;
	}

	public double getyF() {
		return yF;
	}

	public void setyF(double yF) {
		this.yF = yF;
	}
}