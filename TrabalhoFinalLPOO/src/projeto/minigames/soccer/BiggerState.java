package projeto.minigames.soccer;

import projeto.logic.CircleCollider;
import projeto.logic.GameObjectState;
import projeto.logic.State;

public class BiggerState implements State {

	private float timePassed = 0;
	
	@Override
	public void update(float timeLapsed,GameObjectState gO) {
		((CircleCollider)gO.getCollider()).setRadius(gO.getSize() * 2);
		timePassed += timeLapsed;
		if(timePassed >= time){
			timePassed = 0;
			gO.setM_State(new NormalState());
		}
		
	}

}
