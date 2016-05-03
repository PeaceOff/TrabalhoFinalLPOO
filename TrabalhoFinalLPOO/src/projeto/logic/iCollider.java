package projeto.logic;

/**
 * @author David
 * @version 1.0
 * @created 03-mai-2016 15:30:36
 */
public interface iCollider {

	/**
	 * 
	 * @param c
	 */
	public void onCollisionEnter(Collider c);

	/**
	 * 
	 * @param c
	 */
	public void onTriggerEnter(Collider c);

}