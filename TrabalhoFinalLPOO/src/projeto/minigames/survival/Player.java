package projeto.minigames.survival;

import projeto.logic.CircleCollider;
import projeto.logic.Collider;
import projeto.logic.Estatistica;
import projeto.logic.GameObject;
import projeto.logic.Input;
import projeto.logic.Obj;
import projeto.logic.Rectangulo;
import projeto.logic.Vector2;
import projeto.logic.iMinigameTools;

public class Player extends GameObject {
	
	private int id;
	private final static int size = 30;
	private final static float elast = 1.0f;
	private final static int gravity= 30;
	private iMinigameTools tools;
	private float tempo = 0;
	public Player(Input i, int _id , Vector2 pos, iMinigameTools mgTools) {
		
		super(new CircleCollider(size,elast, pos, "Player" + _id, true, 70)
				, i
				, new Obj(new Rectangulo(), "players.png", new Rectangulo(_id*1/8f, 0, 1/8f ,1)));
		id = _id;
		tools = mgTools;
		m_Collider.addListener(this);
		m_Collider.setVelocity(new Vector2(9,2000));
		m_Collider.setVelCap(new Vector2(200, 200)); 
		m_Collider.setDrag(new Vector2(200, 200)); 
		
		
	}

	@Override
	public void update(float timeLapsed) {
		tempo+=timeLapsed;
		boolean left = m_Input.getPlayerInput(id).getKey(0) != 0;
		boolean right = m_Input.getPlayerInput(id).getKey(1) != 0;
		
		if(left){	
			m_Input.getPlayerInput(id).setKey(0,(byte)0);
			m_Collider.addVelocity(new Vector2(-30, 0));
		} 
		if(right){
			m_Input.getPlayerInput(id).setKey(1,(byte)0);
			m_Collider.addVelocity(new Vector2(30, 5));
			
		}
		m_Collider.addVelocity(new Vector2(0, gravity));
	}
	
	@Override
	public void onCollisionEnter(Collider c) {
		if(c.getTag().equals("Asteroide")){
			
			tools.sendEst(id, new Estatistica("Atingido", 1));
			
			Asteroide ast = (Asteroide)c.getGameObj();
			tools.scoreReceived(-ast.getPontos(), id);  
			
			tools.sendEst(id, new Estatistica("Pontos Perdidos", ast.getPontos())); 
			if((int) tempo != 0)
				tools.sendEst(id, new Estatistica("Tempo Total Vivo(s)", (int)tempo));
			tempo = 0;
			c.getGameObj().destroy();
		}
		super.onCollisionEnter(c);
	}
	
	
}
