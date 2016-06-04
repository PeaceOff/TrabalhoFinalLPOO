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



		int k =0;
		while( k < info.length){
			
			information.add(info[k]);
			k++;
		}

		while(true){

			if(information.size() > 4 && tamanhoMSG == 0){
				
				int length = (int)((information.pop() & 0x0FF) << 12)
							| (int)((information.pop()& 0x0FF) << 8)
							| (int)((information.pop()& 0x0FF) << 4)
							| (int)((information.pop()& 0x0FF));
				tamanhoMSG = length;


			}else if(information.size() >= tamanhoMSG && tamanhoMSG > 0){
				
				byte[] cmd = new byte[tamanhoMSG];
				
				for(int i = 0; i < tamanhoMSG ; i++)	
					cmd[i] = information.pop();
				m_ICommandReceived.CommandReceived(cmd, id);
				tamanhoMSG = 0;
			}else {

				break;
			}

		}
		
	} 
	 
	public void clear(){
		information.clear();
	}

	
	public static byte[] transformInformation(byte ... message){
		
		
		byte[] res = new byte[message.length + 4];
		
		int size = message.length; 
		res[0] = (byte)(size  >> 12);
		res[1] = (byte)(size  >> 8);
		res[2] = (byte)(size  >> 4);
		res[3] = (byte)(size  >> 0); 
		// enviar:" + size + " = ");
		//System.out.println(res[0] + " " + res[1] + " " + res[2] + " " +  res[3]); 
		
		for(int i = 0; i < message.length; i++){
			res[4 + i] = message[i];
		}
		
		return res; 
		
	}

}