package Game.Logic;


/**
 * Singleton
 * @author David
 * @version 1.0
 * @created 03-mai-2016 15:30:34
 */
public class Fisica {

	public ArrayList<Collider> objects;

	public Fisica(){
		objects = new ArrayList<Collider>();
	}
	
	public void addObject(Collider c){
		objects.add(c);
	}

	public void finalize() throws Throwable {

	}

	/**
	 * 
	 * @param timeLapsed
	 */
	public void update(float timeLapsed){

	}

}