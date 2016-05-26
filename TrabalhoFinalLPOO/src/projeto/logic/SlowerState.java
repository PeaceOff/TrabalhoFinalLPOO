package projeto.logic;

public class SlowerState implements State {

	private float timePassed = 0;
	private char once = 0;

	@Override
	public void update(float timeLapsed,GameObjectState gO) {
		if(once == 1){
			return;
		}
		/*Substituir por drag
		if(gO instanceof Player){
			((Player)gO).setSpeedMult(0.5);
		} else if (gO instanceof Bola){
			((Bola)gO).getCollider().setVelocity(Vector2.multiply(((Bola)gO).getCollider().getVelocity(), 0.5));
			once = 1;
			return;
		}*/
		
		timePassed += timeLapsed;
		if(timePassed >= time){
			timePassed = 0;
			gO.setM_State(new NormalState());
		}
		
	}

}
