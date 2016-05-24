package projeto.logic;

/**
 * @author David
 * @version 1.0
 * @created 03-mai-2016 15:30:30
 */
public class CircleCollider extends Collider {

	private double radius;
	private double elasticity;

	public CircleCollider(double r){
		radius = r;
	} 
	
	public CircleCollider(double r, double e, Vector2 pos, String t,boolean m,double ms){
		super(pos,t,m,ms);
		radius = r;
		elasticity = e;
	}
	
	public CircleCollider(double r, double x, double y){
		radius = r;
		this.position.x = x;
		this.position.y = y; 
	}

	public double getMass(){
		return mass;
	}
	
	public void setMass(double m){
		this.mass = m;
	}
	
	@Override
	public Rectangulo getBoundingBox() {  
		Rectangulo res = new Rectangulo(this.position.x - radius,this.position.y - radius,radius * 2);
		return res;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public double getElasticity() {
		return elasticity;
	}

	public void setElasticity(double elasticity) {
		this.elasticity = elasticity;
	}

}