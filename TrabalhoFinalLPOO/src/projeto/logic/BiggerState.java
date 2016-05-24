package projeto.logic;

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
