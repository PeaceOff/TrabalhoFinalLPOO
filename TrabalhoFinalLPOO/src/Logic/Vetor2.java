package Logic;

public class Vetor2 {
	float x;
	float y;
	
	Vetor2(){}
	
	Vetor2(float x,float y){
		this.x = x;
		this.y = y;
	}
	
	Vetor2 add(Vetor2 v){
		Vetor2 temp = new Vetor2();
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
