package com.example.calcdist.Op1;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller_Op1 implements Initializable {
    @FXML
    Label lbl_op1;

    private Op1 op1;
    private String[] msg = {"", "", ""};
    private String Display = "";

    @Override
    public void initialize(URL location, ResourceBundle resoruces){
        try {
            op1 = new Op1(new Socket("localhost", 2000));
            System.out.println("Conectado");
        }catch (IOException e){
            e.printStackTrace();
        }
        op1.ReceiveMsg(msg);
        op1.Operation(msg, Display);
        lbl_op1.setText(Display);
        if (msg[0] == "5"){
            String msgToSend = msg[0] + "," + msg[1] + "," + msg[2];
            op1.SendMsg(msgToSend);
        }
    }
}
