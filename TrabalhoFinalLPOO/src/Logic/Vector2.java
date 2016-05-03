package Logic;

public class Vector2 {
	float x;
	float y;
	
	Vector2(){}
	
	Vector2(float x,float y){
		this.x = x;
		this.y = y;
	}
	
	Vector2 add(Vector2 v){
		Vector2 temp = new Vector2();
		temp.x = this.x + v.x;
		temp.y = this.y + v.y;
		return temp;
	}
	
	void normalize(){
		//TODO
	}
	
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	
}
