package com.example.calcdist.show;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Show_Result {
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;

    public Show_Result (Socket socket){
        try {
            this.socket = socket;
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        }catch (IOException e){
            e.printStackTrace();
            closeAll(socket, bufferedReader, bufferedWriter);
        }
    }
    public void sendMsg(String msg){
        try{
            bufferedWriter.write(msg);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        }catch (IOException e){
            e.printStackTrace();
            closeAll(socket, bufferedReader, bufferedWriter);
        }
    }
    public void receiveMsg(String[] msg){
        new Thread(new Runnable() {
            @Override
            public void run() {
                int flag = 0;
                while (socket.isConnected()){
                    try {
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
                    }catch (IOException e)
                    {
                        e.printStackTrace();
                        closeAll(socket, bufferedReader, bufferedWriter);
                        break;
                    }
                }
            }
        }).start();
    }

    public void closeAll(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        try{
            if (bufferedReader != null){
                bufferedReader.close();
            }
            if (bufferedWriter != null){
                bufferedWriter.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
