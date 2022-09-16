package com.example.calcdist.Op2;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller_Op2 implements Initializable {
    @FXML
    Label lbl_op2;

    private Op2 op2;
    private String[] msg = {"", "", ""};
    private String Display = "";

    @Override
    public void initialize(URL location, ResourceBundle resoruces){
        try {
            op2 = new Op2(new Socket("localhost", 3000));
            System.out.println("Conectado");
        }catch (IOException e){
            e.printStackTrace();
        }
        op2.ReceiveMsg(msg);
        op2.Operation(msg, Display);
        lbl_op2.setText(Display);
        if (msg[0] == "5"){
            String msgToSend = msg[0] + "," + msg[1] + "," + msg[2];
            op2.SendMsg(msgToSend);
        }
    }
}
