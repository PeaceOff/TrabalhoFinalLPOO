package projeto.logic;

/**
 * @author David
 * @version 1.0
 * @created 03-mai-2016 15:30:30
 */
public class CircleCollider extends Collider {

	private double radius;

	public CircleCollider(){
		radius = 0;
	}
	
	public CircleCollider(double r){
		radius = r;
	}
	
	public CircleCollider(double r, double x, double y){
		radius = r;
		this.position.x = x;
		this.position.y = y;
	}

	public double getMass(){
		if(this.tag == "Ball")
			return 0.8;//Bola 800grams
		return 1;//Jogador 1 kilo
	}
	
	@Override
	public Rectangulo getBoundingBox() {
		Rectangulo res = new Rectangulo(this.position.x,this.position.y,radius * 2);
		return res;
	}

	@Override
	public void onCollisionEnter(Collider c) {
		if(c.getClass() == CircleCollider.class)//Jogador ou bola
		{
			CircleCollider secondBall = (CircleCollider)c;
			Vector2 newVel1 = new Vector2();
			Vector2 newVel2 = new Vector2();
			newVel1.x = (this.velocity.x * (this.getMass() - secondBall.getMass()) + (2 * secondBall.getMass() * secondBall.velocity.x)) 
					/ (this.getMass() + secondBall.getMass());
			newVel1.y = (this.velocity.y * (this.getMass() - secondBall.getMass()) + (2 * secondBall.getMass() * secondBall.velocity.y)) 
					/ (this.getMass() + secondBall.getMass());
			newVel2.x = (secondBall.velocity.x * (secondBall.getMass() - this.getMass()) + (2 * this.getMass() * this.velocity.x)) 
					/ (secondBall.getMass() + this.getMass());
			newVel2.y = (secondBall.velocity.y * (secondBall.getMass() - this.getMass()) + (2 * this.getMass() * this.velocity.y)) 
					/ (secondBall.getMass() + this.getMass());
			
			this.setVelocity(newVel1);
			this.position.x += newVel1.x;
			this.position.y += newVel1.y;
			secondBall.setVelocity(newVel2);
			secondBall.position.x += newVel2.x;
			secondBall.position.y += newVel2.y;
			return;
			
		}
		if(this.tag == "Ball" && c.tag == "Goal"){
			//Score++
			return;
		}
		//Cima ou Baixo this.velocity.y = -this.velocity.y;
		//Direita ou Esquerda this.velocity.x = -this.velocity.x;
		
		
	}
	/*Ponto de Colisao
 			Vector2 colPoint = new Vector2();
			colPoint.x = ((this.position.x * secondBall.radius) + (secondBall.position.x * this.radius)) /
							(this.radius + secondBall.radius);
			colPoint.y = ((this.position.y * secondBall.radius) + (secondBall.position.y * this.radius)) /
							(this.radius + secondBall.radius);
	*/

	@Override
	public void onTriggerEnter(Collider c) {
		// TODO Auto-generated method stub
		
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

}