package projeto.logic;

public class NormalState implements State {

	@Override
	public void update(float timeLapsed, GameObjectState gO) {
		((CircleCollider)gO.getCollider()).setRadius(gO.getSize());
	}

}
