package com.example.calcdist.show;

import com.example.calcdist.Central_Node.Central_Node;
import com.example.calcdist.Central_Node.Controller_Central;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller_Show implements Initializable {
    @FXML
    Label lbl_rslt;
    private Show_Result show_result;
    private ArrayList<Controller_Central> clients = new ArrayList<>();

    private String[] msg = {"","",""};
    @Override
    public void initialize(URL location, ResourceBundle resources){
        try {
            show_result = new Show_Result(new Socket("localhost", 4000));
        }catch (IOException e){
            e.printStackTrace();
        }
        show_result.receiveMsg(msg);
        if(msg[0] == "5"){
            lbl_rslt.setText(msg[1]);
        }
    }
}
