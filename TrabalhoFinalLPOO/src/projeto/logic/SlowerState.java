package projeto.logic;

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
		gO.getCollider().setDrag(Vector2.multiply(gO.getDrag(),2));
		once = 1;
	}

}
