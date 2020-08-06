package com.tcp.client;

import java.io.IOException;
import java.math.BigInteger;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.xml.bind.DatatypeConverter;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.tcp.client.controllers.Client;
import com.tcp.client.utils.CRC8;

@SpringBootTest
public class TcpClientApplicationTests {

	/*@Test
	void contextLoads() {
	}*/
	
	/*@Test
	public void textMessage() throws UnknownHostException, IOException, ClassNotFoundException {
		Client client1 = new Client();
		client1.startConnection("127.0.0.1", 6666);
		
		System.out.println("Enviando mensagem de texto...");
		
		// Preparação da mensagem
		String msgAscii = "new tsss";
		String msgHex = String.format("%x", new BigInteger(1, msgAscii.getBytes("UTF-8")));
		if (msgHex.length() == 1) msgHex = "0" + msgHex;
		
		// Preparação do tamanho do protocolo
		int bytesAscii = msgAscii.length() + 5; // Número de bytes do protocolo
		String bytesHex = Integer.toHexString(bytesAscii); // Conversão do número de bytes para hex
		if (bytesHex.length() == 1) bytesHex = "0" + bytesHex; // Se a conversão der apenas 1 caractere, adiciona um 0 à esquerda
		
		// Construção da string para cálculo do CRC (bytes, frame, data)
		String crcCalc = bytesHex + "A1" + msgHex;
		//System.out.println(crcCalc);
		
		// Conversão em array de bytes para cálculo do CRC
		byte[] inputCrc = DatatypeConverter.parseHexBinary(crcCalc);
		
		// Cálculo do CRC
		CRC8 crc8 = new CRC8();
		crc8.reset();
		crc8.update(inputCrc);
		String hexCrc = Integer.toHexString((int) crc8.getValue());
		System.out.println("Hex CRC: " + hexCrc);
		
		// Protocolo enviado e recebimento da resposta
		String protocol = "0A" + bytesHex + "A1" + msgHex + hexCrc + "0D";
		String response = client1.sendMessage(protocol);
		
		// Conversão da mensagem recebida de string para array
		ArrayList<String> msgHexArray = new ArrayList<String>();
		for (String hex: response.replaceAll( "..(?!$)", "$0," ).split( "," ) ) {
			msgHexArray.add(hex);
		}
		
		// Construção da string para cálculo do CRC (bytes, frame, crc)
		crcCalc = "";
		for (int i = 1; i <= 3; i++) {
			crcCalc = crcCalc + msgHexArray.get(i);
		}
		
		// Conversão em array de bytes para cálculo do CRC
		inputCrc = DatatypeConverter.parseHexBinary(crcCalc);
		
		// Cálculo do CRC
		crc8.reset();
		crc8.update(inputCrc);
		hexCrc = Integer.toHexString((int) crc8.getValue());
		if (hexCrc.length() == 1) hexCrc = "0" + hexCrc;
		
		if (hexCrc.equals("0")) {
			System.out.println(msgHexArray);
			System.out.println("Hex CRC: " + hexCrc);
			System.out.println("ACK recebido: " + response);
		}
	}*/
	
	/*@Test
	public void userMessage() throws UnknownHostException, IOException, ClassNotFoundException {
		Client client2 = new Client();
		client2.startConnection("127.0.0.1", 6666);
		
		System.out.println("Enviando informações de usuário...");
		
		int age = 32;
		int weight = 122;
		int height = 195;
		String name = "Michel";
		int nameSize = name.length();
		
		// Preparação da mensagem
		String ageHex = Integer.toHexString(age);
		if (ageHex.length() == 1) ageHex = "0" + ageHex;
		String weightHex = Integer.toHexString(weight);
		if (weightHex.length() == 1) weightHex = "0" + weightHex;
		String heightHex = Integer.toHexString(height);
		if (heightHex.length() == 1) heightHex = "0" + heightHex;
		String nameSizeHex = Integer.toHexString(nameSize);
		if (nameSizeHex.length() == 1) nameSizeHex = "0" + nameSizeHex;
		String nameHex = String.format("%x", new BigInteger(1, name.getBytes("UTF-8")));
		if (nameHex.length() == 1) nameHex = "0" + nameHex;
		String dataHex = ageHex + weightHex + heightHex + nameSizeHex + nameHex;
		
		// Preparação do tamanho do protocolo
		int bytesDec = nameSize + 9; // Número de bytes do protocolo
		String bytesHex = Integer.toHexString(bytesDec); // Conversão do número de bytes para hex
		if (bytesHex.length() == 1) bytesHex = "0" + bytesHex; // Se a conversão der apenas 1 caractere, adiciona um 0 à esquerda
		
		// Construção da string para cálculo do CRC (bytes, frame, data)
		String crcCalc = bytesHex + "A2" + dataHex;
		System.out.println(crcCalc);
		
		// Conversão em array de bytes para cálculo do CRC
		byte[] inputCrc = DatatypeConverter.parseHexBinary(crcCalc);
		
		// Cálculo do CRC
		CRC8 crc8 = new CRC8();
		crc8.reset();
		crc8.update(inputCrc);
		String hexCrc = Integer.toHexString((int) crc8.getValue());
		System.out.println("Hex CRC: " + hexCrc);
		
		// Protocolo enviado e recebimento da resposta
		String protocol = "0A" + bytesHex + "A2" + dataHex + hexCrc + "0D";
		String response = client2.sendMessage(protocol);
		
		// Conversão da mensagem recebida de string para array
		ArrayList<String> msgHexArray = new ArrayList<String>();
		for (String hex: response.replaceAll( "..(?!$)", "$0," ).split( "," ) ) {
			msgHexArray.add(hex);
		}
		
		// Construção da string para cálculo do CRC (bytes, frame, crc)
		crcCalc = "";
		for (int i = 1; i <= 3; i++) {
			crcCalc = crcCalc + msgHexArray.get(i);
		}
		
		// Conversão em array de bytes para cálculo do CRC
		inputCrc = DatatypeConverter.parseHexBinary(crcCalc);
		
		// Cálculo do CRC
		crc8.reset();
		crc8.update(inputCrc);
		hexCrc = Integer.toHexString((int) crc8.getValue());
		if (hexCrc.length() == 1) hexCrc = "0" + hexCrc;
		
		if (hexCrc.equals("0")) {
			System.out.println(msgHexArray);
			System.out.println("Hex CRC: " + hexCrc);
			System.out.println("ACK recebido: " + response);
		}
	}*/
	
	@Test
	public void dateTimeMessage() throws UnknownHostException, IOException, ClassNotFoundException {
		Client client3 = new Client();
		client3.startConnection("127.0.0.1", 6666);
		
		System.out.println("Solicitando data e hora atual...");
		
		// Preparação da mensagem
		String msgAscii = "America/Porto_Velho";
		String msgHex = String.format("%x", new BigInteger(1, msgAscii.getBytes("UTF-8")));
		if (msgHex.length() == 1) msgHex = "0" + msgHex;
		
		// Preparação do tamanho do protocolo
		int bytesAscii = msgAscii.length() + 5; // Número de bytes do protocolo
		String bytesHex = Integer.toHexString(bytesAscii); // Conversão do número de bytes para hex
		if (bytesHex.length() == 1) bytesHex = "0" + bytesHex; // Se a conversão der apenas 1 caractere, adiciona um 0 à esquerda
		
		// Construção da string para cálculo do CRC (bytes, frame, data)
		String crcCalc = bytesHex + "A3" + msgHex;
		
		// Conversão em array de bytes para cálculo do CRC
		byte[] inputCrc = DatatypeConverter.parseHexBinary(crcCalc);
		
		// Cálculo do CRC
		CRC8 crc8 = new CRC8();
		crc8.reset();
		crc8.update(inputCrc);
		String hexCrc = Integer.toHexString((int) crc8.getValue());
		System.out.println("Hex CRC: " + hexCrc);
		
		// Protocolo enviado e recebimento da resposta
		String protocol = "0A" + bytesHex + "A3" + msgHex + hexCrc + "0D";
		String response = client3.sendMessage(protocol);
		
		// Conversão da mensagem recebida de string para array
		ArrayList<String> msgHexArray = new ArrayList<String>();
		for (String hex: response.replaceAll( "..(?!$)", "$0," ).split( "," ) ) {
			msgHexArray.add(hex);
		}
		
		// Conversão da data e da hora para inteiro
		int day = Integer.parseInt(msgHexArray.get(3), 16);
		int month = Integer.parseInt(msgHexArray.get(4), 16);
		int year = Integer.parseInt(msgHexArray.get(5), 16);
		int hour = Integer.parseInt(msgHexArray.get(6), 16);
		int minute = Integer.parseInt(msgHexArray.get(7), 16);
		int second = Integer.parseInt(msgHexArray.get(8), 16);
				
		// Construção da string para cálculo do CRC (bytes, frame, data, crc)
		crcCalc = "";
		for (int i = 1; i <= msgHexArray.size() - 2; i++) {
			crcCalc = crcCalc + msgHexArray.get(i);
		}
		
		// Conversão em array de bytes para cálculo do CRC
		inputCrc = DatatypeConverter.parseHexBinary(crcCalc);
		
		// Cálculo do CRC
		crc8.reset();
		crc8.update(inputCrc);
		hexCrc = Integer.toHexString((int) crc8.getValue());
		System.out.println(hexCrc);
		
		if (hexCrc.equals("0")) {
			System.out.println(msgHexArray);
			System.out.println("Hex CRC: " + hexCrc);
			System.out.println("Data/hora no fuso " + msgAscii + ": " 
				+ day + "/" + month + "/" + year + " " + hour + ":" + minute + ":" + second);
		}
	}
	
}
