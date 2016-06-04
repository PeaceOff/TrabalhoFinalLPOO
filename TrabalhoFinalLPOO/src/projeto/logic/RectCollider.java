package projeto.logic;

/**
 * @author Joao e David
 * @version 1.0
 * @created 03-mai-2016 15:30:43
 */
public class RectCollider extends Collider {

	private double height;
	private double width;

	/**
	 * Contrutor de rectCollider com a variaveis de largura e comprimento
	 * @param w largura
	 * @param h comprimento
	 */
	public RectCollider(double w, double h){ 
		height = h; 
		width = w;
	}
	
	/**
	 * Construtor de rectCollider
	 * @param pos posicao do collider
	 * @param w largura
	 * @param h comprimento
	 * @param t tag do collider
	 * @param m booleano que indica se o collider se mexe
	 * @param ms massa
	 */
	public RectCollider(Vector2 pos, double w, double h,String t,boolean m,double ms){
		super(pos,t,m,ms);   
		position.x = pos.x + w/2;
		position.y = pos.y + h/2; 
		height = h;
		width = w;
	}

	@Override
	public Rectangulo getBoundingBox() {    
		Rectangulo res = new Rectangulo(this.position.x - this.width/2 ,this.position.y - this.height/2,this.width,this.height);
		return res; 
	}

}