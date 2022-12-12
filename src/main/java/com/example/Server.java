package com.example;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.example.MODELS.*;

public class Server {
    // create a singletons instance
    private static ServerSocket instance = null;
    // constructor
    private Server() throws IOException {
        Server.instance = start();
    }
    public ServerSocket start() throws IOException {
        ServerSocket server = new ServerSocket(5000);
        // lunch client handler thread
        new Thread(new Runnable() {
            @Override
            public void run() {
                Map<String,Socket> sockets = new HashMap<String,Socket>();
                while (true) {
                    try{
                        Socket client = server.accept();
                        // get the input stream from the client
                        InputStream input = client.getInputStream();
                        // create a DataInputStream so we can read data from it.
                        DataInputStream inst = new DataInputStream(input);
                        String message = inst.readUTF();
                        //  to int
                        // int id = Integer.parseInt(message);
                        // System.out.println("received id : "+id);
                        sockets.put(message,client);
                        new Thread(
                            new Runnable(){
                                @Override
                                public void run() {
                                    try {
                                        while (true) {
                                            // receive an object from the client
                                            InputStream input = client.getInputStream();
                                            ObjectInputStream MessageObject = new ObjectInputStream(input);
                                            // read the object
                                            chat msg = (chat) MessageObject.readObject();
                                            System.out.println("Message received from client: " +msg.getSender_id() + " to " + msg.getReciver_id() + " : " + msg.getMessage());
                                            // get the receiver id
                                            String receiverId = msg.getReciver_id();
                                            // find the receiver socket
                                            Socket receiverSocket = sockets.get(receiverId);
                                            System.out.println(" receiver "+ receiverSocket);
                                            if(receiverSocket == null) continue;
                                            System.out.println("receiverSocket"+receiverSocket);
                                            // send the message object to the receiver
                                            OutputStream output = receiverSocket.getOutputStream();
                                            ObjectOutputStream MessageObjectOut = new ObjectOutputStream(output);
                                            MessageObjectOut.writeObject(msg);
                                        }
                                    } catch (IOException | ClassNotFoundException e) {
                                        System.out.println("message error : "+e.getMessage());
                                    }
                                }
                            }
                        ).start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } 
                    }
                }
            }).start();
        System.out.println("Server started");
        return server;
    }
    // get instance
    public static ServerSocket getInstance() throws IOException {
        if (instance == null) {
            new Server();
        }
        return instance;
    }
}
