package projeto.network;

import java.util.LinkedList;

/**
 * Mensagens sao enviadas e recebidas em formato String(Mais facil para perceber o
 * que fazer)
 * @author Joao
 * @version 1.0
 * @created 03-mai-2016 15:24:25
 */
public class InformationParser {

	private final int id;
	private LinkedList<Byte> information = new LinkedList<Byte>();
	private ICommandReceived m_ICommandReceived;
	int counter = 0;
	
	public InformationParser(int id, ICommandReceived rc){
		this.id = id;
		m_ICommandReceived = rc; 
	}

	/**
	 * 
	 * @param info
	 */
	public void parseInformation(byte[] info){
		
		LinkedList<Integer> lengths = new LinkedList<Integer>();
		
		for(int i =0; i < info.length; i++){
			
			counter ++; 
			
			if(info[i] == '#'){
				lengths.add(counter);
				counter = 0;
			}
			information.add(info[i]);
		}
		
		while(lengths.size() > 0){
			
			int length = lengths.pop();
			
			byte[] cmd = new byte[length];
			
			for(int i = 0; i < length ; i++)	
				cmd[i] = information.pop();
			
			m_ICommandReceived.CommandReceived(cmd, id);
			 
		}
		
		
	} 

	/**
	 * 
	 * @param msg
	 * @param tag
	 */
	public static byte[] transformInformation(String msg, byte tag){
		
		byte[] info = msg.getBytes();
		
		byte[] res = new byte[info.length + 3];
		
		for(int i = 0 ; i < info.length; i++){
			
			if(info[i] == '#' || info[i] == '$')
				res[i] = ' ';
			else
				res[i+2] = info[i];
		}   
		  
		 
		res[1] = tag; 
		res[0] = '$';  
		res[res.length-1] = '#'; 
		return res;
		
	}

}