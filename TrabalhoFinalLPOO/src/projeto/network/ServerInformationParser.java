package projeto.network;


/**
 * Mensagens recebidas com flag UDP significa que se o numero da conexao for
 * superior a metade das conexoes então a mensagem recebida é por UDP
 * @author Joao
 * @version 1.0
 * @created 03-mai-2016 15:24:26
 */
public class ServerInformationParser implements IMessage, ICommandReceived {

	private InformationParser[] m_InformationParser;
	private CommandParser commandParser;
	private boolean udp = false;

	/** 
	 * 
	 * @param num
	 * @param hasUDP
	 */
	public ServerInformationParser(int num, boolean hasUDP, CommandParser c){
		commandParser = c;
		m_InformationParser = new InformationParser[num];
		for(int i = 0; i < m_InformationParser.length; i++){
			m_InformationParser[i] = new InformationParser(i, this); 
		}
		udp = hasUDP;
	}

	/**
	 * 
	 * @param cmd
	 * @param index
	 */
	public void CommandReceived(byte[] cmd, int index){
		if(udp) index %= (m_InformationParser.length/2);
		commandParser.parseCMD(cmd, index);  
	}

	/**
	 * 
	 * @param info
	 * @param id
	 */
	public void OnMessageReceived(byte[] info, int id){
		byte[] res = info;
		
		if(udp){
			if(id >= m_InformationParser.length/2){
				
				int length = 0;
				
				for(int i = info.length-1; i > 0; i--){
					if(info[i] == '#'){						
						length = i;
						break;
					} 
				}				
				
				res = new byte[length+1];
				
				for(int i = 0; i <= length ; i++){
					
					res[i] = info[i];
				}				
			}
		}
		
		synchronized(m_InformationParser[id]){
			m_InformationParser[id].parseInformation(res);
		}
	}

}