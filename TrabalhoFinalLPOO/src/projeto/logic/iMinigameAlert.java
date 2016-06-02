package projeto.logic;

public interface iMinigameAlert {

	void receiveEstatistica(int player_id, Estatistica e);
	
	void gameEnded(String vencedor);
	
}
