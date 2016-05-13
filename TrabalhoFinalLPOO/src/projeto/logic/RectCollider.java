package projeto.logic;

/**
 * @author David
 * @version 1.0
 * @created 03-mai-2016 15:30:43
 */
public class RectCollider extends Collider {

	private double height;
	private double width;

	public RectCollider(double w, double h){ 
		height = h; 
		width = w;
	}
	
	public RectCollider(Vector2 pos, double w, double h,String t,boolean m,double ms){
		super(pos,t,m,ms);   
		position.x = pos.x + w/2;
		position.y = pos.y + h/2; 
		height = h;
		width = w;
	}

	@Override
	public Rectangulo getBoundingBox() {    
		Rectangulo res = new Rectangulo(this.position.x - this.width/2 ,this.position.y - this.height/2,this.width,this.height);
		return res; 
	}

	@Override
	public void onCollisionEnter(Collider c) {	
	}

	@Override
	public void onTriggerEnter(Collider c) {
		// TODO Auto-generated method stub
		
	}

}