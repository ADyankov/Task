package com.tcp.client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by blabla on 05-Feb-19.
 */
public class TcpClient {

    public static void main(String args[]) throws Exception {
        System.out.println("Starting client");
        while(true) {
            Socket clientSocket = null;
            try {
                clientSocket = new Socket(System.getenv("HOST"), Integer.parseInt(System.getenv("PORT")));
                DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
                BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                outToServer.writeBytes("Hi boss " + System.lineSeparator());
                outToServer.flush();
                String modifiedSentence = inFromServer.readLine();
                System.out.println("FROM SERVER: " + modifiedSentence);
            } catch(Exception e) {
                System.out.println("Still waiting: " + e.getMessage());
            } finally {
                if(clientSocket != null && !clientSocket.isClosed()) {
                    clientSocket.close();
                }
            }
            Thread.sleep(1000);
        }
    }
}
