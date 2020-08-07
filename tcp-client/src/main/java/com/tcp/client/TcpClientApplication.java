package com.tcp.client;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.xml.bind.DatatypeConverter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.tcp.client.controllers.Client;
import com.tcp.client.utils.CRC8;

/**
 * Classe principal do cliente
 * @author Alex Juno Bócoli
 *
 */
@SpringBootApplication
public class TcpClientApplication {
	
	static File log = new File("LOG_CLIENT.txt");
	
	/**
	 * Método principal
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(TcpClientApplication.class, args);
		
		System.setProperty("java.awt.headless", "false");
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		// Ícones dos botões
		ImageIcon message = new ImageIcon("src/main/resources/img/envelope.png");
		ImageIcon user = new ImageIcon("src/main/resources/img/person.png");
		ImageIcon dateTime = new ImageIcon("src/main/resources/img/relogio.png");
		
		// Configurações da janela
		JFrame window = new JFrame();
		window.setResizable(false);
		window.setLayout(new GridLayout(5,1));
		window.setTitle("Cliente TCP");
	    window.setBounds(0, 0, screenSize.width/2, screenSize.height - screenSize.height/3);
	    window.setLocation(screenSize.width/4, screenSize.height/5);
	    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    // Painel 0: configuração de ip e porta
	    JPanel panel0 = new JPanel();
	    panel0.setLayout(new BoxLayout(panel0, BoxLayout.Y_AXIS));
	    JLabel label0 = new JLabel("Configurações de IP e porta");
	    label0.setAlignmentX(Component.CENTER_ALIGNMENT);
	    panel0.add(label0);
	    window.add(panel0);
	    
	    JPanel panelConfig = new JPanel();
	    panelConfig.setLayout(new FlowLayout());
	    JLabel labelIp = new JLabel("IP: ");
	    panelConfig.add(labelIp);
	    JTextField editIp = new JTextField("127.0.0.1", 16);
	    panelConfig.add(editIp);
	    JLabel labelPort = new JLabel("Porta: ");
	    panelConfig.add(labelPort);
	    JTextField editPort = new JTextField("6666", 4);
	    panelConfig.add(editPort);
	    panel0.add(panelConfig);
	    
	    // Painel 1: mensagem de texto
	    JPanel panel1 = new JPanel();
	    panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
	    JLabel label1 = new JLabel("Mensagem de texto");
	    label1.setAlignmentX(Component.CENTER_ALIGNMENT);
	    panel1.add(label1);
	    window.add(panel1);
	    
	    JPanel panelText = new JPanel();
	    panelText.setLayout(new FlowLayout());
	    JTextField editMsg = new JTextField(40);
	    panelText.add(editMsg);
	    panel1.add(panelText);
	    
	    JPanel panelEnviar1 = new JPanel();
	    panelEnviar1.setLayout(new FlowLayout());
	    JButton btEnviar1 = new JButton("Enviar", message);
	    btEnviar1.setCursor(new Cursor(Cursor.HAND_CURSOR));
	    btEnviar1.setPreferredSize(new Dimension(120, 30));
	    panelEnviar1.add(btEnviar1);
	    panel1.add(panelEnviar1);
	        
	    // Painel 2: informações de usuário
	    JPanel panel2 = new JPanel();
	    panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
	    JLabel label2 = new JLabel("Informações de usuário");
	    label2.setAlignmentX(Component.CENTER_ALIGNMENT);
	    panel2.add(label2);
	    window.add(panel2);
	    
	    JPanel panelUser = new JPanel();
	    panelUser.setLayout(new FlowLayout());
	    JLabel labelName = new JLabel("Nome: ");
	    panelUser.add(labelName);
	    JTextField editName = new JTextField(20);
	    panelUser.add(editName);
	    JLabel labelAge = new JLabel("Idade: ");
	    panelUser.add(labelAge);
	    JTextField editAge = new JTextField(3);
	    panelUser.add(editAge);
	    JLabel labelWeight = new JLabel("Peso: ");
	    panelUser.add(labelWeight);
	    JTextField editWeight = new JTextField(3);
	    panelUser.add(editWeight);
	    JLabel labelHeight = new JLabel("Altura: ");
	    panelUser.add(labelHeight);
	    JTextField editHeight = new JTextField(3);
	    panelUser.add(editHeight);
	    panel2.add(panelUser);
	    
	    JPanel panelEnviar2 = new JPanel();
	    panelEnviar2.setLayout(new FlowLayout());
	    JButton btEnviar2 = new JButton("Enviar", user);
	    btEnviar2.setCursor(new Cursor(Cursor.HAND_CURSOR));
	    btEnviar2.setPreferredSize(new Dimension(120, 30));
	    panelEnviar2.add(btEnviar2);
	    panel2.add(panelEnviar2);
	    
	    // Painel 3: solicitação de data e hora
	    JPanel panel3 = new JPanel();
	    panel3.setLayout(new BoxLayout(panel3, BoxLayout.Y_AXIS));
	    JLabel label3 = new JLabel("Solicitação de data e hora");
	    label3.setAlignmentX(Component.CENTER_ALIGNMENT);
	    panel3.add(label3);
	    window.add(panel3);
	    
	    JPanel panelTimezone = new JPanel();
	    panelTimezone.setLayout(new FlowLayout());
	    JLabel labelTimezone = new JLabel("Fuso horário: ");
	    panelTimezone.add(labelTimezone);
	    JTextField editTimezone = new JTextField(40);
	    panelTimezone.add(editTimezone);
	    panel3.add(panelTimezone);
	    
	    JPanel panelEnviar3 = new JPanel();
	    panelEnviar3.setLayout(new FlowLayout());
	    JButton btEnviar3 = new JButton("Enviar", dateTime);
	    btEnviar3.setCursor(new Cursor(Cursor.HAND_CURSOR));
	    btEnviar3.setPreferredSize(new Dimension(120, 30));
	    panelEnviar3.add(btEnviar3);
	    panel3.add(panelEnviar3);
	    
	    // Painel 4: mensagens de log
	    JPanel panel4 = new JPanel();
	    panel4.setLayout(new BoxLayout(panel4, BoxLayout.Y_AXIS));
	    JLabel label4 = new JLabel("LOG");
	    label4.setAlignmentX(Component.CENTER_ALIGNMENT);
	    panel4.add(label4);
	    window.add(panel4);
	    
	    JTextArea textLog = new JTextArea();
	    JScrollPane scrollPane = new JScrollPane(textLog);
	    textLog.setEnabled(false);
	    textLog.setDisabledTextColor(Color.BLUE);
	    textLog.setLineWrap(true);
	    textLog.setWrapStyleWord(true);
	    panel4.add(scrollPane);
	    
	    // Eventos dos botões
	    
	    // Enviar mensagem de texto
	    btEnviar1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (editIp.getText().equals("")) {
					setWarningMessage("Informe o IP do servidor!");
					return;
				}
				
				if (editPort.getText().equals("")) {
					setWarningMessage("Informe a porta de conexão!");
					return;
				}
				else {
					if (isInteger(editPort.getText()) == false) {
						setErrorMessage("A porta de conexão precisa ser um valor numérico!");
						return;
					}
				}
				
				if (editMsg.getText().equals("")){
					setWarningMessage("Informe uma mensagem!");
					return;
				}
				
				// Procedimento de envio de mensagem
				try {
					textMessage(editMsg.getText(), textLog, editIp.getText(), Integer.parseInt(editPort.getText()));
				} catch (ClassNotFoundException | IOException e1) {
					e1.printStackTrace();
				}
			}
		});
	    
	    // Enviar informações de usuário
	    btEnviar2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (editIp.getText().equals("")) {
					setWarningMessage("Informe o IP do servidor!");
					return;
				}
				
				if (editPort.getText().equals("")) {
					setWarningMessage("Informe a porta de conexão!");
					return;
				}
				else {
					if (isInteger(editPort.getText()) == false) {
						setErrorMessage("A porta de conexão precisa ser um valor numérico!");
						return;
					}
				}
				
				if (editName.getText().equals("")){
					setWarningMessage("Informe o nome do usuário!");
					return;
				}
				
				if (editAge.getText().equals("")){
					setWarningMessage("Informe a idade do usuário!");
					return;
				}
				else {
					if (isInteger(editAge.getText()) == false) {
						setErrorMessage("Idade informada não é um número inteiro!");
						return;
					}
				}
				
				if (editWeight.getText().equals("")){
					setWarningMessage("Informe o peso do usuário!");
					return;
				}
				else {
					if (isInteger(editWeight.getText()) == false) {
						setErrorMessage("Peso informado não é um número inteiro!");
						return;
					}
				}
				
				if (editHeight.getText().equals("")){
					setWarningMessage("Informe a altura do usuário!");
					return;
				}
				else {
					if (isInteger(editHeight.getText()) == false) {
						setErrorMessage("Altura informada não é um número inteiro!");
						return;
					}
				}
				
				// Procedimento de envio de mensagem
				try {
					userMessage(editName.getText(), Integer.parseInt(editAge.getText()), Integer.parseInt(editWeight.getText()), 
						Integer.parseInt(editHeight.getText()), textLog, editIp.getText(), Integer.parseInt(editPort.getText()));
				} catch (NumberFormatException | ClassNotFoundException | IOException e1) {
					e1.printStackTrace();
				}
			}
		});
	    
	    // Solicitar data e hora
	    btEnviar3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (editIp.getText().equals("")) {
					setWarningMessage("Informe o IP do servidor!");
					return;
				}
				
				if (editPort.getText().equals("")) {
					setWarningMessage("Informe a porta de conexão!");
					return;
				}
				else {
					if (isInteger(editPort.getText()) == false) {
						setErrorMessage("A porta de conexão precisa ser um valor numérico!");
						return;
					}
				}
				
				if (editTimezone.getText().equals("")){
					setWarningMessage("Informe um fuso horário! \nEx: America/Sao_Paulo");
					return;
				}
				
				// Procedimento de envio de mensagem
				try {
					dateTimeMessage(editTimezone.getText(), textLog, editIp.getText(), Integer.parseInt(editPort.getText()));
				} catch (ClassNotFoundException | IOException e1) {
					e1.printStackTrace();
				}
			}
		});
	    
		window.setVisible(true);	
	}
	
	/**
	 * Define uma mensagem de alerta
	 * @param text o texto da mensagem de alerta
	 */
	public static void setWarningMessage(String text) {
	    JOptionPane optionPane = new JOptionPane(text,JOptionPane.WARNING_MESSAGE);
	    JDialog dialog = optionPane.createDialog("Atenção");
	    dialog.setAlwaysOnTop(true);
	    dialog.setVisible(true);
	}
	
	/**
	 * Define uma mensagem de erro
	 * @param text o texto da mensagem de erro
	 */
	public static void setErrorMessage(String text) {
	    JOptionPane optionPane = new JOptionPane(text,JOptionPane.ERROR_MESSAGE);
	    JDialog dialog = optionPane.createDialog("Erro");
	    dialog.setAlwaysOnTop(true);
	    dialog.setVisible(true);
	}
	
	/**
	 * Verifica se o texto é um valor numérico
	 * @param s o texto informado no JTextField
	 * @return true ou false
	 */
	public static boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    }
	    
	    return true;
	}
	
	/**
	 * Escreve os logs em arquivo
	 * @param writer o objeto responsável por escrever no arquivo
	 * @param text o texto a ser escrito
	 * @throws IOException
	 */
	public static void logWriter(FileWriter writer, String text) throws IOException {		
		Date dateTime = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		
		writer.write(dateFormat.format(dateTime) + ": " + text);
	}
	
	/**
	 * Rotina de envio de uma mensagem de texto
	 * @param textMsg a mensagem a ser enviada
	 * @param textLog o log da aplicação
	 * @throws UnknownHostException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static void textMessage(String textMsg, JTextArea textLog, String ip, int port) throws UnknownHostException, IOException, ClassNotFoundException {
		FileWriter writer = new FileWriter(log, true);
		
		// Limpa o LOG
		textLog.setText(null);
		
		// Instancia o cliente e solicita conexão com o servidor
		Client client1 = new Client();
		client1.startConnection(ip, port);
		
		logWriter(writer, "Conexão estabelecida com o servidor.\n");
		logWriter(writer, "IP: " + ip + " / Porta:" + port + "\n");
		
		textLog.append("Convertendo mensagem de texto '" + textMsg + "'...\n");
		logWriter(writer, "Convertendo mensagem de texto '" + textMsg + "'...\n");
		
		// Conversão da mensagem em hexadecimal
		String hexMsg = String.format("%x", new BigInteger(1, textMsg.getBytes("UTF-8")));
		if (hexMsg.length() == 1) hexMsg = "0" + hexMsg;
		
		// Preparação do tamanho do protocolo
		int bytesAscii = textMsg.length() + 5;
		String bytesHex = Integer.toHexString(bytesAscii);
		if (bytesHex.length() == 1) bytesHex = "0" + bytesHex;
		
		// Construção da string para cálculo do CRC (bytes, frame, data)
		String crcCalc = bytesHex + "A1" + hexMsg;
		
		// Conversão em array de bytes para cálculo do CRC
		byte[] crcCalcBytes = DatatypeConverter.parseHexBinary(crcCalc);
		
		// Cálculo do CRC
		CRC8 crc8 = new CRC8();
		crc8.reset();
		crc8.update(crcCalcBytes);
		String hexCrc = Integer.toHexString((int) crc8.getValue());
		textLog.append("CRC calculado (dec): " + crc8.getValue() + "\n");
		textLog.append("CRC calculado (hex): " + hexCrc + "\n");
		logWriter(writer, "CRC calculado (dec): " + crc8.getValue() + "\n");
		logWriter(writer, "CRC calculado (hex): " + hexCrc + "\n");
		
		// Protocolo enviado e recebimento da resposta
		String protocol = "0A" + bytesHex + "A1" + hexMsg + hexCrc + "0D";
		textLog.append("Enviando protocolo '" + protocol + "' ao servidor...\n");
		logWriter(writer, "Enviando protocolo '" + protocol + "' ao servidor...\n");
		String response = client1.sendMessage(protocol);
		
		// Conversão da mensagem recebida de string para array
		ArrayList<String> hexResArray = new ArrayList<String>();
		for (String hex: response.replaceAll( "..(?!$)", "$0," ).split( "," ) ) {
			hexResArray.add(hex);
		}
		
		// Construção da string para cálculo do CRC (bytes, frame, crc)
		crcCalc = "";
		for (int i = 1; i <= 3; i++) {
			crcCalc = crcCalc + hexResArray.get(i);
		}
		
		// Conversão em array de bytes para cálculo do CRC
		crcCalcBytes = DatatypeConverter.parseHexBinary(crcCalc);
		
		// Cálculo do CRC
		crc8.reset();
		crc8.update(crcCalcBytes);
		hexCrc = Integer.toHexString((int) crc8.getValue());
		
		if (hexCrc.equals("0")) {
			textLog.append("\nResposta recebida sem erros: " + response + "\n");
			logWriter(writer, "Resposta recebida sem erros: " + response + "\n");
		}
		else {
			textLog.append("\nResposta recebida com erros!\n");
			logWriter(writer, "Resposta recebida com erros!\n");
		}
		textLog.append("CRC calculado (dec): " + crc8.getValue() + "\n");
		textLog.append("CRC calculado (hex): " + hexCrc + "\n");
		logWriter(writer, "CRC calculado (dec): " + crc8.getValue() + "\n");
		logWriter(writer, "CRC calculado (hex): " + hexCrc + "\n");
		
		if (log.exists()) {
			writer.write("\n");
		}
		
		writer.close();
	}
	
	/**
	 * Rotina de envio das informações do usuário
	 * @param userName o nome do usuário
	 * @param userAge a idade do usuário
	 * @param userWeight o peso do usuário
	 * @param userHeight a altura do usuário
	 * @param textLog o log da aplicação
	 * @throws UnknownHostException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static void userMessage(String userName, int userAge, int userWeight, int userHeight, JTextArea textLog, String ip, int port) throws UnknownHostException, IOException, ClassNotFoundException {
		FileWriter writer = new FileWriter(log, true);
		
		// Limpa o LOG
		textLog.setText(null);
		
		// Instancia o cliente e solicita conexão com o servidor
		Client client2 = new Client();
		client2.startConnection(ip, port);
		
		logWriter(writer, "Conexão estabelecida com o servidor.\n");
		logWriter(writer, "IP: " + ip + " / Porta:" + port + "\n");
		
		textLog.append("Codificando informações de usuário '" + userName + ", " + userAge + " anos, " + userWeight + " Kg, " + userHeight + " cm'...\n");
		logWriter(writer, "Codificando informações de usuário '" + userName + ", " + userAge + " anos, " + userWeight + " Kg, " + userHeight + " cm'...\n");
		
		// Preparação da mensagem
		String hexAge = Integer.toHexString(userAge);
		if (hexAge.length() == 1) hexAge = "0" + hexAge;
		String hexWeight = Integer.toHexString(userWeight);
		if (hexWeight.length() == 1) hexWeight = "0" + hexWeight;
		String hexHeight = Integer.toHexString(userHeight);
		if (hexHeight.length() == 1) hexHeight = "0" + hexHeight;
		String hexNameSize = Integer.toHexString(userName.length());
		if (hexNameSize.length() == 1) hexNameSize = "0" + hexNameSize;
		String hexName = String.format("%x", new BigInteger(1, userName.getBytes("UTF-8")));
		if (hexName.length() == 1) hexName = "0" + hexName;
		String hexData = hexAge + hexWeight + hexHeight + hexNameSize + hexName;
		
		// Preparação do tamanho do protocolo
		int bytesDec = userName.length() + 9; 
		String bytesHex = Integer.toHexString(bytesDec); 
		if (bytesHex.length() == 1) bytesHex = "0" + bytesHex;
		
		// Construção da string para cálculo do CRC (bytes, frame, data)
		String crcCalc = bytesHex + "A2" + hexData;
		
		// Conversão em array de bytes para cálculo do CRC
		byte[] crcCalcBytes = DatatypeConverter.parseHexBinary(crcCalc);
		
		// Cálculo do CRC
		CRC8 crc8 = new CRC8();
		crc8.reset();
		crc8.update(crcCalcBytes);
		String hexCrc = Integer.toHexString((int) crc8.getValue());
		textLog.append("CRC calculado (dec): " + crc8.getValue() + "\n");
		textLog.append("CRC calculado (hex): " + hexCrc + "\n");
		logWriter(writer, "CRC calculado (dec): " + crc8.getValue() + "\n");
		logWriter(writer, "CRC calculado (hex): " + hexCrc + "\n");		
		
		// Protocolo enviado e recebimento da resposta
		String protocol = "0A" + bytesHex + "A2" + hexData + hexCrc + "0D";
		textLog.append("Enviando protocolo '" + protocol + "' ao servidor...\n");
		logWriter(writer, "Enviando protocolo '" + protocol + "' ao servidor...\n");
		String response = client2.sendMessage(protocol);
		
		// Conversão da mensagem recebida de string para array
		ArrayList<String> hexResArray = new ArrayList<String>();
		for (String hex: response.replaceAll( "..(?!$)", "$0," ).split( "," ) ) {
			hexResArray.add(hex);
		}
		
		// Construção da string para cálculo do CRC (bytes, frame, crc)
		crcCalc = "";
		for (int i = 1; i <= 3; i++) {
			crcCalc = crcCalc + hexResArray.get(i);
		}
		
		// Conversão em array de bytes para cálculo do CRC
		crcCalcBytes = DatatypeConverter.parseHexBinary(crcCalc);
		
		// Cálculo do CRC
		crc8.reset();
		crc8.update(crcCalcBytes);
		hexCrc = Integer.toHexString((int) crc8.getValue());
		
		if (hexCrc.equals("0")) {
			textLog.append("\nResposta recebida sem erros: " + response + "\n");
			logWriter(writer, "\nResposta recebida sem erros: " + response + "\n");
		}
		else {
			textLog.append("\nResposta recebida com erros!\n");
			logWriter(writer, "Resposta recebida com erros!\n");
		}
		textLog.append("CRC calculado (dec): " + crc8.getValue() + "\n");
		textLog.append("CRC calculado (hex): " + hexCrc + "\n");
		logWriter(writer, "CRC calculado (dec): " + crc8.getValue() + "\n");
		logWriter(writer, "CRC calculado (hex): " + hexCrc + "\n");
		
		if (log.exists()) {
			writer.write("\n");
		}
		
		writer.close();
	}
	
	/**
	 * Rotina de solicitação de data e hora
	 * @param timezoneMsg o fuso horário informado
	 * @param textLog o log da aplicação
	 * @throws UnknownHostException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static void dateTimeMessage(String timezoneMsg, JTextArea textLog, String ip, int port) throws UnknownHostException, IOException, ClassNotFoundException {
		FileWriter writer = new FileWriter(log, true);
		
		// Limpa o LOG
		textLog.setText(null);
		
		// Instancia o cliente e solicita conexão com o servidor
		Client client3 = new Client();
		client3.startConnection(ip, port);
		
		logWriter(writer, "Conexão estabelecida com o servidor.\n");
		logWriter(writer, "IP: " + ip + " / Porta:" + port + "\n");
		
		textLog.append("Codificando o fuso horário '" + timezoneMsg + "'...\n");
		logWriter(writer, "Codificando o fuso horário '" + timezoneMsg + "'...\n");
		
		// Conversão da mensagem em hexadecimal
		String hexTimezone = String.format("%x", new BigInteger(1, timezoneMsg.getBytes("UTF-8")));
		if (hexTimezone.length() == 1) hexTimezone = "0" + hexTimezone;
		
		// Preparação do tamanho do protocolo
		int bytesAscii = timezoneMsg.length() + 5;
		String bytesHex = Integer.toHexString(bytesAscii);
		if (bytesHex.length() == 1) bytesHex = "0" + bytesHex;
		
		// Construção da string para cálculo do CRC (bytes, frame, data)
		String crcCalc = bytesHex + "A3" + hexTimezone;
		
		// Conversão em array de bytes para cálculo do CRC
		byte[] crcCalcBytes = DatatypeConverter.parseHexBinary(crcCalc);
		
		// Cálculo do CRC
		CRC8 crc8 = new CRC8();
		crc8.reset();
		crc8.update(crcCalcBytes);
		String hexCrc = Integer.toHexString((int) crc8.getValue());
		textLog.append("CRC calculado (dec): " + crc8.getValue() + "\n");
		textLog.append("CRC calculado (hex): " + hexCrc + "\n");
		logWriter(writer, "CRC calculado (dec): " + crc8.getValue() + "\n");
		logWriter(writer, "CRC calculado (hex): " + hexCrc + "\n");
		
		// Protocolo enviado e recebimento da resposta
		String protocol = "0A" + bytesHex + "A3" + hexTimezone + hexCrc + "0D";
		textLog.append("Enviando protocolo '" + protocol + "' ao servidor...\n");
		logWriter(writer, "Enviando protocolo '" + protocol + "' ao servidor...\n");
		String response = client3.sendMessage(protocol);
		
		// Se a resposta for um ACK, significa que o fuso consultado não consta na tabela de fusos horários
		if (response.equals("0A05A0280D")) {
			// Conversão da mensagem recebida de string para array
			ArrayList<String> hexResArray = new ArrayList<String>();
			for (String hex: response.replaceAll( "..(?!$)", "$0," ).split( "," ) ) {
				hexResArray.add(hex);
			}
			
			// Construção da string para cálculo do CRC (bytes, frame, crc)
			crcCalc = "";
			for (int i = 1; i <= 3; i++) {
				crcCalc = crcCalc + hexResArray.get(i);
			}
			
			// Conversão em array de bytes para cálculo do CRC
			crcCalcBytes = DatatypeConverter.parseHexBinary(crcCalc);
			
			// Cálculo do CRC
			crc8.reset();
			crc8.update(crcCalcBytes);
			hexCrc = Integer.toHexString((int) crc8.getValue());
			
			if (hexCrc.equals("0")) {
				textLog.append("\nResposta recebida sem erros: " + response + "\n");
				textLog.append("O fuso horário requisitado não existe!\n");
				logWriter(writer, "Resposta recebida sem erros: " + response + "\n");
				logWriter(writer, "O fuso horário requisitado não existe!\n");
			}
			else {
				textLog.append("\nResposta recebida com erros!\n");
				logWriter(writer, "\nResposta recebida com erros!\n");
			}
			textLog.append("CRC calculado (dec): " + crc8.getValue() + "\n");
			textLog.append("CRC calculado (hex): " + hexCrc + "\n");
			logWriter(writer, "CRC calculado (dec): " + crc8.getValue() + "\n");
			logWriter(writer, "CRC calculado (hex): " + hexCrc + "\n");
			
			writer.close();
			
			return;
		}
		
		// Conversão da mensagem recebida de string para array
		ArrayList<String> hexResArray = new ArrayList<String>();
		for (String hex: response.replaceAll( "..(?!$)", "$0," ).split( "," ) ) {
			hexResArray.add(hex);
		}
		
		// Conversão da data e da hora para inteiro
		int day = Integer.parseInt(hexResArray.get(3), 16);
		int month = Integer.parseInt(hexResArray.get(4), 16);
		int year = Integer.parseInt(hexResArray.get(5), 16);
		int hour = Integer.parseInt(hexResArray.get(6), 16);
		int minute = Integer.parseInt(hexResArray.get(7), 16);
		int second = Integer.parseInt(hexResArray.get(8), 16);
				
		// Construção da string para cálculo do CRC (bytes, frame, data, crc)
		crcCalc = "";
		for (int i = 1; i <= hexResArray.size() - 2; i++) {
			crcCalc = crcCalc + hexResArray.get(i);
		}
		
		// Conversão em array de bytes para cálculo do CRC
		crcCalcBytes = DatatypeConverter.parseHexBinary(crcCalc);
		
		// Cálculo do CRC
		crc8.reset();
		crc8.update(crcCalcBytes);
		hexCrc = Integer.toHexString((int) crc8.getValue());
		
		if (hexCrc.equals("0")) {
			textLog.append("\nResposta recebida sem erros: " + response + "\n");
			textLog.append("Data e hora no fuso " + timezoneMsg + ": " 
				+ day + "/" + month + "/" + year + " " + hour + ":" + minute + ":" + second + "\n");
			logWriter(writer, "Resposta recebida sem erros: " + response + "\n");
			logWriter(writer, "Data e hora no fuso " + timezoneMsg + ": " 
				+ day + "/" + month + "/" + year + " " + hour + ":" + minute + ":" + second + "\n");
		}
		else {
			textLog.append("\nResposta recebida com erros!\n");
			logWriter(writer, "Resposta recebida com erros!\n");
		}
		textLog.append("CRC calculado (dec): " + crc8.getValue() + "\n");
		textLog.append("CRC calculado (hex): " + hexCrc + "\n");
		logWriter(writer, "CRC calculado (dec): " + crc8.getValue() + "\n");
		logWriter(writer, "CRC calculado (hex): " + hexCrc + "\n");
		
		if (log.exists()) {
			writer.write("\n");
		}
		
		writer.close();
	}
}
