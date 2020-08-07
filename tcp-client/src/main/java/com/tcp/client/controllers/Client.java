package com.tcp.client.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Classe que trata o comportamento do cliente
 * @author Alex Juno Bócoli
 *
 */
public class Client {
	private Socket clientSocket;
	private PrintWriter out;
    private BufferedReader in;
 
    /**
     * Inicia a comunicação com o servidor
     * @param ip o ip utilizado pelo cliente
     * @param port a porta utilizada pelo cliente
     * @throws UnknownHostException
     * @throws IOException
     */
    public void startConnection(String ip, int port) throws UnknownHostException, IOException {
        clientSocket = new Socket(ip, port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }
    
    /**
     * Rotina de envio de mensagem ao servidor e recebimento da resposta
     * @param messageSent a mensagem a ser enviada
     * @return a resposta do servidor
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public String sendMessage(String messageSent) throws IOException, ClassNotFoundException {
    	out.println(messageSent);
    	String response = in.readLine();
    	return response;
    }
 
    /**
     * Finaliza a conexão com o servidor
     * @throws IOException
     */
    public void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }
}
