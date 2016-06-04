package projeto.logic;

import java.io.Serializable;

/**
 * @author Joao e David
 * @version 1.0
 * @created 03-mai-2016 15:30:46
 */
public class Vector2 implements Serializable {

	static final long serialVersionUID = 41L;
	
	public double x;
	public double y;
	/**
	 * Construtor base de um Vetor que inicializa os seus atributos a 0
	 */
	public Vector2(){
		x = 0;
		y = 0;
	}
	
	/**
	 * Constructor de vetor que recebe o valor dos seus atributos
	 * @param xCoord coordenada x do vetor
	 * @param yCoord coordenada y do vetor
	 */
	public Vector2(double xCoord, double yCoord){
		x = xCoord;
		y = yCoord;
	}

	public void finalize() throws Throwable {

	}

	/**
	 * Funcao para adicionar um vetor a outro
	 * @param v Vetor que ira ser somada a este
	 */
	public void add(Vector2 v){
		this.x += v.x;
		this.y += v.y;
	}
	
	/**
	 *	Funcao para subtrair dois vetores (v1 - v2)
	 * @param v1
	 * @param v2
	 * @return devolve um novo vetor com o resultado
	 */
	public static Vector2 sub(Vector2 v1,Vector2 v2){
		Vector2 res = new Vector2();
		res.x = v1.x - v2.x;
		res.y = v1.y - v2.y;
		return res;
	}

	/**
	 * Funcao para calcular a distancia entre dois vetores
	 * @param v1
	 * @param v2
	 * @return o valor da distancia entre v1 e v2.
	 */
	public static double distance(Vector2 v1, Vector2 v2){
		double res;
		double formula = ( (v2.x - v1.x)*(v2.x - v1.x) ) + ( (v2.y - v1.y)*(v2.y - v1.y));
		
		res = Math.sqrt(formula);
		return res;
	}

	/**
	 * Funcao de interpolacao linear de dois vetores
	 * @param v1 
	 * @param v2
	 * @param t valor da interpolacao
	 * @return vetor resultante da interpolacao
	 */
	public static Vector2 lerp(Vector2 v1, Vector2 v2, double t){
		Vector2 res = new Vector2();
		res.x = v1.x + ((v2.x - v1.x) * t);
		res.y = v1.y + ((v2.y - v1.y) * t);
		return res;
	}

	/**
	 * Funcao obter a norma de um vetor
	 * @return Norma do vetor
	 */
	public double getNorm(){
		return Math.sqrt(this.x*this.x + this.y*this.y);
	}
	
	/**
	 * Funcao para normalizar um dado vetor
	 */
	public void normalize(){
		double norm = getNorm();
		x /= norm;
		y /= norm;
	}
	
	/**
	 * Funcao para obter o produto escalar
	 * @param v
	 * @return produto escalar entre os vetores
	 */
	public double dot(Vector2 v){
		return this.x * v.x + this.y * v.y;
	}
	
	/**
	 * Funcao para multiplicar um vetor por uma constante
	 * @param v constante a multiplicar no vetor
	 */
	public void multiply(double v){
		this.x *= v;
		this.y *= v;
		return;
	}
	
	/**
	 * Funcao para duplicar um vetor
	 */
	public Vector2 clone(){
		return new Vector2(x,y);
	}
	
	/**
	 * Funcao para multiplicar um vetor por um valor
	 * @param v1
	 * @param valor
	 * @return um novo vetor com o resultado da multiplicacao
	 */
	public static Vector2 multiply(Vector2 v1,double valor){
		return new Vector2(v1.x * valor, v1.y * valor);
	}
		
	/**
	 * Funcao para adicionar dois vetores
	 * @param v1
	 * @param v2
	 * @return devolve um novo vetor com o resultado
	 */
	public static Vector2 add(Vector2 v1, Vector2 v2){
		return new Vector2(v1.x + v2.x,v1.y + v2.y);
	}

}