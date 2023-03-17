package Students;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author aashgar
 */
public class Controller implements Initializable {
//controls:
    @FXML
    private TextField textFieldName;
    @FXML
    private TextField textFieldID;
    @FXML
    private TextField textFieldGrade;
    @FXML
    private ListView<String> listViewStudents;

    @FXML
    private ComboBox<String> sortByOption;
    @FXML
    private TextField textFieldMajor;
    @FXML
    private TextField textFieldRange;
    @FXML
    private ComboBox<String> groupBy = new ComboBox<>();
    @FXML
    private Button buttonClear;
    @FXML
    private Button buttonAdd;
    @FXML
    private Button gradeRange;
    @FXML
    private Button deleteButton;
    @FXML
    private Button avgButton;

    //Actions:
    @Override //initialize the application with initial data and components!
    public void initialize(URL url, ResourceBundle rb) {
        loadListContent();//load contents from list to Ui
        groupBy.getItems().addAll("Grade","Major");
        sortByOption.getItems().addAll("Grade","Name");
    }
    @FXML //handles the add button
    private void buttonAddHandle() {
        //creates new student object with form input and add it to the list
        StudentHandler.getInstance().getStudents().add(
                new Student(textFieldID.getText(),
                        textFieldName.getText(),
                        textFieldMajor.getText().toUpperCase(Locale.ROOT),
                        Double.parseDouble(textFieldGrade.getText())));
        //loads the contents from the students list again to the viewList
        loadListContent();
    }

    @FXML//handles the clear button
    private void buttonClearHandle() {
        Stage clearConfirm = new Stage();
        BorderPane container = new BorderPane();
        Scene main = new Scene(container,300,200);
        Label msg = new Label("Are you sure to clear the list?");
        Button clearAll = new Button("Clear the list permanently");
        Button clearUi = new Button("Clear the list in Ui");
        clearAll.setOnAction(e->
        {   StudentHandler.getInstance().getStudents().clear();
            listViewStudents.getItems().clear();
            clearConfirm.close();
        });
        clearUi.setOnAction(e->{
            listViewStudents.getItems().clear();
            clearConfirm.close();
        });
        HBox choices = new HBox(10,clearAll,clearUi);
        choices.setAlignment(Pos.CENTER);
        container.setCenter(msg);
        container.setBottom(choices);
        clearConfirm.setScene(main);
        clearConfirm.setTitle("Confirm Clear");
        clearConfirm.setResizable(false);
        clearConfirm.show();
    }
    @FXML//sort by name or grade
    void getSortBy(ActionEvent event) {
            switch (sortByOption.getSelectionModel().getSelectedItem().toLowerCase(Locale.ROOT)){
                case "name":
                    StudentHandler.getInstance().sortStudents();
                    loadListContent();
                    break;
                case "grade":
                    listViewStudents.getItems().clear();
                           StudentHandler.getInstance().studentsSortedGrades()
                                   .forEach((k,v)->listViewStudents.getItems()
                                           .add(String.format("%-5s %-10s",k,v)));
                    break;
                default:
                    return;

            }
    }

    @FXML//gives the avg
    void getAvg(ActionEvent event) {
    Stage avgStage  = new Stage();
    avgStage.setScene(new Scene
            (new BorderPane
                    (new Label
                            (StudentHandler.getInstance().getGradeAvg()+"")
                    ),200,200));
    avgStage.setTitle("Average");
    avgStage.setResizable(false);
    avgStage.show();
    }
    @FXML
    void getGradesWithRange(){
        listViewStudents.getItems().clear();
        StudentHandler.getInstance().studentsMapWithRange(textFieldRange.getText())
                .forEach((k,v)->listViewStudents.getItems()
                .add(String.format("%-5s %-10s",k,v)));
    }
    @FXML
    void getGroupBy(ActionEvent event) {
        listViewStudents.getItems().clear();
        StudentHandler.getInstance().groupBy(groupBy.getSelectionModel().getSelectedItem())
                .forEach((k,v)->listViewStudents.getItems()
                        .add(String.format("%-5s %-10s",k,v.stream().map(student -> student.getName()).collect(Collectors.joining(",")))));
    }
    @FXML
    void deleteButton(ActionEvent event) {
        listViewStudents.getItems()
                .remove(listViewStudents.getSelectionModel().getSelectedItem());
    }

    public void loadListContent(){
        listViewStudents.getItems().clear();
        StudentHandler.getInstance().getStudents()
                .stream().forEach(
                        student ->
                                listViewStudents.getItems().add(String.format("%-5s %-10s %-7s %8.2f",
                        student.getId(),
                        student.getName(),
                        student.getMajor(),
                        student.getGrade()
                )));

    }

}