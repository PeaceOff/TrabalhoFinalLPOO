package projeto.minigames.soccer;

import projeto.logic.CircleCollider;
import projeto.logic.GameObjectState;
import projeto.logic.State;

public class NormalState implements State {
	
	private char once = 0;

	@Override
	public void update(float timeLapsed, GameObjectState gO) {
		if(once == 1){
			return;
		}
		((CircleCollider)gO.getCollider()).setRadius(gO.getSize());
		gO.getCollider().setVelCap(gO.getCap());

		once = 1;
	}

}
