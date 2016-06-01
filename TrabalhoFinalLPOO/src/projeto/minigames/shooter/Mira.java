package projeto.minigames.shooter;


import projeto.logic.GameObject;
import projeto.logic.Input;
import projeto.logic.Obj;
import projeto.logic.Rectangulo;
import projeto.logic.Vector2;

public class Mira extends GameObject{
	
	public Mira(Input i) {
		super(null, i, new Obj(new Rectangulo(0,0,10),"poste.png",new Rectangulo(0,0,1,1)));
	}

	public void setPosition(Vector2 pos){
		m_Obj.setDimensions(new Rectangulo(pos.x - 5,pos.y - 5,10));
	}

	@Override
	public void update(float timeLapsed) {
		// TODO Auto-generated method stub
		
	}
	
}
