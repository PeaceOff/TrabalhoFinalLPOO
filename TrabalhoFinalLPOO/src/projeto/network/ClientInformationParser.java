package projeto.network;


/**
 * @author Joao
 * @version 1.0
 * @created 03-mai-2016 15:23:53
 */
public class ClientInformationParser implements IMessage, ICommandReceived {

	private InformationParser[] m_InformationParser;
	private CommandParser commandParser;
	public ClientInformationParser( CommandParser c){
		m_InformationParser = new InformationParser[2];
		for(int i = 0; i < m_InformationParser.length; i++){
			m_InformationParser[i] = new InformationParser(i, this);
		} 
		commandParser = c;  
	}

	/**
	 * 
	 * @param cmd
	 * @param index
	 */
	
	@Override
	public void CommandReceived(byte[] cmd, int index) {
		commandParser.parseCMD(cmd, index);
		
	}

	
	@Override
	public void OnMessageReceived(byte[] info, int id) {
		
		synchronized(m_InformationParser[id]){
			m_InformationParser[id].parseInformation(info);
		}
		
	}

}