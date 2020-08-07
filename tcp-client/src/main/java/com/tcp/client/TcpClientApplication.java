package com.tcp.client;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;

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

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.tcp.client.controllers.Client;

@SpringBootApplication
public class TcpClientApplication {
	
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
		window.setLayout(new GridLayout(4,1));
		window.setTitle("Cliente TCP");
	    window.setBounds(0, 0, screenSize.width/2, screenSize.height - screenSize.height/3);
	    window.setLocation(screenSize.width/4, screenSize.height/5);
	    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
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
	    textLog.setLineWrap(true);
	    textLog.setWrapStyleWord(true);
	    panel4.add(scrollPane);
	    
	    // Eventos dos botões
	    
	    // Enviar mensagem de texto
	    btEnviar1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (editMsg.getText().equals("")){
					setWarningMessage("Informe uma mensagem!");
					return;
				}
				
				// Procedimento de envio de mensagem
				try {
					textMessage(editMsg.getText(), textLog);
				} catch (UnknownHostException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
	    
	    // Enviar informações de usuário
	    btEnviar2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
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
			}
		});
	    
	    // Solicitar data e hora
	    btEnviar3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (editTimezone.getText().equals("")){
					setWarningMessage("Informe um fuso horário! \nEx: America/Sao_Paulo");
					return;
				}
			}
		});
	    
		window.setVisible(true);	
	}
	
	public static void setWarningMessage(String text) {
	    JOptionPane optionPane = new JOptionPane(text,JOptionPane.WARNING_MESSAGE);
	    JDialog dialog = optionPane.createDialog("Atenção");
	    dialog.setAlwaysOnTop(true);
	    dialog.setVisible(true);
	}
	
	public static void setErrorMessage(String text) {
	    JOptionPane optionPane = new JOptionPane(text,JOptionPane.ERROR_MESSAGE);
	    JDialog dialog = optionPane.createDialog("Erro");
	    dialog.setAlwaysOnTop(true);
	    dialog.setVisible(true);
	}
	
	public static boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    }
	    
	    return true;
	}
	
	public static void textMessage(String textMsg, JTextArea textLog) throws UnknownHostException, IOException {
		//Client client1 = new Client();
		//client1.startConnection("127.0.0.1", 6666);
		
		textLog.append("Enviando mensagem de texto '" + textMsg + "'...\n");
		
	}

}
