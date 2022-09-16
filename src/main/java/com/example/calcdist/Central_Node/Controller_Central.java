package com.example.calcdist.Central_Node;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Controller_Central implements Runnable {
    private Socket client;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private static ArrayList<Central_Node> clients = new ArrayList<>();
    private static ExecutorService pool = Executors.newFixedThreadPool(5);

    public static void main(String[] args) throws IOException{
        ServerSocket listener = new ServerSocket(2000);
        while (true){
            Socket client = listener.accept();
            Central_Node central_thread = new Central_Node(client, clients);
            clients.add(central_thread);
            pool.execute((Runnable) central_thread);
        }
    }

    @Override
    public void run(){
        try {
            while(true){
                String msg = bufferedReader.readLine();
                if (msg != ""){
                    SendAll(msg);
                }

            }
        }catch (IOException e){
            e.printStackTrace();

        }
    }
    private void SendAll(String msg) throws IOException {
        for (Central_Node allClients : clients){
            allClients.bufferedWriter.write(msg);
        }

    }
}
