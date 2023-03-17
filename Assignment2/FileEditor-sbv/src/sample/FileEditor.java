package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

public class FileEditor extends Pane {
    @FXML
    private MenuItem blueItem;

    @FXML
    private TextArea workSpace;

    @FXML
    private MenuItem redItem;

    @FXML
    private MenuItem save;

    @FXML
    private Menu colorMenu;

    @FXML
    private WebView view;

    @FXML
    private MenuItem greenItem;

    @FXML
    private MenuItem close;

    @FXML
    private Slider fontSizeSlider;

    @FXML
    private Menu fileMenu;

    @FXML
    private MenuItem open;

    @FXML
    private ComboBox<String> linksCombo;

    @FXML
    private MenuBar mainMenuBar;
    public  Pane createPane(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        loader.setController(this);
        Pane main = loader.load();

        fontSizeSlider.setMin(5);
        fontSizeSlider.setMax(35);
        fontSizeSlider.setValue(12);
        fontSizeSlider.setBlockIncrement(5);
        fontSizeSlider.setMajorTickUnit(5);
        fontSizeSlider.setShowTickLabels(true);
//Actions on controllers:
        Controller actionController = new Controller(primaryStage);
        fontSizeSlider.valueProperty().addListener( e-> actionController.changeFontSize(fontSizeSlider,workSpace));
        AtomicReference<File> chosenFile = new AtomicReference<>();
//files menu actions:
        open.setOnAction(event -> {
            FileChooser fileOpener = new FileChooser();
            fileOpener.setInitialDirectory(new File("C:\\Users\\pc\\Desktop"));
            chosenFile.set( (fileOpener.showOpenDialog(primaryStage)));
            Scanner choseFileReader = null;
            try {
                choseFileReader = new Scanner(chosenFile.get());
            } catch (FileNotFoundException exception) {
                exception.printStackTrace();
            }
            while (choseFileReader.hasNext())
                workSpace.appendText(choseFileReader.nextLine() + "\n");
        });
        save.setOnAction(event->{

            if(chosenFile.get()!=null){
                try {
                    FileWriter writer = new FileWriter(chosenFile.get());
                    for (String word : workSpace.getText().split(" "))
                        writer.append(word+" ");
                    writer.close();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }

            }
            });

        close.setOnAction(event->{
            workSpace.setText("");
        });
        //colors menu actions:
        colorMenu.getItems().stream().forEach(color ->color.setOnAction(e-> workSpace.setStyle("-fx-text-fill: "+ color.getText())));
        //links actions:
        linksCombo.getItems().addAll(Links.Student.toString(),Links.Instructor.toString());
        linksCombo.setStyle("-fx-text-fill: black;");
        linksCombo.setOnAction(e->{
            String option = linksCombo.getSelectionModel().getSelectedItem();
            view.getEngine().load(Links.valueOf(option).getLink());
        });
return main;
    }

}
