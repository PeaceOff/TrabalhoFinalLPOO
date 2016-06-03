package projeto.minigames.soccer;

import projeto.logic.GameObjectState;
import projeto.logic.State;
import projeto.logic.Vector2;

public class SlowerState implements State {

	private float timePassed = 0;
	private char once = 0;
	
	@Override
	public void update(float timeLapsed,GameObjectState gO) {
		
		timePassed += timeLapsed;
		if(timePassed >= time){
			timePassed = 0;
			gO.setM_State(new NormalState());
		}
		if(once == 1){
			return;
		}
		gO.getCollider().setVelCap(Vector2.multiply(gO.getCap(),0.5));
		once = 1;
	}

}
