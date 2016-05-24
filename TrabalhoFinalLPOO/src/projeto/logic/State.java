package projeto.logic;

public interface State {
	
	public final int time = 5;
	
	public void update(float timeLapsed,GameObjectState gO);
}
