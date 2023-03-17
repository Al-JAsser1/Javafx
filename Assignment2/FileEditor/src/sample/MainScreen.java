package sample;

import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

/**
 *
 * @author aashgar
 */
public class MainScreen extends BorderPane{

    MenuBar mainMenu;
    TextArea workspace;
    Slider fontSize;


    public MainScreen(Stage primaryStage) {
        mainMenu = createMenuBar(primaryStage);
        this.setTop(mainMenu);

         workspace = createTextArea();
         fontSize = createSlider(5,30,12);

        VBox textBox = new VBox(10,workspace,fontSize);
        this.setCenter(textBox);
        ComboBox<String> links = createComboBox();
        links.setPromptText("go to");
        links.getSelectionModel().selectFirst();
        WebView linkView = new WebView();
        System.out.println(WebView.getClassCssMetaData());
        links.setOnAction(e->{ linkView.getEngine().load("https://github.com/salemAmassi"); });
        HBox linksContainer = new HBox(10,links,linkView);
        this.setBottom(linksContainer);





    }
    public TextArea createTextArea(){
        TextArea workSpace = new TextArea();
        workSpace.setPromptText("Enter Text here");
        workSpace.setPrefColumnCount(20);
        workSpace.setPrefColumnCount(30);
        return workSpace;
    }
    public Slider createSlider(int min, int max,int defaultValue){
        Slider fontSize = new Slider(min,max,defaultValue);
        fontSize.setMajorTickUnit(5);
        fontSize.setMinorTickCount(4);
        fontSize.setShowTickLabels(true);
        fontSize.setShowTickMarks(true);
        fontSize.setSnapToTicks(true);
        fontSize.valueProperty().addListener(
                e->
                        workspace.setStyle("-fx-font-size:"+fontSize.getValue()+"pt")
        );
        return fontSize;

    }
    public MenuBar createMenuBar(Stage primaryStage){
      MenuBar  mainMenu = new MenuBar();
        Menu fileMenu = new Menu("File");
        MenuItem open = new MenuItem("Open");
        AtomicReference<File> chosenFile = new AtomicReference<>();
        open.setOnAction(
                e->{
                    FileChooser fileOpener = new FileChooser();
                    fileOpener.setInitialDirectory(new File("C:\\Users\\pc\\Desktop"));
                   chosenFile.set(fileOpener.showOpenDialog(primaryStage));
                    try {
                        Scanner choseFileReader = new Scanner(chosenFile.get());
                        while(choseFileReader.hasNext()){
                            this.workspace.appendText(choseFileReader.nextLine()+"\n");
                        }
                    } catch (FileNotFoundException exception) {
                        exception.printStackTrace();
                    }


                }
        );

        MenuItem close = new MenuItem("Close");
        close.setOnAction(
                e->
                        workspace.setText("")
        );

        MenuItem save = new MenuItem("Save");
        save.setOnAction(e-> {
                    try {
                        FileWriter fileWriter = new FileWriter(chosenFile.get());
                        for (String word:workspace.getText().split(" ")) {
                            fileWriter.append(word);
                        }
                        fileWriter.close();
                    } catch (IOException exception) {
                        exception.printStackTrace();
                    }
                });
        fileMenu.getItems().addAll(open,close,save);

        Menu colorsMenu = new Menu("Color");
        for (MyColor color: MyColor.values()) {
            MenuItem colorItem = new MenuItem(color.toString());
            colorsMenu.getItems().add(colorItem);
            colorItem.setOnAction(e->workspace.setStyle("-fx-text-fill:"+ colorItem.getText()));
        }

        mainMenu.getMenus().addAll(fileMenu, colorsMenu);
        return mainMenu;
    }
    public ComboBox createComboBox(){
        ComboBox links = new ComboBox();
        links.getItems().add("Student's github");
        return links;
    }
    public WebView createWebView(){
        return new WebView();
    }


}