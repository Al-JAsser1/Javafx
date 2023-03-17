package sample;

import javafx.beans.InvalidationListener;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Controller implements EventHandler {
    Stage primaryStage;
   public Controller(Stage primaryStage){
        this.primaryStage = primaryStage;
    }
    public  void changeFontSize(Slider slider, TextArea workSpace){
        workSpace.setStyle("-fx-font-size: "+ slider.getValue() + "pt;");
    }

    public  void openFile(TextArea workSpace, Event event){





    }
    public void saveFile(TextArea workSpace) {


    }

    @Override
    public void handle(Event event) {
    }
}
