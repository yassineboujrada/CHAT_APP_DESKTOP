package com.example;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;

import com.example.DAO_IMPLEMENTATION.*;
import com.example.MODELS.chat;
import com.example.MODELS.login;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class chatController implements Initializable{
    static login account;
    static Client client;
    static String recive;

    @FXML
    private Label date,msg_label;

    @FXML
    private TextField mesg_input;

    @FXML
    private Button send_btn;

    @FXML
    private VBox vbox_msg_to_show;

    @FXML
    private VBox accounts_list;
    AnchorPane panel_hold_msg = new AnchorPane();
    List<login> users = new login_implementation().getAllData();

    private void users_componnent(String user_name,String date_send){
        AnchorPane panel = new AnchorPane();
        panel.setId(user_name);
        panel.setPrefHeight(71.0);
        panel.setPrefWidth(249.0);
        panel.setStyle("-fx-background-color: #6785ff;");

        Label user_name_label = new Label(user_name);
        user_name_label.setLayoutX(94.0);
        user_name_label.setLayoutY(25.0);
        user_name_label.setPrefHeight(21.0);
        user_name_label.setPrefWidth(163.0);
        user_name_label.setStyle("-fx-text-fill: #f4f5fd; -fx-font-size: 15;");

        ImageView user_img = new ImageView();
        user_img.setFitHeight(52.0);
        user_img.setFitWidth(62.0);
        user_img.setLayoutX(23.0);
        user_img.setLayoutY(8.0);
        user_img.setPickOnBounds(true);
        user_img.setPreserveRatio(true);
        user_img.setImage(new javafx.scene.image.Image(getClass().getResourceAsStream("/com/example/images/user.png")));

        panel.getChildren().addAll(user_name_label,user_img);
        panel.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, (e) -> {
            recive=((AnchorPane) e.getSource()).getId();
            System.out.println("clicked"+((AnchorPane) e.getSource()).getId());
            vbox_msg_to_show.getChildren().clear();
            for (chat chat : new chat_implementation().getMsg(((AnchorPane) e.getSource()).getId(), account.getUsername())) {
                try {
                    add_message(chat.getMessage(), chat.getDate_send().toString(), chat.getSender_id());
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
        accounts_list.setSpacing(5);
        accounts_list.getChildren().add(panel);
    }
    
    @FXML
    void send_message(ActionEvent event) throws IOException {
        add_message(mesg_input.getText(), "20:20",account.getUsername());
        Client.sendMsg(new chat(account.getUsername(),recive, mesg_input.getText(), null));
    }

    @FXML
    void send_message_by_key(KeyEvent event) throws IOException {
        if(event.getCode().toString().equals("ENTER")){
            System.out.println(mesg_input.getText());
            add_message(mesg_input.getText(), "20:20",account.getUsername());
            Client.sendMsg(new chat(account.getUsername(),recive, mesg_input.getText(), null));
        }
    }

    void add_message(String msg,String date_of_snd,String send) throws IOException{
        
        AnchorPane panel = new AnchorPane();
        panel.setPrefHeight(71.0);
        panel.setPrefWidth(646.0);
        panel.setLayoutX(16.0);

        Label msg_label = new Label("  "+msg);
        msg_label.setLayoutX(33.0);
        msg_label.setLayoutY(10.0);
        msg_label.setPrefHeight(27.0);
        msg_label.setPrefWidth(401.0);

        if(send.equals(account.getUsername())){
            msg_label.setStyle("-fx-background-color: #05a3ed; -fx-background-radius: 8; -fx-border-radius: 8; -fx-text-fill:#fff;");}
        else{
            msg_label.setStyle("-fx-background-color: #fff; -fx-background-radius: 8; -fx-border-radius: 8;");
        }

        Label date = new Label(date_of_snd);
        date.setLayoutX(33.0);
        date.setLayoutY(42.0);
        date.setPrefHeight(27.0);
        date.setPrefWidth(129.0);
        date.setStyle("-fx-font-size: 12; -fx-text-fill: #999; -fx-font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;");
        panel.getChildren().addAll(msg_label,date);
        
        vbox_msg_to_show.getChildren().add(panel);
        
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        System.out.println("imean "+App.account);
        account = App.account;
        
        client =new Client();
        System.out.println("init sock"+account.getUsername());
        try {
            client.start(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        for (login account_ : users) {
            if (account_.getUsername().equals(account.getUsername())) {
                continue;
            }
            else{
                users_componnent(account_.getUsername(), "20:20");
            }
        }

        
    }


    // accounts_list.getChildren().add((Pane) new FXMLLoader(App.class.getResource("account_template.fxml")).load());

}
