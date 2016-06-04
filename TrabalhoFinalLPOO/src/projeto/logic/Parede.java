package projeto.logic;

/**
 * @author Joao e David
 * @version 1.0
 * @created 03-mai-2016 15:30:40
 */
public class Parede extends GameObject{
	
	/**
	 * Construtor da classe Parede
	 * @param i Input para este objeto
	 * @param pos posicao do objecto
	 * @param size tamanho da parede
	 */
	public Parede(Input i, Vector2 pos, Vector2 size) {
		super(new RectCollider(pos,size.x,size.y, "Parede" ,false,2000), i, new Obj(new Rectangulo(pos.x,pos.y,size.x,size.y)
				,"parede.png" 
				, new Rectangulo(0,0,1,1))); 
		m_Collider.addListener(this);
	} 

	@Override
	public void update(float timeLapsed) {
		// TODO Auto-generated method stub	
	}
}
