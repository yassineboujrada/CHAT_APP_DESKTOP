// package Controllers.Employer.Authentification;
package com.example;

import java.io.IOException;
import java.util.ArrayList;

import com.example.DAO_IMPLEMENTATION.login_implementation;
import com.example.MODELS.login;

// import org.controlsfx.control.MaskerPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.*;


public class loginController{
    private String compte;
    
    login_implementation log = new login_implementation();

    @FXML
    public TextField email_text;

    // @FXML
    // private Line email_line, pass_line;

    @FXML
    private Pane general_pane;

    @FXML
    public PasswordField password_label;

    @FXML
    private Button signin_btn,signup_btn;

    @FXML AnchorPane achnopane;

    // @FXML
    // private Label pass_word;

    @FXML
    private Label label_to_manage_error;

    @FXML
    private ImageView image_login;
    public Pane ParentPane;
    public ArrayList<String> request;

    // Switch To Sign Up page of Employer
    @FXML
    public void SwitchToSignUp(ActionEvent event) throws IOException {
        App.setRoot("primary");
    }

    @FXML
    public void login_formule(ActionEvent event) {
        if (email_text.getText().contains("@gmail.com")) {
            StartConnection(email_text.getText(), password_label.getText());
            System.out.println("yeah");
        } else {
            email_text.setStyle("-fx-background-color:#e00b0b99;");
            password_label.setStyle("-fx-background-color:#e00b0b99;");
        }
    }

    private void StartConnection(String mail, String pass) {
        try{
            System.out.println(email_text.getText());
            login data = log.getData(mail);
            if(data.getEmail().equals(mail)){
                if(data.getPassword().equals(pass)){
                    System.out.println("Connected");
                    App.account = data;
                    System.out.println(App.account.getEmail());
                    App.setRoot("main_app");
                }else{
                    label_to_manage_error.setText("Password incorrect");
                }
            }else{
                label_to_manage_error.setText("Email incorrect");
            }
        }catch (Exception ex){
            ex.printStackTrace();
            label_to_manage_error.setText("Something incorrect");
        }
    }

    private void SwitchToHomePage() throws IOException {
        App.setRoot("secondary");
    }
}
