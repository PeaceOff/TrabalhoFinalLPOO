package projeto.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import projeto.network.IClientConnection;
import projeto.network.InformationParser;
import projeto.network.TCPClient;
import projeto.network.UDPConnection;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.awt.event.ActionEvent;

public class ClientTest extends JFrame implements IClientConnection {

	private JPanel contentPane;
	private JTextField textFIP;
	private JTextField txtPort;
	private TCPClient tcpClient;
	private UDPConnection udpClient;
	private JButton btnSendInfo;
	private JButton btnConnect ;
	private JLabel lblInfo;
	private ClientTest object = this;
	private TouchScreen ts = new TouchScreen();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientTest frame = new ClientTest();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ClientTest() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textFIP = new JTextField();
		textFIP.setText("localhost"); 
		textFIP.setBounds(12, 12, 175, 19);
		contentPane.add(textFIP);
		textFIP.setColumns(10);
		
		txtPort = new JTextField();
		txtPort.setText("27015");
		txtPort.setBounds(197, 12, 114, 19);
		contentPane.add(txtPort);
		txtPort.setColumns(10);
		
		btnConnect = new JButton("Connect");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String ip = textFIP.getText();
				int port  = Integer.parseInt(txtPort.getText());
				try {
					
					tcpClient = new TCPClient(ip, port,object); 
					udpClient = new UDPConnection(ip, 0, tcpClient.getPort(), 1);
					ts.udp = udpClient; 
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
			}
		});
		btnConnect.setBounds(323, 9, 117, 25);
		contentPane.add(btnConnect); 
		
		lblInfo = new JLabel("INFORMATION");
		lblInfo.setBounds(22, 43, 418, 15);
		contentPane.add(lblInfo);
		
		ts.setBounds(20, 60, 200, 200);
		ts.setVisible(true);
		contentPane.add(ts); 
		
	}

	@Override
	public void ConnectedToServer(Socket server) {
		lblInfo.setText("Connected to" + server.getInetAddress().getHostAddress() + " " + server.getPort());
		btnConnect.setEnabled(false);
		
	}

	@Override
	public void DisconnectedFromServer(Socket server) { 
		lblInfo.setText("Disconnected from" + server.getInetAddress().getHostAddress());
		btnConnect.setEnabled(true);
		
	}

	@Override
	public void ServerFull(Socket server) {
		lblInfo.setText("Server: " + server.getInetAddress().getHostAddress() + " is FULL!!");
		btnConnect.setEnabled(true);
		
	}
}
