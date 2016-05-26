package projeto.logic;

public class PowerUp extends GameObject {
	
	public static enum PowerType {
		Faster,
		Slower,
		Bigger,
		Smaller
	}
	
	private PowerType pType;
	

	public PowerUp(int comprimento, int largura) {
		
		super(new CircleCollider(10,0,new Vector2(),"PowerUp", false, 1),null,new Obj(new Rectangulo(100,100, 10), "circulo.png", new Rectangulo(0,0,1,1)));
		
		double x = (Math.random() * (comprimento * 0.9)) + (comprimento * 0.05);
		double y = (Math.random() * (largura * 0.9)) + (largura * 0.05);
		super.getCollider().setPosition(new Vector2(x,y));
		m_Collider.addListener(this);
		m_Collider.trigger = true;
		double choice = Math.random() * 4;
		if(choice < 1){
			pType = PowerUp.PowerType.Faster;
			System.out.println("Gerei um powerup RAPIDO");
		} else if (choice < 2){
			pType = PowerUp.PowerType.Slower;
			System.out.println("Gerei um powerup LENTO");
		} else if (choice < 3){
			pType = PowerUp.PowerType.Bigger;
			System.out.println("Gerei um powerup MAIOR");
		} else if (choice <= 4){
			pType = PowerUp.PowerType.Smaller;
			System.out.println("Gerei um powerup MENOR");
		}
		
	}

	public PowerType getPowerType(){
		return pType;
	}
	
	@Override
	public void update(float timeLapsed) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCollisionEnter(Collider c) {
		super.onCollisionEnter(c);
	}

	@Override
	public void onTriggerEnter(Collider c) {
		if(c.getGameObj() instanceof GameObjectState){
			switch (pType) {
			case Faster:
				((GameObjectState)c.getGameObj()).setM_State(new FasterState());
				break;
			case Slower:
				((GameObjectState)c.getGameObj()).setM_State(new SlowerState());
				break;
			case Bigger:
				((GameObjectState)c.getGameObj()).setM_State(new BiggerState());
				break;
			case Smaller:
				((GameObjectState)c.getGameObj()).setM_State(new SmallerState());
				break;
			default:
				break;
			}
		}
		this.m_Collider.destroy = 1;
		super.onTriggerEnter(c);
	}

}
