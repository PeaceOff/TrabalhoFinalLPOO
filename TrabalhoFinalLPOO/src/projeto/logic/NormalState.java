package projeto.logic;

public class NormalState implements State {

	@Override
	public void update(float timeLapsed, GameObject gO) {
		Collider c = gO.getCollider();
		if(c instanceof CircleCollider){
			CircleCollider cC = (CircleCollider)c;
			cC.setRadius(cC.getRadius() + timeLapsed);
		}

	}

}
