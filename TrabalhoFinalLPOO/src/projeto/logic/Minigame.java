package projeto.logic;

import java.util.ArrayList;

/**
 * @author Joao e David
 * @version 1.0
 * @created 03-mai-2016 15:30:40
 */
public abstract class Minigame implements IMinigameTools {

	protected ArrayList<Integer> scores;
	protected Fisica m_Fisica;
	protected ArrayList<GameObject> game_objects;
	protected Input m_Input; 
	protected IMinigameAlert i_EstAlert;
	
	/**
	 * Construtor da classe minigame
	 * @param i o input relativo as ligacoes dos jogadores
	 * @param estA interface de alertas relacionados com o minijogo
	 */
	public Minigame(Input i, IMinigameAlert estA){
		scores = new ArrayList<Integer>();
		m_Fisica = new Fisica() ; //Fisica.getInstance();
		game_objects = new ArrayList<GameObject>();
		m_Input = i; 
		i_EstAlert = estA;
	}
	
	/**
	 * Funcao de adicionar um jogador
	 * @param id do jogador a adicionar
	 */
	public abstract void addPlayer(int id);
	
	/**
	 * Funcao para remover um player
	 * @param id do player a remover
	 */
	public abstract void removePlayer(int id);
	
	/**
	 * Funcao para terminar um minijogo
	 */
	public void acabar(){
		i_EstAlert = null;
		m_Input = null;
		scores = null;
		game_objects = null;
		
	}
	
	/**
	 * Funcao para adicionar um objecto ao minijogo
	 * @param g object que se vai adicionar
	 */
	public void addGameObject(GameObject g){
		synchronized (game_objects) {
			game_objects.add(g);
			m_Fisica.addObject(g.m_Collider);
		}
	}
	
	/**
	 * Funcao para remover um objecto do minijogo
	 * @param g objecto a remover
	 */
	public void removeGameObject(GameObject g){
		if(g == null) return;
		synchronized (game_objects) { 
			game_objects.remove(g);
			m_Fisica.removeObject(g.m_Collider);
		}
		
	}
	
	/**
	 * Funcao get para o controlador de packets
	 * @return controlador de packets
	 */
	public abstract ArrayList<ControllerInformationPacket> getControllPacket();
	
	/**
	 * Funcao para inicializar o jogo
	 */
	public abstract void initGame();
	
	/**
	 * Funcao get para os scores do jogo
	 * @return scores do jogo
	 */
	public abstract int[] getScores();
	
	/**
	 * Funcao get para as dimensoes do jogo
	 * @return as dimensoes do jogo
	 */
	public abstract Vector2 getDim();
	
	/**
	 * Funcao get para a dica do minijogo
	 * @return a dica do minijogo
	 */
	public abstract String getDica();
	
	/**
	 * Funcao get para o input
	 * @return input
	 */
	public Input getInput(){
		return m_Input;
	}
	 
	/**
	 * Funcao get para os objetos do minijogo
	 * @return os objectos do minijogo
	 */
	public ArrayList<GameObject> getGame_objects() {
		synchronized (game_objects) {
			return game_objects;
		}
	}

	/**
	 * Funcao para atualizar o estado do minijogo
	 * @param timeLapsed tempo que passou desde a ultima atualizacao
	 */
	public void update(float timeLapsed){ 
		synchronized(game_objects){
			for(int i = 0; i < game_objects.size(); i++){
				if(game_objects.get(i).isDestroyed()){
					removeGameObject(game_objects.get(i));
					i--;
					continue;
				}
				game_objects.get(i).update(timeLapsed);
			}
		}
		m_Fisica.update(timeLapsed);
		
	}
	
	/**
	 * Funcao add para um novo object do minijogo
	 * @param gO novo objecto do jogo
	 */
	public void newGameObject(GameObject gO){
		addGameObject(gO);
	}

}