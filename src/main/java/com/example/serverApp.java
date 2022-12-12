package com.example;

import java.io.IOException;
import java.net.ServerSocket;

public class serverApp {
    public static void main(String[] args) throws IOException {
        ServerSocket server = Server.getInstance();
        System.out.println("Server started"+server);
    }
}