package projeto.logic;

public class Bola extends GameObjectState {
	
	private int lastID = -1;
	private int lastSecondID = -1;
	private iMinigameTools myTools;
	private final static int size = 10;
	private final static double elast = 0.9;
	private final Vector2 sDrag = new Vector2(20,20);
	private final Vector2 vCap = new Vector2(5000,5000);
	
	public Bola(Input i, Vector2 pos,iMinigameTools t){
		super(new CircleCollider(size,elast,pos,"ball", true, 30),i,new Obj(new Rectangulo(pos.x,pos.y, size,size), "bola.png", new Rectangulo(0,0,1,1)));   
		m_Collider.setDrag(sDrag);
		m_Collider.setVelCap(vCap);
		m_Collider.addListener(this);
		myTools = t;
	}
	  
	@Override
	public void update(float timeLapsed) {
		super.update(timeLapsed);
	}
	
	@Override
	public void onCollisionEnter(Collider c) {
		if(c.tag.contains("Player")){
			setLastID(Character.getNumericValue(c.tag.charAt(6)));
		}
		super.onCollisionEnter(c);
	}
	
	@Override
	public final int getSize(){
		return size;
	}

	public int getLastID() {
		return lastID;
	}

	public void setLastID(int lastID) {
		this.lastSecondID = this.lastID;
		this.lastID = lastID;
	}
	
	public int getLastSecondID() {
		return lastSecondID;
	}

	@Override
	public void onTriggerEnter(Collider c) {
		if(c.tag.contains("Baliza")){
			if(lastID != -1){
				myTools.sendEst(lastID,new Estatistica("SoccerGame","Golos Marcados",1));
			}
			if(lastSecondID != -1 && lastID != lastSecondID){
				myTools.sendEst(lastSecondID,new Estatistica("SoccerGame","Assistencias",1));
			}
			if(lastID != -1 && !c.tag.contains("" + (lastID % 2))){//Situacao de auto-golo
				myTools.sendEst(lastID,new Estatistica("SoccerGame","Auto-Golos",1));
				System.out.println("NOPE");
			}
			if(lastSecondID != -1 && lastSecondID != lastID && !c.tag.contains("" + (lastSecondID % 2))) {
				myTools.sendEst(lastSecondID, new Estatistica("SoccerGame","Auto-Assistencias",1));
			}
		}
		super.onTriggerEnter(c);
	}
	
	public Vector2 getCap(){
		return vCap;
	}
}
