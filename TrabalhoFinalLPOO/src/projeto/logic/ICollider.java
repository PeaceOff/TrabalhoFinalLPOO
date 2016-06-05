package projeto.logic;

/**
 * @author Joao e David
 * @version 1.0
 * @created 03-mai-2016 15:30:36
 */
public interface ICollider {

	/**
	 * Funcao para quando ocorre colisao
	 * @param c Collider com o qual colidiu
	 */
	public void onCollisionEnter(Collider c);

	/**
	 * Funcao para quando ocorre colisao com um trigger
	 * @param c Collider com o qual colidiu
	 */
	public void onTriggerEnter(Collider c);

}