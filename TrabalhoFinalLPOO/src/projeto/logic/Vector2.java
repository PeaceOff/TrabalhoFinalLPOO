package projeto.logic;


/**
 * @author David
 * @version 1.0
 * @created 03-mai-2016 15:30:46
 */
public class Vector2 {

	public double x;
	public double y;

	public Vector2(){
		x = 0;
		y = 0;
	}
	
	public Vector2(double xCoord, double yCoord){
		x = xCoord;
		y = yCoord;
	}

	public void finalize() throws Throwable {

	}

	public void add(Vector2 v){
		this.x += v.x;
		this.y += v.y;
	}
	
	public static Vector2 sub(Vector2 v1,Vector2 v2){
		Vector2 res = new Vector2();
		res.x = v1.x - v2.x;
		res.y = v1.y - v2.y;
		return res;
	}

	public static double distance(Vector2 v1, Vector2 v2){
		double res;
		double formula = ( (v2.x - v1.x)*(v2.x - v1.x) ) + ( (v2.y - v1.y)*(v2.y - v1.y));
		res = Math.sqrt(formula);
		return res;
	}

	public static Vector2 lerp(Vector2 v1, Vector2 v2, double t){
		Vector2 res = new Vector2();
		res.x = v1.x + ((v2.x - v1.x) * t);
		res.y = v1.y + ((v2.y - v1.y) * t);
		return res;
	}

	public double getNorm(){
		return Math.sqrt(this.x*this.x + this.y*this.y);
	}
	
	public void normalize(){
		this.x /= this.getNorm();
		this.y /= this.getNorm();
	}

}