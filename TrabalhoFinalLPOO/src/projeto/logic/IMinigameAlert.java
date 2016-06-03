package projeto.logic;

public interface IMinigameAlert {

	void receiveEstatistica(int player_id, Estatistica e);
	
	void gameEnded(String vencedor);
	
}
