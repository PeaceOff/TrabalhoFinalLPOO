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
		System.out.println("bound"	+ bound.getxI() + ","
									+ bound.getyI() + "," 
									+ bound.getxF() + ","
									+ bound.getyF()); 
		
		Rectangulo r1 = new Rectangulo(bound.getxI() - circle.getRadius(), bound.getyI(),circle.getRadius(),bound.getHeight());
		Rectangulo r2 = new Rectangulo(bound.getxI() ,bound.getyF() , bound.getWidth(), circle.getRadius());
		Rectangulo r3 = new Rectangulo(bound.getxF(), bound.getyI(), circle.getRadius(),bound.getHeight());  
		Rectangulo r4 = new Rectangulo(bound.getxI() ,bound.getyI() - circle.getRadius(),bound.getWidth(),circle.getRadius()); 
		System.out.println(circle.getPosition().x + " " + circle.getPosition().y);
		System.out.println(r1.getxI() + " , " + r1.getxF() );
		System.out.println(r1.getyI() + " - " + r1.getyF() ); 
		 
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
		System.out.println("Distancia ao Centro" + distCenter); 
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
					if(c instanceof CircleCollider || objects.get(k) instanceof CircleCollider)
						System.out.println("Colisao!!");  
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
		
		CircleCollider secondBall = (CircleCollider)c2;
		Vector2 newVel1 = new Vector2();
		Vector2 newVel2 = new Vector2();
		
		if(c1.movable){
			newVel1.x = (c1.velocity.x * (c1.getMass() - secondBall.getMass()) + (2 * secondBall.getMass() * secondBall.velocity.x)) 
				/ (c1.getMass() + secondBall.getMass());
			newVel1.y = (c1.velocity.y * (c1.getMass() - secondBall.getMass()) + (2 * secondBall.getMass() * secondBall.velocity.y)) 
				/ (c1.getMass() + secondBall.getMass());
			c1.setVelocity(newVel1);
		}
		
		if(c2.movable){
			newVel2.x = (secondBall.velocity.x * (secondBall.getMass() - c1.getMass()) + (2 * c1.getMass() * c1.velocity.x)) 
				/ (secondBall.getMass() + c1.getMass());
			newVel2.y = (secondBall.velocity.y * (secondBall.getMass() - c1.getMass()) + (2 * c1.getMass() * c1.velocity.y)) 
				/ (secondBall.getMass() + c1.getMass());
			secondBall.setVelocity(newVel2);
			
		}
		
		Vector2 colPoint = new Vector2();
		colPoint.x = ((c1.position.x * secondBall.getRadius()) + (secondBall.position.x * c1.getRadius())) /
					(c1.getRadius() + secondBall.getRadius());
		colPoint.y = ((c1.position.y * secondBall.getRadius()) + (secondBall.position.y * c1.getRadius())) /
					(c1.getRadius() + secondBall.getRadius());
		
		if(c1.movable){
			Vector2 newPos1 = new Vector2();
			Vector2 temp = Vector2.sub(c1.getPosition(), colPoint);
			temp.normalize();
			newPos1.x = colPoint.x + (temp.x * c1.getRadius());
			newPos1.y = colPoint.y + (temp.y * c1.getRadius());
			c1.setPosition(newPos1);
		}
		
		if(c2.movable){
			Vector2 newPos2 = new Vector2();
			Vector2 temp2 = Vector2.sub(secondBall.getPosition(), colPoint);
			temp2.normalize();
			newPos2.x = colPoint.x + (temp2.x * secondBall.getRadius());
			newPos2.y = colPoint.y + (temp2.y * secondBall.getRadius());
			secondBall.setPosition(newPos2);
		}
		
		return;
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
			return;
		}
	}
}