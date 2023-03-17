
package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class LoginUI extends Application{

    //login labels:
    private Label labelTitle, labelError;
    private TextField textFieldLoginName;
    private PasswordField passwordField;
    private Button buttonSubmit, buttonCancel;// control buttons

    /**
     *
     * start method:
     * loads the login UI
     * */
    @Override
    public void start(Stage primaryStage) throws Exception {
        labelTitle = new Label("Login Information");
        HBox label = new HBox(labelTitle);
        label.setAlignment(Pos.CENTER);
        textFieldLoginName = new TextField();
        textFieldLoginName.setAlignment(Pos.CENTER);
        textFieldLoginName.setPromptText("Login Name");

        passwordField = new PasswordField();
        passwordField.setAlignment(Pos.CENTER);
        passwordField.setPromptText("Password");



        Button submit = new Button("submit");
        submit.setAlignment(Pos.CENTER_LEFT);
        Button cancel = new Button("cancel");
        cancel.setAlignment(Pos.CENTER_RIGHT);


        GridPane form = new GridPane();
        form.setVgap(10);
        form.setHgap(10);
        form.addRow(0,label);
        form.addRow(1,textFieldLoginName);
        form.addRow(2,passwordField);
        HBox buttons = new HBox(5,submit,cancel);
        buttons.setAlignment(Pos.CENTER);
        form.addRow(4,buttons);
                        labelError = new Label();
                        labelError.setAlignment(Pos.CENTER);
                        labelError.setId("label-error");
                        form.addRow(3,labelError);

//buttons' actions:
        submit.setOnAction( e->checkUser(primaryStage));
        cancel.setOnAction(e->{
                    textFieldLoginName.setText("");
                    passwordField.setText("");
                    labelError.setText("");
                });

        form.setPadding(new Insets(10, 20, 10, 20));
        form.setAlignment(Pos.CENTER);
        BorderPane borderContainer = new BorderPane();
        borderContainer.setCenter(form);
        Scene scene = new Scene(borderContainer, 400, 300);
        scene.getStylesheets().add("file:src/sample/style.css");
        primaryStage.setScene(scene);
        primaryStage.setTitle("Login Screen");
        primaryStage.show();
    }

    public static void main(String[] args){
        launch(args);}

    //check if user is on file
    public  void checkUser(Stage primaryStage){
        File users = new File("src/sample/users");
        try {

            Scanner userScanner = new Scanner(users);
            boolean isFound  = false;
            while (userScanner.hasNext()){
                String[] userInfo = userScanner.nextLine().split(",");
                if
                (userInfo[0].equals(textFieldLoginName.getText())
                        &&userInfo[1].equals(passwordField.getText())){
                    labelError.setText("Welcome "+userInfo[0]);
                    primaryStage.setScene(new Scene(new MainScreen(primaryStage)));
                    isFound = true;
                    break;

                }else{
                    isFound = false;
                }

            }
            if(!isFound)
                labelError.setText("Invalid info! check your inputs");
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        }
    }



}