import controller.Controller;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.state.*;
import model.statement.Statement;
import model.type.Type;
import programs.Programs;
import repository.Repository;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class ProgramSelectionGUI extends Application {
    private Statement selectedProgram;
    private static final Set<Statement> executedPrograms=new HashSet<>();
    @Override
    public void start(Stage stage) {
        ListView<Statement> programListView =new ListView<>();
        programListView.getItems().addAll(Programs.hardcodedPrograms);
        programListView.setCellFactory(lv-> new ListCell<>() {
            @Override
            protected void updateItem(Statement stmt, boolean empty){
                super.updateItem(stmt,empty);
                setText(empty||stmt==null? null:stmt.toString());
            }
        });
        Button selectedButton=new Button("Select Program");
        selectedButton.setOnAction(e->{
            Statement selectedProgram=programListView.getSelectionModel().getSelectedItem();
            if(selectedProgram==null){
                new Alert(Alert.AlertType.WARNING,"Please select a program!").showAndWait();
                return;
            }
            if(executedPrograms.contains(selectedProgram))
            {
                new Alert(Alert.AlertType.WARNING,"This program has already been executed! Please select another one.").showAndWait();
                return;
            }
            executedPrograms.add(selectedProgram);
            programListView.getItems().removeAll(executedPrograms);
            try{
                selectedProgram.typeCheck(new MapSymbolTable<String, Type>());
            }catch(Exception ex){
                new Alert(Alert.AlertType.ERROR,"Typecheck error:\n"+ex.getMessage()).showAndWait();
                return;
            }
            ProgramState initialState=new ProgramState(new StackExecutionStack<>(),new MapSymbolTable<>(),new ListOut<>(),new MapFileTable(),new HeapTable<>(),selectedProgram);
            Repository repo=new Repository(initialState,"log"+selectedProgram.hashCode()+".txt");
            Controller controller=new Controller(repo);
            MainWindowGUI.setController(controller);

            try{
                Stage mainStage =new Stage();
                MainWindowGUI mainWindow = new MainWindowGUI(selectedProgram);
                mainWindow.start(mainStage);
                stage.hide();
                mainStage.setOnCloseRequest(event-> {stage.show();});
            }catch(Exception ex){
                ex.printStackTrace();
            }
        });
        VBox root=new VBox(10,programListView,selectedButton);
        stage.setScene(new Scene(root,800,600));
        stage.setTitle("Select a Program to Run");
        stage.show();

    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
