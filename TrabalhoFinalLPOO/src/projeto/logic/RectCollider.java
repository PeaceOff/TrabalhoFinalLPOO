package projeto.logic;

/**
 * @author David
 * @version 1.0
 * @created 03-mai-2016 15:30:43
 */
public class RectCollider extends Collider {

	private double height;
	private double width;

	public RectCollider(double h, double w){
		height = h;
		width = w;
	}
	
	public RectCollider(double h, double w, Vector2 pos, String t,boolean m){
		super(pos,t,m);
		height = h;
		width = w;
	}

	@Override
	public Rectangulo getBoundingBox() {
		Rectangulo res = new Rectangulo(this.position.x,this.position.y,this.height,this.width);
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