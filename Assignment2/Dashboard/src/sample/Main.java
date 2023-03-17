/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.HashSet;
import java.util.concurrent.atomic.AtomicReference;

/**
 *
 * @author aashgar
 */
public class Main extends Application{

    //main controls:
    private ListView<String> listViewSource, listViewDest; //two listViews
    private TextField textFieldName;//input area
    private CheckBox checkBoxAll; //to decide to select all items in listView or not
    private RadioButton radioButtonDark, radioButtonLight; //to change theme
    private Button buttonAdd, buttonDel, buttonUpdate, buttonCopy, buttonClear;
    private AtomicReference<String> choice;
    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene;
        String names[] = {"Ahmad", "Huda"};
        listViewSource = new ListView(
                FXCollections.observableArrayList(names)
        );
        listViewSource.getSelectionModel().selectedItemProperty()
                .addListener(e->
                        textFieldName.setText(listViewSource.getSelectionModel().getSelectedItem())
                );
        listViewSource.setPrefSize(100, 150);
        listViewDest = new ListView();
        listViewDest.setPrefSize(100, 150);

        HBox hBox1 = new HBox(10, listViewSource, listViewDest);
        hBox1.setAlignment(Pos.CENTER);

        textFieldName = new TextField();
         choice = new AtomicReference<>("single");
        checkBoxAll = new CheckBox("Select All");
        checkBoxAll.setOnAction(
                e->
                {
                    if(choice.get().equalsIgnoreCase("single")){
                    listViewSource.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
                            choice.set("multiple");
                    }
                    else{
                    listViewSource.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
                            choice.set("single");
                    }

                }
        );

        radioButtonDark = new RadioButton("Dark");
        radioButtonLight = new RadioButton("Light");
        ToggleGroup toggleGroup = new ToggleGroup();
        radioButtonDark.setToggleGroup(toggleGroup);
        radioButtonLight.setToggleGroup(toggleGroup);
        radioButtonLight.setSelected(true);
        VBox themes = new VBox(20, radioButtonDark, radioButtonLight);
        themes.setPadding(new Insets(10));
        themes.setAlignment(Pos.CENTER);

        buttonAdd = new Button("Add");
        buttonDel = new Button("Delete");
        buttonUpdate = new Button("Update");
        buttonCopy = new Button("Copy");
        buttonClear = new Button("Clear");

        MyEventHandler myEventHandler = new MyEventHandler();
        buttonAdd.setOnAction(myEventHandler);
        buttonDel.setOnAction(myEventHandler);
        buttonUpdate.setOnAction(myEventHandler);
        buttonCopy.setOnAction(myEventHandler);
        buttonClear.setOnAction(myEventHandler);

        HBox hBox3 = new HBox(10, buttonAdd, buttonDel, buttonUpdate, buttonCopy, buttonClear);
        hBox3.setAlignment(Pos.CENTER);

        VBox vBox1 = new VBox(10, hBox1, textFieldName, checkBoxAll, hBox3);
        vBox1.setPadding(new Insets(20));
        vBox1.setAlignment(Pos.CENTER);

        HBox container = new HBox(vBox1,themes);
        FlowPane flowPane = new FlowPane(container);
        flowPane.setAlignment(Pos.CENTER);
        flowPane.getStyleClass().add("mainPane");

         scene = new Scene(flowPane);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        radioButtonDark.setOnAction(e->{

                    scene.getStylesheets().remove(getClass().getResource("style.css").toExternalForm());
                    scene.getStylesheets().add(getClass().getResource("darkTheme.css").toExternalForm());

                }

        );
        radioButtonLight.setOnAction(e->
                {
                    scene.getStylesheets().remove(getClass().getResource("darkTheme.css").toExternalForm());
                     scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());


                }
        );

        primaryStage.setScene(scene);
        primaryStage.setTitle("Main App");
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }

    private class MyEventHandler implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent event) {
            String buttonName = ((Button) event.getSource()).getText();
            String txtValue = textFieldName.getText();

            switch(buttonName){
                case "Add":
                   if (!check(textFieldName)&&!listViewSource.getItems().contains(txtValue)){
                            listViewSource.getItems().add(txtValue);
                   }
                    break;
                case "Delete":
                    if (!check(textFieldName)&&listViewSource.getItems().contains(txtValue)){
                        if(isSingle(listViewSource.getSelectionModel().getSelectionMode()))
                            listViewSource.getItems().remove(txtValue);
                        else{
                            listViewSource.getItems().removeAll(listViewSource.getSelectionModel().getSelectedItems());
                        }
                    }

                    break;
                case "Update":
                    if (!check(textFieldName))
                    listViewSource.getItems().set(listViewSource.getSelectionModel().getSelectedIndex(),txtValue);
                    break;
                case "Copy":

                    if (!check(textFieldName)&&listViewSource.getItems().contains(txtValue)){

                        if(!listViewDest.getItems().contains(txtValue)){
                            if(isSingle(listViewSource.getSelectionModel().getSelectionMode()))
                                listViewDest.getItems().add(txtValue);
                            else
                                listViewDest.getItems().addAll(listViewSource.getItems());
                        }


                }
                    break;
                case "Clear":
                        textFieldName.setText("");
                        listViewSource.getItems().clear();
                        listViewDest.getItems().clear();

                        break;

            }
        }
    }
    public static  boolean check(TextField textField){
        return textField.getText().isBlank();
    }
    public static boolean isSingle(SelectionMode mode){
        return mode.toString().equalsIgnoreCase("single");
    }


}