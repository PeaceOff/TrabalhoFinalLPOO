package projeto.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import projeto.network.InformationParser;
import projeto.network.TCPClient;
import projeto.network.UDPConnection;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class ClientTest extends JFrame {

	private JPanel contentPane;
	private JTextField textFIP;
	private JTextField txtPort;
	private TCPClient tcpClient;
	private UDPConnection udpClient;
	private JButton btnSendInfo;

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
		textFIP.setText("192.168.1.67");
		textFIP.setBounds(12, 12, 175, 19);
		contentPane.add(textFIP);
		textFIP.setColumns(10);
		
		txtPort = new JTextField();
		txtPort.setText("27015");
		txtPort.setBounds(197, 12, 114, 19);
		contentPane.add(txtPort);
		txtPort.setColumns(10);
		
		JButton btnConnect = new JButton("Connect");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String ip = textFIP.getText();
				int port  = Integer.parseInt(txtPort.getText());
				try {
					
					tcpClient = new TCPClient(ip, port);
					udpClient = new UDPConnection(ip, 0, tcpClient.getPort(), 1);
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
			}
		});
		btnConnect.setBounds(323, 9, 117, 25);
		contentPane.add(btnConnect);
		
		JLabel lblInfo = new JLabel("INFORMATION");
		lblInfo.setBounds(22, 43, 418, 15);
		contentPane.add(lblInfo);
		
		btnSendInfo = new JButton("SendDir");
		btnSendInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(udpClient != null){
					udpClient.sendInfo(InformationParser.transformInformation("" + (char)(-1) + (char)-1, (byte)'D'));
					  
				}
			}
		});
		btnSendInfo.setBounds(12, 70, 117, 25);
		contentPane.add(btnSendInfo);
		
		JButton btnDirL = new JButton("UP");
		btnDirL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(udpClient != null){
					udpClient.sendInfo(InformationParser.transformInformation("" + (char)(1) + (char)-1, (byte)'D'));
					  
				}
			}
		});
		btnDirL.setBounds(141, 70, 117, 25);
		contentPane.add(btnDirL);
		
		JButton btnLeft = new JButton("Left");
		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(udpClient != null){
					udpClient.sendInfo(InformationParser.transformInformation("" + (char)(-1) + (char)1, (byte)'D'));
					  
				}
			}
		});
		btnLeft.setBounds(12, 124, 117, 25);
		contentPane.add(btnLeft);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(udpClient != null){
					udpClient.sendInfo(InformationParser.transformInformation("" + (char)(1) + (char)1, (byte)'D'));
					  
				}
			}
		});
		btnNewButton.setBounds(141, 124, 117, 25);
		contentPane.add(btnNewButton);
	}
}
