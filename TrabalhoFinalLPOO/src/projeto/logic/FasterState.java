package projeto.logic;

public class FasterState implements State {

	private float timePassed = 0;
	
	@Override
	public void update(float timeLapsed,GameObjectState gO) {
		if(gO instanceof Player){
			((Player)gO).setSpeedMult(1.5);
		}
		
		timePassed += timeLapsed;
		if(timePassed >= time){
			timePassed = 0;
			gO.setM_State(new NormalState());
		}
	}

}
