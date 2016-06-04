package projeto.logic;


/**
 * @author Joao e David
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
	
	/**
	 * Construtor default da classe
	 */
	public Rectangulo() {
		
	}

	/**
	 * Construtor da classe rectangulo
	 * @param xCoord coordenada x do canto superior esquerdo do rectangulo
	 * @param yCoord coordenada y do canto superior esquerdo do rectangulo
	 * @param w largura
	 * @param h comprimento
	 */
	public Rectangulo(double xCoord,double yCoord,double w, double h){//Para rectagunlos
		height = h;
		width = w;
		xI = xCoord;  
		yI = yCoord;
		xF = xI + w;
		yF = yI + h;
	}
	
	/**
	 * Contrustor para rectangulo envolventes de circulos
	 * @param xCoord coordenada x do canto superior esquerdo do rectangulo
	 * @param yCoord coordenada y do canto superior esquerdo do rectangulo
	 * @param edge tamanho do lado do quadrado
	 */
	public Rectangulo(double xCoord, double yCoord, double edge){//Para circulos (x e y do centro do circulo)
		height = edge;
		width = edge; 
		xI = xCoord;
		yI = yCoord;
		xF = xI + edge;
		yF = yI + edge;
	}

	/**
	 * Funcao get para a altura
	 * @return a altura
	 */
	public double getHeight() {
		return height;
	}

	/**
	 * Funcao set para a altura
	 * @param height nova altura
	 */
	public void setHeight(double height) {
		this.height = height;
	}

	/**
	 * Funcao get para a largura
	 * @return a largura
	 */
	public double getWidth() {
		return width;
	}

	/**
	 * Funcao set para a largura
	 * @param width nova largura
	 */
	public void setWidth(double width) {
		this.width = width;
	}

	/**
	 * Funcao get para o x inicial
	 * @return x inicial
	 */
	public double getxI() {
		return xI;
	}

	/**
	 * Funcao set para o x inicial
	 * @param xI novo x inicial
	 */
	public void setxI(double xI) {
		this.xI = xI;
		this.xF = xI + width;
	}

	/**
	 * Funcao get para o y inicial
	 * @return y inicial
	 */
	public double getyI() {
		return yI;
	}

	/**
	 * Funcao set para o y inicial
	 * @param yI novo y inicial
	 */
	public void setyI(double yI) {
		this.yI = yI;
		this.yI = yI + height;  
	}

	/**
	 * Funcao get para o x final
	 * @return x final
	 */
	public double getxF() {
		return xF;
	}

	/**
	 * Funcao set para o x final
	 * @param xF novo x final
	 */
	public void setxF(double xF) {
		this.xF = xF; 
	}

	/**
	 * Funcao get para o x final
	 * @return x final
	 */
	public double getyF() {
		return yF;
	}

	/**
	 * Funcao set para o y final
	 * @param yF novo y final
	 */
	public void setyF(double yF) {
		this.yF = yF;
	}
}