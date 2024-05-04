package org.example;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;


public class TCPServer {
    public static void main(String[] args) throws Exception {
        readAsBytes();
    }

    @SneakyThrows
    public static void readAsBytes() {
        ServerSocket outFirstServerSocket = new ServerSocket(6789);
        while(true) {
            System.out.println("Waiting for connection...");
            Socket connection = outFirstServerSocket.accept();//waiting
            System.out.println("Connection accepted");
            DataInputStream dataInputStream = new DataInputStream(connection.getInputStream());
            byte[] arr = readMessage(dataInputStream);
            System.out.println("message length2:"+arr.length);
            FileUtility.writeIntoFileBytes("C:\\Users\\elvin\\Desktop\\cls.jpg", arr);
        }
    }

    @SneakyThrows
    public static byte[] readMessage(DataInputStream din) {
       int msgLength = din.readInt();
        System.out.println("Message length1: " + msgLength);
       byte[] msg = new byte[msgLength];
       din.readFully(msg);
       return msg;
    }

    @SneakyThrows
    public static void readAsString() {
        ServerSocket outFirstServerSocket = new ServerSocket(6789);
        while(true) {
            System.out.println("Waiting for connection...");
            Socket connection = outFirstServerSocket.accept();//waiting
            System.out.println("Connection accepted");
            InputStream is = connection.getInputStream();
            InputStreamReader reader = new InputStreamReader(is);
            BufferedReader bReader = new BufferedReader(reader);

            String messageFromClient = bReader.readLine();
            System.out.println("Message from client: " + messageFromClient);
        }
    }
}
