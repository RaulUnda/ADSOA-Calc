package com.example.calcdist.Op1;

import java.io.*;
import java.net.Socket;

public class Op1 {
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;

    public Op1 (Socket socket){
        try {
            this.socket = socket;
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        }catch (IOException e) {
            System.out.println("Error creando el cliente");
            e.printStackTrace();
            closeAll(socket, bufferedReader, bufferedWriter);
        }
    }
    public void Operation(String[] msg, String operation){
        int add, subs, mult, div = 0;
        switch (msg[0])
        {
            case "1":
                add = Integer.parseInt(msg[1]) + Integer.parseInt(msg[2]);
                operation = msg[1] + " + " + msg[2] + " = ";
                msg[0] = "5";
                msg[1] = Integer.toString(add);
                msg[2] = "";
                operation += msg[1];
                break;
            case "2":
                subs = Integer.parseInt(msg[1]) - Integer.parseInt(msg[2]);
                operation = msg[1] + " - " + msg[2] + " = ";
                msg[0] = "5";
                msg[1] = Integer.toString(subs);
                msg[2] = "";
                operation += msg[1];
                break;
            case "3":
                mult = Integer.parseInt(msg[1]) * Integer.parseInt(msg[2]);
                operation = msg[1] + " * " + msg[2] + " = ";
                msg[0] = "5";
                msg[1] = Integer.toString(mult);
                msg[2] = "";
                operation += msg[1];
                break;
            case "4":
                operation = msg[1] + " / " + msg[2] + " = ";
                if (msg[2] == "0"){
                    msg[0] = "5";
                    msg[1] = "ERROR";
                    msg[2] = "";
                    operation += msg[1];
                }
                else{
                    div = Integer.parseInt(msg[1]) / Integer.parseInt(msg[2]);
                    msg[0] = "5";
                    msg[1] = Integer.toString(div);
                    msg[2] = "";
                    operation += msg[1];
                }
                break;
            default:
                msg[0] = "0";
                msg[1] = "ERROR";
                msg[2] = "";
                break;
        }
    }

    public void SendMsg(String msgToSend){
        try {
            bufferedWriter.write(msgToSend);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (IOException e){
            e.printStackTrace();
            closeAll(socket, bufferedReader, bufferedWriter);
        }
    }

    public void ReceiveMsg(String[] msg){
        new Thread(new Runnable() {
            @Override
            public void run() {
                int flag = 0;
                while (socket.isConnected()){
                    try{
                        String receivedMsg = bufferedReader.readLine();
                        for(int i=0; i<receivedMsg.length(); i++)
                        {
                            if(receivedMsg.charAt(i) == ','){
                                flag += 1;
                            }
                            else {
                                msg[flag] += receivedMsg.charAt(i);
                            }
                        }
                    }catch (IOException e) {
                        e.printStackTrace();
                        closeAll(socket,bufferedReader,bufferedWriter);
                    }
                }
            }
        }).start();
    }


    public void closeAll(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        try {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
