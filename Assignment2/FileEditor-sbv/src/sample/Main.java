package sample;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

public class Main extends Application {
    @FXML
    private Label msg;

    @FXML
    private TextField userNameField;

    @FXML
    private Button cancleButton;

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passwordField;




    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("login.fxml"));
        loginLoader.setController(this);

        FlowPane loginPane = loginLoader.load();
      loginPane.setAlignment(Pos.CENTER);

      //actions:
        loginButton.setOnAction(e->checkUser(primaryStage));
        cancleButton.setOnAction(e->{
            userNameField.setText("");
            passwordField.setText("");
            msg.setText("");
        });
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(loginPane, 600, 400));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    public  void checkUser(Stage primaryStage){
        File users = new File("src/sample/users");
        try {

            Scanner userScanner = new Scanner(users);
            boolean isFound  = false;
            while (userScanner.hasNext()){
                String[] userInfo = userScanner.nextLine().split(",");
                if
                (userInfo[0].equals(userNameField.getText())
                        &&userInfo[1].equals(passwordField.getText())){
                    msg.setText("Welcome "+userInfo[0]);
                    primaryStage.setScene(new Scene(new FileEditor().createPane(primaryStage),500,500));
                    isFound = true;
                    break;

                }else{
                    isFound = false;
                }

            }
            if(!isFound)
                msg.setText("Invalid info! check your inputs");
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
