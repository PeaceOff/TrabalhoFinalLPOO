package Logic;

import java.util.ArrayList;

/**
 * Singleton
 * @author David
 * @version 1.0
 * @created 03-mai-2016 15:30:34
 */
public class Fisica {

	public ArrayList<Collider> objects;
	private static Fisica fis = null;

	private Fisica(){
		objects = new ArrayList<Collider>();
	}
	
	public static Fisica getInstance(){
		if(fis == null){
			synchronized (Fisica.class) {
				if (fis == null) {
					fis = new Fisica();
				} 
			}
		}
		return fis;
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