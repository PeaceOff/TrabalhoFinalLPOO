package projeto.logic;

import projeto.minigames.soccer.NormalState;
import projeto.minigames.soccer.PowerUp;
/**
 * @author Joao e David
 * @version 1.0
 * @created 03-mai-2016 15:32:26
 */
public abstract class GameObjectState extends GameObject{

	protected State m_State;
	
	/**
	 * Construtor da classe 
	 * @param c Collider do objecto
	 * @param i Input para o objecto
	 * @param o Obj do objeto
	 */
	public GameObjectState(Collider c, Input i, Obj o) {
		super(c, i, o);
		m_State = new NormalState();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(float timeLapsed) {
		m_State.update(timeLapsed, this);
	}

	@Override
	public void onTriggerEnter(Collider c) {
		if(c.tag == "PowerUp"){
			
			m_State = ((PowerUp)c.getGameObj()).getState();
	
		}
		super.onTriggerEnter(c);
	}
	
	/**
	 * Funcao get para o state
	 * @return state do objecto
	 */
	public State getM_State() {
		return m_State;
	}

	/**
	 * Funcao set para o state
	 * @param m_State novo state do objecto
	 */
	public void setM_State(State m_State) {
		this.m_State = m_State;
	}
	
	/**
	 * Funcao para dar reset ao state
	 */
	public void resetState(){
		this.m_State = new NormalState();
	}

	/**
	 * Funcao get para o tamanho
	 * @return tamanho do objecto
	 */
	public abstract int getSize();
	
	/**
	 * Funcao get para o cap da velocidade
	 * @return cap da velocidade
	 */
	public abstract Vector2 getCap();
}
