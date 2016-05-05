package projeto.logic;

/**
 * @author PeaceOff
 * @version 1.0
 * @created 03-mai-2016 15:30:42
 */
public class Player extends GameObject {

	private int id;
	private String nome;

	public Player(int _id, String n, Collider c, Input i, Obj o){
		super(c, i, o);
		id = _id;
		nome = n;
	}

	public void start(){

	}

	public void update(float timeLapsed){
		PlayerInput pIn = this.m_Input.getPlayerInput(id);
		this.m_Collider.velocity.add(pIn.getDirection());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}