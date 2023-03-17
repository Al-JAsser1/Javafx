package Students;

import javafx.application.Application;
import javafx.stage.Stage;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 *
 * @author aashgar
 */
public class UiDriver extends Application{


    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("StudentScreen.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent,600,600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Student Processing Screen");
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
