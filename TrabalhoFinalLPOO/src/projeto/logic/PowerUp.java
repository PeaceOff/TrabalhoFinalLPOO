package projeto.logic;

import projeto.minigames.soccer.BiggerState;
import projeto.minigames.soccer.FasterState;
import projeto.minigames.soccer.SlowerState;
import projeto.minigames.soccer.SmallerState;

public class PowerUp extends GameObject {
	
	private State escolhido;

	public PowerUp(int comprimento, int largura) {
		
		super(new CircleCollider(20,0,new Vector2(),"PowerUp", false, 1)
			 ,null
			 ,new Obj(new Rectangulo(100,100, 50), "powerups.png", new Rectangulo(0,0,1,1)));
		 
		
		double x = (Math.random() * (comprimento * 0.9)) + (comprimento * 0.05);
		double y = (Math.random() * (largura * 0.9)) + (largura * 0.05);
		super.getCollider().setPosition(new Vector2(x,y));
		m_Collider.addListener(this);
		m_Collider.trigger = true;
		int choice =(int)(Math.random() * 4); 
		  
		switch(choice){
		case 0:
			escolhido = new BiggerState();
			System.out.println("Gerei um powerup MAIOR");
			break;
		case 1:
			escolhido = new SmallerState();
			System.out.println("Gerei um powerup MENOR");
			break;
		case 2:
			escolhido = new FasterState();
			System.out.println("Gerei um powerup RAPIDO");
			break;
		case 3:
			escolhido = new SlowerState();
			System.out.println("Gerei um powerup LENTO");
			break;
		}
		
		m_Obj.setSubImage(new Rectangulo(choice/4f,0,1/4f,1));     
		 
		
	}
	
	public State getState(){
		return escolhido;
	}
	
	@Override
	public void update(float timeLapsed) {
		// Podemos por os powerups a mexerem-se i guess
	}

	@Override
	public void onCollisionEnter(Collider c) {
		super.onCollisionEnter(c);
	}

	@Override
	public void onTriggerEnter(Collider c) {
		this.destroy();
		super.onTriggerEnter(c);
	}

}
