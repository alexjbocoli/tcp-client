package com.tcp.client.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	private Socket clientSocket;
    //private ObjectOutputStream out;
    //private ObjectInputStream in;
	private PrintWriter out;
    private BufferedReader in;
 
    public void startConnection(String ip, int port) throws UnknownHostException, IOException {
        clientSocket = new Socket(ip, port);
        //out = new ObjectOutputStream(clientSocket.getOutputStream());
        //in = new ObjectInputStream(clientSocket.getInputStream());
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }
    
    public String sendMessage(String messageSent) throws IOException, ClassNotFoundException {
    	out.println(messageSent);
    	String response = in.readLine();
    	return response;
    }
 
    public void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }
}
