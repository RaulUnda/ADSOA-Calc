package com.example.calcdist.Central_Node;

import javafx.scene.control.Label;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Central_Node {
    private Socket socket;
    private BufferedReader bufferedReader;
    BufferedWriter bufferedWriter;
    private ArrayList<Central_Node> clients;

    public Central_Node(Socket socket, ArrayList<Central_Node> clients) throws IOException {
        try {
            this.socket = socket;
            this.clients = clients;
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
            bufferedWriter.close();
            bufferedReader.close();
        }
    }
    public void run() throws IOException{
        try{
            while (true){
                String request = bufferedReader.readLine();
            }
        }catch (IOException e){
            e.printStackTrace();
            bufferedReader.close();
            bufferedWriter.close();
        }
    }

}
