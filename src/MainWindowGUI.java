import controller.Controller;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.state.ProgramState;
import model.statement.Statement;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.Scene;
import model.value.StringValue;
import model.value.Value;
import java.io.BufferedReader;

import java.util.*;

public class MainWindowGUI extends Application {
    private static Statement program;
    private static Controller controller;
    private TextField numPrgStatesField;
    private TableView<HeapEntry> heapTable;
    private ListView<String> outListView;
    private ListView<String> fileTableListView;
    private ListView<Integer> prgStateIdsListView;
    private TableView<SymbolTableEntry> symbolTableView;
    private ListView<String> executionStackView;
    private Button runOnStepButton;


    public static void setProgram(Statement program) {
        MainWindowGUI.program = program;
    }

    public static void setController(Controller controller) {
        MainWindowGUI.controller = controller;
    }

    public MainWindowGUI(Statement program)
    {
        this.program=program;
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        heapTable=new TableView<>();
        heapTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<HeapEntry,Integer> addressColumn=new TableColumn<>("Address");
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        TableColumn<HeapEntry,String> valueColumn=new TableColumn<>("Value");
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
        heapTable.getColumns().clear();
        heapTable.getColumns().addAll(addressColumn,valueColumn);

        outListView= new ListView<>();
        fileTableListView=new ListView<>();
        prgStateIdsListView=new ListView<>();

        prgStateIdsListView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> updateSelectedProgramStateDetails());


        symbolTableView=new TableView<>();
        symbolTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        TableColumn<SymbolTableEntry,String> varNameCol=new TableColumn<>("Variable");
        varNameCol.setCellValueFactory(new PropertyValueFactory<>("variableName"));
        TableColumn<SymbolTableEntry,String> varValueCol=new TableColumn<>("Value");
        varValueCol.setCellValueFactory(new PropertyValueFactory<>("value"));
        symbolTableView.getColumns().clear();
        symbolTableView.getColumns().addAll(varNameCol,varValueCol);
        executionStackView=new ListView<>();

        runOnStepButton=new Button("Run one step");
        runOnStepButton.setOnAction(e -> runOneStep());

        numPrgStatesField=new TextField();
        numPrgStatesField.setEditable(false);


        VBox leftPanel=new VBox(10,new Label("Number of Program States"), numPrgStatesField,new Label("Program States: "),prgStateIdsListView,new Label("ExeStack: "),executionStackView);
        VBox rightPanel=new VBox(10,new Label("Heap Table: "),heapTable,new Label("Symbol Table: "),symbolTableView,new Label("Output: "),outListView,new Label("File Table: "),fileTableListView,runOnStepButton);
        leftPanel.setPadding(new Insets(10));
        rightPanel.setPadding(new Insets(10));
        HBox root=new HBox(20,leftPanel,rightPanel);
        Scene scene =new Scene(root,1200,600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Interpreter");
        primaryStage.show();

        updatePrgStateIdsList();
        updateSelectedProgramStateDetails();
    }

    public static class HeapEntry{
        private Integer address;
        private String value;

        public HeapEntry(Integer address, String value) {
            this.address = address;
            this.value = value;
        }

        public Integer getAddress() {
            return address;
        }

        public String getValue() {
            return value;
        }
    }

    public static class SymbolTableEntry {
        private String name;
        private String value;

        public SymbolTableEntry(String name, String value) {
            this.name = name;
            this.value = value;
        }

        public String getVariableName() {
            return name;
        }

        public String getValue() {
            return value;
        }
    }

    private void updatePrgStateIdsList()
    {
        prgStateIdsListView.getItems().clear();
        for(ProgramState prg: controller.getProgramStates())
        {
            prgStateIdsListView.getItems().add(prg.getId());
        }

        if(!prgStateIdsListView.getItems().isEmpty())
        {
            prgStateIdsListView.getSelectionModel().selectFirst();
            updateSelectedProgramStateDetails();
        }
    }

    private void updateSelectedProgramStateDetails()
    {
        Integer selectedId=prgStateIdsListView.getSelectionModel().getSelectedItem();
        if(selectedId==null)
            return;
        ProgramState selectedPrg=controller.getProgramStates().stream()
                .filter(prg->prg.getId()==selectedId)
                .findFirst()
                .orElse(null);
        if(selectedPrg==null)
            return;

        List<Statement> stackElem=new ArrayList<>(selectedPrg.executionStack().getContent());
        Collections.reverse(stackElem);
        executionStackView.getItems().clear();
        for (Statement stmt : stackElem) {
            executionStackView.getItems().add(stmt.toString());
        }

        symbolTableView.getItems().clear();
        for(Map.Entry<String, Value> entry : selectedPrg.symbolTable().getContent().entrySet())
        {
            symbolTableView.getItems().add(new SymbolTableEntry(entry.getKey(), entry.getValue().toString()));
        }

        heapTable.getItems().clear();
        Map<Integer, Value> heapMap = (Map<Integer,Value>) selectedPrg.heap().getContent();
        for (Map.Entry<Integer, Value> entry : heapMap.entrySet()) {
            heapTable.getItems().add(new HeapEntry(entry.getKey(), entry.getValue().toString()));
        }

        outListView.getItems().clear();
        for(Value val : selectedPrg.out().getContent())
        {
            outListView.getItems().add(val.toString());
        }

        fileTableListView.getItems().clear();
        Map<StringValue,BufferedReader> fileTable = (Map<StringValue,BufferedReader>) selectedPrg.fileTable().getContent();
        for(Map.Entry<StringValue, BufferedReader> entry : fileTable.entrySet())
        {
            StringValue sv=entry.getKey();
            fileTableListView.getItems().add(sv.getInnerValue());
        }

        numPrgStatesField.setText(String.valueOf(controller.getProgramStates().size()));
    }

    private void runOneStep(){
        try{
            controller.oneGUIStep();
            updatePrgStateIdsList();
            updateSelectedProgramStateDetails();

            if(controller.getProgramStates().isEmpty())
            {
                runOnStepButton.setDisable(true);
                prgStateIdsListView.setDisable(true);
            }
        }
        catch(Exception e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR,"Runtime error: "+e.getMessage());
            alert.showAndWait();
        }
    }

}
