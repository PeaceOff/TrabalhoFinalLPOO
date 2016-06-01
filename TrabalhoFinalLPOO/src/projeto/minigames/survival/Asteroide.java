package projeto.minigames.survival;

import projeto.logic.CircleCollider;
import projeto.logic.Collider;
import projeto.logic.GameObject;
import projeto.logic.Input;
import projeto.logic.Obj;
import projeto.logic.Rectangulo;
import projeto.logic.Vector2;

public class Asteroide extends GameObject {

	private static int gravity = 100;
	private int toques = 10;
	private int raioMaximo = 10;
	private int raio = 0;
	public Asteroide(Vector2 position, int radius , Vector2 velo, int raioMaximo) {
		
		super(new CircleCollider(radius, 1, position, "Asteroide", true, 80)
				, null
				, new Obj(new Rectangulo(), "asteroide.png", new Rectangulo(0, 0, 1,1)));
		this.raioMaximo =  raioMaximo;
		raio = radius;
		m_Collider.addListener(this);
		m_Collider.setVelCap(new Vector2(1000, 1000));
		m_Collider.setVelocity(velo);
	}

	@Override
	public void update(float timeLapsed) {
		
		m_Collider.addVelocity(new Vector2(0, 1));
		
	}
	
	
	public int getPontos(){
		return raioMaximo - raio;
	}
	
	@Override
	public void onTriggerEnter(Collider c) {
		System.out.println(c.getTag());
		super.onTriggerEnter(c);
	}
	@Override
	public void onCollisionEnter(Collider c) {
		toques --;
		super.onCollisionEnter(c);
		System.out.println(c.getTag());
		if(toques <= 0)
			destroy();
	}
}
