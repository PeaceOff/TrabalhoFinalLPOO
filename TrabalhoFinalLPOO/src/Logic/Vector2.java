package Game.Logic;


/**
 * @author David
 * @version 1.0
 * @created 03-mai-2016 15:30:46
 */
public class Vector2 {

	public float x;
	public float y;

	public Vector2(){

	}
	
	Vector2(float xCoord, float yCoord){
		x = xCoord;
		y = yCoord;
	}

	public void finalize() throws Throwable {

	}

	/**
	 * 
	 * @param v
	 */
	public void add(Vector2 v){
		this.x += v.x;
		thisy += v.y;
	}

	/**
	 * 
	 * @param v1
	 * @param v2
	 */
	public static float distance(Vector2 v1, Vector2 v2){
		float res = null;
		res = sqrt( ( (v2.x - v1.x)^2 ) + ( (v2.y - v1.y)^2) );
		return res;
	}

	/**
	 * 
	 * @param v1
	 * @param v2
	 * @param t
	 */
	public static Vector2 lerp(Vector2 v1, Vector2 v2, float t){
		Vector2 res = null;
		res.x = v1.x + ((v2.x - v1.x) * t);
		res.y = v1.y + ((v2.y - v1.y) * t);
		return res;
	}

	public float getNorm(){
		return sqrt(this.x^2 + this.y^2);
	}
	
	public void normalize(){
		this.x /= this.getNorm();
		this.y /= this.getNorm();
	}

}