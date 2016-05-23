package projeto.logic;

public class GameObjectState extends GameObject{

	protected State m_State;
	
	public GameObjectState(Collider c, Input i, Obj o) {
		super(c, i, o);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(float timeLapsed) {
		// TODO Auto-generated method stub
		
	}

	public State getM_State() {
		return m_State;
	}

	public void setM_State(State m_State) {
		this.m_State = m_State;
	}

}
