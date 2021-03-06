package projeto.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;

import projeto.logic.Input;
import projeto.logic.Minigame;
import projeto.logic.IMinigameAlert;
import projeto.minigames.shooter.ShooterGame;
import projeto.minigames.soccer.SoccerGame;
import projeto.minigames.survival.SurvivalMinigame;

public class MinigameSelector {

	private float time = 0;
	
	private String str = "";
	
	private int nextMg = -1;
	private Input in;
	private IMinigameAlert a;
	private boolean fim = false;
	
	public MinigameSelector(Input i, IMinigameAlert alert){
		in = i;
		a = alert;
		randomMinigame();
	}
	
	public void update(float tempo){
		
		time += tempo;

	}
	
	public void SetWinnerString(String vencedor){
		
		time = 0;
		str = vencedor;
		fim = false;

	}
	
	public boolean escolhidoMG(){

		return nextMg != -1 && fim;
	}
	
	public void drawScene(Graphics g){
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 70));
		
		if(!str.equals("")){
			
			fim=false;
			
			g.drawString(str, g.getClipBounds().width/2 - g.getFontMetrics().stringWidth(str)/2, g.getClipBounds().height/2);
			
			if(time > 3){
				str = "";
				randomMinigame();
			}
		}else{

			String proximoJogo = "O Proximo jogo �: ";
			switch(nextMg){
			case 0:
				proximoJogo += "Soccer";
				break; 
			case 1:
				proximoJogo += "Shooter";
				break;
			case 2:
				proximoJogo += "Survival";
				break;
			}
			
			g.drawString(proximoJogo, g.getClipBounds().width/2 - g.getFontMetrics().stringWidth(proximoJogo)/2, g.getClipBounds().height/2);
			
			if(time > 5){
				fim = true;
			}
			
		}
		
		
	}
	
	public Minigame escolherMinijogo(){

		switch(nextMg){
		case 0:
			return new SoccerGame(in,a);
		case 1:
			return new ShooterGame(in,a);
		}
		return new SurvivalMinigame(in,a);
	}
	
	private void randomMinigame(){
		nextMg = (new Random()).nextInt(3);
	}
	
}
