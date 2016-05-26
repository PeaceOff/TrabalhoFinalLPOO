package projeto.logic;

public class SlowerState implements State {

	private float timePassed = 0;
	private char once = 0;
	private Vector2 velCap;
	@Override
	public void update(float timeLapsed,GameObjectState gO) {
		
		timePassed += timeLapsed;
		if(timePassed >= time){
			timePassed = 0;
			gO.getCollider().setVelCap(velCap);
			gO.setM_State(new NormalState());
		}
		if(once == 1){
			return;
		}
		velCap = gO.getCollider().getVelCap(); 
		gO.getCollider().setVelCap(Vector2.multiply(velCap,0.5));
		once = 1;
	}

}
