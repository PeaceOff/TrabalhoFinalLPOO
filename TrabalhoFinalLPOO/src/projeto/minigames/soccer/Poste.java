package projeto.minigames.soccer;

import projeto.logic.CircleCollider;
import projeto.logic.GameObject;
import projeto.logic.Input;
import projeto.logic.Obj;
import projeto.logic.Rectangulo;
import projeto.logic.Vector2;

public class Poste extends GameObject {
	
	public Poste(Input i,Vector2 pos, int size, int mass){
		super(new CircleCollider(size, 0 ,pos, "", false, mass), i, new Obj(new Rectangulo(), "poste.png", new Rectangulo(0, 0, 1 ,1)));
		m_Collider.addListener(this);
	}

	@Override
	public void update(float timeLapsed) {
		// TODO Auto-generated method stub
		
	}
	
}
