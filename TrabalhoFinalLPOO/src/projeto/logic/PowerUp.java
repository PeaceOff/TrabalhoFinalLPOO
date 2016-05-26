package projeto.logic;

public class PowerUp extends GameObject {
	
	private enum PowerType {
		Faster,
		Slower,
		Bigger,
		Smaller
	}
	
	private State escolhido;

	public PowerUp(int comprimento, int largura) {
		
		super(new CircleCollider(10,0,new Vector2(),"PowerUp", false, 1)
			 ,null
			 ,new Obj(new Rectangulo(100,100, 10), "players.png", new Rectangulo(0,0,1,1)));
		
		
		double x = (Math.random() * (comprimento * 0.9)) + (comprimento * 0.05);
		double y = (Math.random() * (largura * 0.9)) + (largura * 0.05);
		super.getCollider().setPosition(new Vector2(x,y));
		m_Collider.addListener(this);
		m_Collider.trigger = true;
		int choice =(int)(Math.random() * 4);  
		if(choice < 1){
			escolhido = new FasterState();
			System.out.println("Gerei um powerup RAPIDO");
		} else if (choice < 2){
			escolhido = new SlowerState();
			System.out.println("Gerei um powerup LENTO");
		} else if (choice < 3){
			escolhido = new BiggerState();
			System.out.println("Gerei um powerup MAIOR");
		} else if (choice <= 4){  
			escolhido = new SmallerState();
			System.out.println("Gerei um powerup MENOR");
		} 
		m_Obj.setSubImage(new Rectangulo(choice/8f,0,1/8f,1));     
		 
		
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
		this.m_Collider.destroy = 1;
		super.onTriggerEnter(c);
	}

}
