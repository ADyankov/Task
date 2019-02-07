package com.tcp.server;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by blabla on 05-Feb-19.
 */
public class TCPServer {

    public static void main(String argv[]) throws Exception {

        System.out.println("Starting server");
        String clientSentence;
        ServerSocket welcomeSocket = new ServerSocket(Integer.parseInt(System.getenv("PORT")));

        while (true) {
            Socket connectionSocket = welcomeSocket.accept();
            BufferedReader inFromClient =
                    new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
            clientSentence = inFromClient.readLine();
            System.out.println("Received: " + clientSentence);
            PrintWriter writer = new PrintWriter(outToClient, true);
            writer.println("This is a message sent to the server");
        }
    }

}
