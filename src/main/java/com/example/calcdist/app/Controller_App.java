package com.example.calcdist.app;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller_App implements Initializable {
    @FXML
    private Label op;
    @FXML
    private Button btn_0, btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8,
            btn_9, btn_add, btn_subs, btn_mult, btn_div, btn_clr, btn_rslt;

    private Calculator calculator;
    private String Text = "";
    private String num1 = "";
    private String num2 = "";
    private String newVal = "";
    private String sign = "";
    private String[] msg = {"", "", ""};
    @Override
    public void initialize(URL location, ResourceBundle resources){
        try{
            calculator = new Calculator(new Socket("localhost", 1000));
            System.out.println("Conectado");
        }catch (IOException e){
            e.printStackTrace();
        }
        btn_0.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                calculator.AssignNum(msg, sign, num1, num2, newVal);
            }
        });
        btn_1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                calculator.AssignNum(msg, sign, num1, num2, newVal);
            }
        });
        btn_2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                calculator.AssignNum(msg, sign, num1, num2, newVal);
            }
        });
        btn_3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                calculator.AssignNum(msg, sign, num1, num2, newVal);
            }
        });
        btn_4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                calculator.AssignNum(msg, sign, num1, num2, newVal);
            }
        });
        btn_5.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                calculator.AssignNum(msg, sign, num1, num2, newVal);
            }
        });
        btn_6.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                calculator.AssignNum(msg, sign, num1, num2, newVal);
            }
        });
        btn_7.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                calculator.AssignNum(msg, sign, num1, num2, newVal);
            }
        });
        btn_8.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                calculator.AssignNum(msg, sign, num1, num2, newVal);
            }
        });
        btn_9.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                calculator.AssignNum(msg, sign, num1, num2, newVal);
            }
        });
        btn_add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (sign == null)
                {
                    Text = op.getText() + num1;
                    op.setText(Text);
                }
                newVal = btn_add.getText();
                calculator.Operation(newVal, msg, sign);
            }
        });
        btn_subs.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (sign == null)
                {
                    Text = op.getText() + num1;
                    op.setText(Text);
                }
                newVal = btn_subs.getText();
                calculator.Operation(newVal, msg, sign);
            }
        });
        btn_mult.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (sign == null)
                {
                    Text = op.getText() + num1;
                    op.setText(Text);
                }
                newVal = btn_mult.getText();
                calculator.Operation(newVal, msg, sign);
            }
        });
        btn_div.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (sign == null)
                {
                    Text = op.getText() + num1;
                    op.setText(Text);
                }
                newVal = btn_div.getText();
                calculator.Operation(newVal, msg, sign);
            }
        });
        btn_clr.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                num1 = "";
                num2 = "";
                newVal = "";
                sign = "";
                op.setText("");
            }
        });
        btn_rslt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (sign != null)
                {
                    Text = op.getText() + num2;
                    op.setText(Text);
                }
                newVal = btn_rslt.getText();
                if (msg[0] != null && msg[1] != null && msg[2] != null ){
                    String msgToSend = msg[0] + "," + msg[1] + "," + msg[2];
                    calculator.SendMsg(msgToSend);
                }
            }
        });
}}
