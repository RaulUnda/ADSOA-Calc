package com.example.calcdist.app;

import java.io.*;
import java.net.Socket;

public class Calculator {
        private Socket socket;
        private BufferedReader bufferedReader;
        private BufferedWriter bufferedWriter;

        public Calculator(Socket socket)
        {
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
        public void AssignNum(String[] msg, String sign, String num1, String num2, String newNum) {
            if(sign == "") {
                num1 += newNum;
                msg[1] += newNum;
            }
          else if(sign != "")
            {
                num2 += newNum;
                msg[2] += newNum;
            }
        }
        public void Operation(String Op, String[] msg, String sign){
            switch(Op){
               case "+":
                    sign = "+";
                    msg[0] = "1";
                    break;
                case "-":
                    sign = "-";
                    msg[0] = "2";
                    break;
                case "*":
                    sign = "*";
                    msg[0] = "3";
                    break;
                case "/":
                    sign = "/";
                    msg[0] = "4";
                    break;
                default:
                    msg[0] = "0";
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

        public void ReceiveMsg(){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (socket.isConnected()){
                        try{
                            String receivedMsg = bufferedReader.readLine();
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