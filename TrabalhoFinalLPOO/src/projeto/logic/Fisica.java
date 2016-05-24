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
	 
	public Fisica(){
		objects = new ArrayList<Collider>();
	}
	
	public void addObject(Collider c){
		objects.add(c);
	}
	 
	public void removeObject(Collider c){
		objects.remove(c);
	}
	
	public boolean checkColision(Collider c1, Collider c2){
		
		Rectangulo rect1 = c1.getBoundingBox();
		Rectangulo rect2 = c2.getBoundingBox();
		
		if(	rect1.getxI() < rect2.getxF() && rect1.getxF() > rect2.getxI() &&
			rect1.getyI() < rect2.getyF() && rect1.getyF() > rect2.getyI() ){//Houve colisao 
			
			if(c1 instanceof RectCollider && c2 instanceof RectCollider)//Se ambos sao rectangulos existe colisao
				return true; 
			else if(c1 instanceof CircleCollider && c2 instanceof CircleCollider)
				return checkCirclesColision((CircleCollider)c1,(CircleCollider)c2);
			
			else if(c1 instanceof RectCollider && c2 instanceof CircleCollider)
				return checkCollisionTwo((RectCollider)c1,(CircleCollider)c2);
				
			else if(c2 instanceof RectCollider && c1 instanceof CircleCollider)
				return checkCollisionTwo((RectCollider)c2,(CircleCollider)c1);	
		} 
		
		return false;
	}
	
	private boolean checkCollisionTwo(RectCollider rC,CircleCollider circle){//THE PROBLEM LIES HERE
		
		Rectangulo bound = rC.getBoundingBox();

		
		Rectangulo r1 = new Rectangulo(bound.getxI() - circle.getRadius(), bound.getyI(),circle.getRadius(),bound.getHeight());
		Rectangulo r2 = new Rectangulo(bound.getxI() ,bound.getyF() , bound.getWidth(), circle.getRadius());
		Rectangulo r3 = new Rectangulo(bound.getxF(), bound.getyI(), circle.getRadius(),bound.getHeight());  
		Rectangulo r4 = new Rectangulo(bound.getxI() ,bound.getyI() - circle.getRadius(),bound.getWidth(),circle.getRadius());  
		 
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
		
		return pointInRect(bound,circle.getPosition());
		
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

	public void update(float timeLapsed){
		
		for(Collider c : objects)
			c.update(timeLapsed);
		
		for(Collider c : objects){
			for(int k = objects.indexOf(c) + 1; k < objects.size(); k++){
				if(checkColision(c,objects.get(k))){//True = colisao
					if(c.isTrigger() || objects.get(k).isTrigger()){
						objects.get(k).onTriggerEnter(c);
						c.onTriggerEnter(objects.get(k));
						continue;
					}
					c.onCollisionEnter(objects.get(k));
					objects.get(k).onCollisionEnter(c);
					dealWithCollision(c,objects.get(k));
				}
			}
		}
	}
	
	private void dealWithCollision(Collider c1, Collider c2){
		if(c1.getClass() == CircleCollider.class && c2.getClass() == CircleCollider.class){
			dealWithCollision((CircleCollider)c1, (CircleCollider)c2);
			return;
		}
		if(c1.getClass() == RectCollider.class && c2 instanceof CircleCollider ){
			dealWithCollision((CircleCollider)c2, (RectCollider)c1);
			return;
		} else if (c2 instanceof CircleCollider && c1 instanceof RectCollider){
			dealWithCollision((CircleCollider)c1, (RectCollider)c2);
			return;
		}
	}
	
	private void dealWithCollision(CircleCollider c1, CircleCollider c2){//Player x Player || Player x Ball
		
		
		Vector2 colPoint = new Vector2();
		colPoint.x = ((c1.position.x * c2.getRadius()) + (c2.position.x * c1.getRadius())) /
					(c1.getRadius() + c2.getRadius());
		colPoint.y = ((c1.position.y * c2.getRadius()) + (c2.position.y * c1.getRadius())) /
					(c1.getRadius() + c2.getRadius());
		
		
		Vector2  normalVector = Vector2.sub(c2.position, c1.position);
		normalVector.normalize();
		Vector2 tangentVector  = new Vector2(normalVector.y * -1, normalVector.x);
		//System.out.println("Normal:" + normalVector.getNorm()); 
        // create ball scalar normal direction.
        double ball1scalarNormal =  normalVector.dot(c1.velocity);
        double ball2scalarNormal = normalVector.dot(c2.velocity);

        // create scalar velocity in the tagential direction.
        double ball1scalarTangential = tangentVector.dot(c1.velocity); 
        double ball2scalarTangential = tangentVector.dot(c2.velocity); 
        
        if(c1.movable){
	        double ball1ScalarNormalAfter = (ball1scalarNormal * (c1.getMass() - c2.getMass()) + 2 * c2.getMass() * ball2scalarNormal) / (c1.getMass() + c2.getMass());
	        Vector2 ball1scalarNormalAfter_vector = Vector2.multiply(normalVector,ball1ScalarNormalAfter); // ball1Scalar normal doesnt have multiply not a vector.
	        Vector2 ball1ScalarNormalVector = (Vector2.multiply(tangentVector,ball1scalarTangential));
	        c1.velocity = Vector2.multiply(Vector2.add(ball1ScalarNormalVector,ball1scalarNormalAfter_vector),c1.getElasticity());
	        Vector2 temp = Vector2.sub(c1.position, colPoint);
	        temp.normalize();
	        c1.setPosition(Vector2.add(colPoint,Vector2.multiply(temp, c1.getRadius())));
        }
        if(c2.movable){ 
	        double ball2ScalarNormalAfter = (ball2scalarNormal * (c2.getMass() - c1.getMass()) + 2 * c1.getMass() * ball1scalarNormal) / (c1.getMass() + c2.getMass());
	        Vector2 ball2scalarNormalAfter_vector = Vector2.multiply(normalVector,ball2ScalarNormalAfter);
	        Vector2 ball2ScalarNormalVector = (Vector2.multiply(tangentVector,ball2scalarTangential));;
	        c2.velocity = Vector2.multiply(Vector2.add(ball2ScalarNormalVector,ball2scalarNormalAfter_vector),c2.getElasticity());
	        Vector2 temp = Vector2.sub(c2.position, colPoint);
	        temp.normalize();  
	        c2.setPosition(Vector2.add(colPoint, Vector2.multiply(temp, c2.getRadius())));
        }
	}
	
	private void dealWithCollision(CircleCollider c1, RectCollider r1){
		
		Rectangulo r = r1.getBoundingBox();
		if(c1.position.x > r.getxI() && c1.position.x < r.getxF()) 
		{
			c1.velocity.y = -c1.velocity.y;
			if(c1.position.y > r1.getPosition().y)//Circulo em baixo
			{
					c1.position.y = r.getyF() + c1.getRadius();
			} else {
					c1.position.y = r.getyI() - c1.getRadius();
			}
			c1.velocity = Vector2.multiply(c1.velocity, c1.getElasticity());
			return; 
		}
		
		if(c1.position.y > r.getyI() && c1.position.y < r.getyF()){
			c1.velocity.x = -c1.velocity.x;   
			if(c1.position.x > r1.getPosition().x) 
			{
					c1.position.x = r.getxF() + c1.getRadius();
			} else {
					c1.position.x = r.getxI() - c1.getRadius();
			}
			c1.velocity = Vector2.multiply(c1.velocity, c1.getElasticity());
			return;
		}
	}
}