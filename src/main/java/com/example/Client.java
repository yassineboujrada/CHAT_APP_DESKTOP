package com.example;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

import com.example.DAO_IMPLEMENTATION.chat_implementation;
import com.example.MODELS.*;

import javafx.application.Platform;

public class Client {
    public static Socket client;
    // static login account;
    // start
    public void start(chatController ch) throws IOException {
        client = new Socket("localhost", 5000);
        // get the output stream from the socket.
        OutputStream output = client.getOutputStream();
        // create a DataOutputStream so we can write data
        DataOutputStream out = new DataOutputStream(output);
        // send the message to the server
        System.out.println("Sending message to the server...");
        // out.writeUTF(App.getUser().getId() + "");
        System.out.println(ch.account.getUsername());
        out.writeUTF(ch.account.getUsername() + "");
        // lunch a thread to listen to the server
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        // get the input stream from the client
                        InputStream input = client.getInputStream();
                        ObjectInputStream MessageObject = new ObjectInputStream(input);
                        // read the object
                        chat msg = (chat) MessageObject.readObject();
                        System.out.println("raje3"+msg.getMessage());
                        // add the message to the list
                        // App.addMessage(msg);
                        ch.add_message(msg.getMessage(),"10:20",ch.account.getUsername());
                        
                    }
                    // Platform.runLater(() -> {
                    //     // labTime.setText(time);
                    //     ch.add_message(msg.getMessage(),"10:20",ch.account.getUsername());
                    // });

                    
                    
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    public static void sendMsg(chat msg) throws IOException{
        // get the input stream from the client
        OutputStream Output = client.getOutputStream();
        ObjectOutputStream MessageObject = new ObjectOutputStream(Output);
        // read the object
        MessageObject.writeObject(msg);
        chat_implementation chat = new chat_implementation();
        chat.addMsg(msg);
    }
}
