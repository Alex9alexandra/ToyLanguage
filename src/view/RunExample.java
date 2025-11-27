package view;
import java.util.Scanner;
import java.util.Stack;

import controller.Controller;
import exceptions.ArithmeticDivBy0Exception;
import exceptions.ArithmeticFirstOpNotIntException;
import exceptions.ArithmeticInvalidOpException;
import exceptions.VariableNotDefinedException;
import exceptions.*;
import model.statement.IfStatement;


public class RunExample extends Command{
    private Controller ctr;
    private boolean hasRun=false;
    private int programIndex;
    public RunExample(String key, String description, Controller ctr,int programIndex) {
        super(key, description);
        this.ctr=ctr;
        this.programIndex=programIndex;
    }
    @Override
    public void execute() {
        if(hasRun){
            System.out.println("The program has already been executed.");
            return;
        }
        try {
            Scanner scanner =new Scanner(System.in);
            System.out.println("Show steps? (1-y/0-n)");
            int display = scanner.nextInt();
            ctr.setDisplayFlag(display==1);
            ctr.setProgramStateID(programIndex);
            ctr.allStepExecution();
            hasRun=true;
        } catch (AddressNotInHeapException|
                ArithmeticDivBy0Exception|
                ArithmeticFirstOpNotIntException|
                ArithmeticInvalidOpException|
                ArithmeticSecOpNotIntException|
                AsignmentTypeMismatchException|
                ControllerExecutionStackIsEmptyException|
                FileAlreadyOpenException|
                FileCloseException|
                FileNotOpenException|
                FileOpenException|
                IfStatementNotEvalToBoolException|
                InvalidTypeException|
                LogicFirstOpNotBoolException|
                LogicSecOpNotBoolException|
                NotRefValueEvaluatedException|
                ProgStateExecStackIsEmpty|
                ReadFromHeapExceptions|
                RelationalInvalidOperatorException|
                RepositoryInvalidProgramIndexException|
                StackReadingFromEmptyStackException|
                VariableAlreadyDeclaredException|
                VariableNotDefinedException|
                WhileStatementNotEvalToBoolException|
                WriteToHeapExceptions|
                 InitializeLogFileException
                 e) {
            System.out.println("Runtime error: " + e.getMessage());

        } catch (Exception e) {
            System.out.println("Unexpected internal error: " + e.getMessage());
            e.printStackTrace(); // optional, for debugging
        }
    }
}
