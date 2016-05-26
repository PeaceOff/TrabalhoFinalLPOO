package projeto.logic;

public class NormalState implements State {
	
	private char once = 0;

	@Override
	public void update(float timeLapsed, GameObjectState gO) {
		if(once == 1){
			return;
		}
		((CircleCollider)gO.getCollider()).setRadius(gO.getSize());
		gO.m_Collider.setVelCap(gO.getCap());

		once = 1;
	}

}
