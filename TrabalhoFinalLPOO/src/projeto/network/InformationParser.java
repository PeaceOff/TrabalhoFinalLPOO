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
	private int tamanhoMSG = 0;
	
	public InformationParser(int id, ICommandReceived rc){
		this.id = id;
		m_ICommandReceived = rc;
		
		 
	}

	/**
	 * 
	 * @param info
	 */
	public void parseInformation(byte[] info){
		
		for(int i =0; i < info.length; i++){
			System.out.println(info[i]);

			information.add(info[i]);
		}
		
		while(true){
			
			if(information.size() > 4 && tamanhoMSG == 0){
				
				int length = (int)(information.pop() << 12)
							| (int)(information.pop() << 8)
							| (int)(information.pop() << 4)
							| (int)(information.pop());
				tamanhoMSG = length - 4;
				
			}else
				break;
			
			if(information.size() >= tamanhoMSG && tamanhoMSG > 0){
				
				byte[] cmd = new byte[tamanhoMSG];
				
				for(int i = 0; i < tamanhoMSG ; i++)	
					cmd[i] = information.pop();
				
				m_ICommandReceived.CommandReceived(cmd, id);
				
				tamanhoMSG = 0; 
			}else
				break;
		}
		
	} 
	 
	public void clear(){
		information.clear();
	}

	/**
	 * 
	 * @param msg
	 * @param tag
	 */
	
	public static byte[] transformInformation(byte ... message){
		
		
		byte[] res = new byte[message.length + 4];
		
		int size = message.length; 
		res[0] = (byte)(size >> 12);
		res[1] = (byte)(size >> 8);
		res[2] = (byte)(size >> 4);
		res[3] = (byte)(size >> 0);
		//System.out.print("Tamanho mensagem a enviar:" + size + " = ");
		//System.out.println(res[0] + " " + res[1] + " " + res[2] + " " +  res[3]); 
		
		for(int i = 0; i < message.length; i++){
			res[4 + i] = message[i];
		}
		
		return res; 
		
	}

}