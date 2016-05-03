package projeto.network;


/**
 * Mensagens recebidas com flag UDP significa que se o numero da conexao for
 * superior a metade das conexoes ent�o a mensagem recebida � por UDP
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
		commandParser.parseCMD(cmd, index); 
	}

	/**
	 * 
	 * @param info
	 * @param id
	 */
	public void OnMessageReceived(byte[] info, int id){
		synchronized(m_InformationParser[id]){
			m_InformationParser[id].parseInformation(info);
		}
	}

}