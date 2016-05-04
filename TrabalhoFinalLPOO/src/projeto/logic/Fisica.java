package projeto.logic;

import java.util.ArrayList;

/**
 * Singleton
 * @author David
 * @version 1.0
 * @created 03-mai-2016 15:30:34
 */
public class Fisica {

	public ArrayList<Collider> objects;
	private static Fisica fis = null;

	private Fisica(){
		objects = new ArrayList<Collider>();
	}
	
	public static Fisica getInstance(){
		if(fis == null){
			synchronized (Fisica.class) {
				if (fis == null) {
					fis = new Fisica();
				} 
			}
		}
		return fis;
	}
	
	public void addObject(Collider c){
		objects.add(c);
	}
	
	public boolean checkColision(Collider c1, Collider c2){
		
		Rectangulo rect1 = c1.getBoundingBox();
		Rectangulo rect2 = c2.getBoundingBox();
		
		if(	rect1.getxI() < rect2.getxF() && rect1.getxF() > rect2.getxI() &&
			rect1.getyI() < rect2.getyF() && rect1.getyF() > rect2.getyI() ){//Houve colisao
			
			if(c1.getClass() == RectCollider.class && c2.getClass() == RectCollider.class){//Se ambos sao rectangulos existe colisao
				return true;
			} else {//Ha colisao mas um é circulo e outro rectangulo ou ambos sao circulos
				
				if(c1.getClass() == CircleCollider.class && c2.getClass() == CircleCollider.class){
					return checkCirclesColision((CircleCollider)c1,(CircleCollider)c2);
				}
				
				if(c1.getClass() == RectCollider.class){
					return checkCollisionTwo((RectCollider)c1,(CircleCollider)c2);
				} else {
					return checkCollisionTwo((RectCollider)c2,(CircleCollider)c1);
				}
				
			}
		}
		return false;
	}
	
	private boolean checkCollisionTwo(RectCollider rC,CircleCollider circle){//THE PROBLEM LIES HERE
		Rectangulo bound = rC.getBoundingBox();
		Rectangulo r1 = new Rectangulo(bound.getxI() - (circle.getRadius()/2),rC.getPosition().y,bound.getHeight(),circle.getRadius());
		Rectangulo r2 = new Rectangulo(rC.getPosition().x,bound.getyF() + (circle.getRadius()/2),circle.getRadius(),bound.getWidth());
		Rectangulo r3 = new Rectangulo(bound.getxF() + (circle.getRadius()/2),rC.getPosition().y,bound.getHeight(),circle.getRadius());
		Rectangulo r4 = new Rectangulo(rC.getPosition().x,bound.getyI() - (circle.getRadius()/2),circle.getRadius(),bound.getWidth());
		if (pointInRect(r1,circle.getPosition()) ||
			pointInRect(r2,circle.getPosition()) ||
			pointInRect(r3,circle.getPosition()) ||
			pointInRect(r4,circle.getPosition()))
			return true;
		
		CircleCollider c1 = new CircleCollider(circle.getRadius(),bound.getxI(),bound.getyF());
		CircleCollider c2 = new CircleCollider(circle.getRadius(),bound.getxF(),bound.getyF());
		CircleCollider c3 = new CircleCollider(circle.getRadius(),bound.getxI(),bound.getyI());
		CircleCollider c4 = new CircleCollider(circle.getRadius(),bound.getxF(),bound.getyI());
		if (pointInCircle(c1,circle.getPosition()) ||
			pointInCircle(c2,circle.getPosition()) ||
			pointInCircle(c3,circle.getPosition()) ||
			pointInCircle(c4,circle.getPosition()))
			return true;
		return false;
		
	}
	
	private boolean pointInRect(Rectangulo r,Vector2 p){
		return (p.x <= r.getxF() && p.x >= r.getxI() && p.y <= r.getyF() && p.y >= r.getyI());
	}
	
	private boolean pointInCircle(CircleCollider c, Vector2 p){
		double distCenter = Vector2.distance(c.getPosition(), p);
		return (distCenter <= c.getRadius());
	}
	
	private boolean checkCirclesColision(CircleCollider c1, CircleCollider c2){
		double distMin = c1.getRadius() + c2.getRadius();
		double distCenters = Vector2.distance(c1.getPosition(), c2.getPosition());
		return (distCenters <= distMin);
	}

	/**
	 * 
	 * @param timeLapsed
	 */
	public void update(float timeLapsed){
		
		for(Collider c : objects){
			
			c.position.x += c.getVelocity().x * timeLapsed;
			c.position.y += c.getVelocity().y * timeLapsed;
			Vector2 newVelocity = new Vector2();
			newVelocity.x = c.getVelocity().x - (c.getDrag().x  * timeLapsed);
			newVelocity.y = c.getVelocity().y - (c.getDrag().y * timeLapsed);
			c.setVelocity(newVelocity);
			
			for(int k = objects.indexOf(c) + 1; k < objects.size(); k++){
				if(checkColision(c,objects.get(k))){//True = colisao
					c.onCollisionEnter(objects.get(k));
				}
			}
		}
	}

}