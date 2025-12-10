package repository;

import exceptions.InitializeLogFileException;
import exceptions.RepositoryInvalidProgramIndexException;
import model.state.*;
import model.statement.Statement;

import static programs.Programs.hardcodedPrograms;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Repository implements IRepository {
    private List<ProgramState> programStates;
    private int currentIndex;
    private String logFilePath;

    public Repository(ProgramState stmt,String path) {
        this.programStates=new ArrayList<>();
        programStates.add(stmt);
        this.logFilePath = path;

        try (PrintWriter writer=new PrintWriter(logFilePath)){
            writer.print("");
        } catch (IOException e) {
            throw new InitializeLogFileException("Could not initialize log file: " + e.getMessage());
        }
    }

    @Override
    public List<ProgramState> getPrgList() {
        return programStates;
    }


    @Override
    public void setPrgList(List<ProgramState> prgList) {
        this.programStates = prgList;
    }

//    @Override
//    public ProgramState getCurrentProgramState() {
//        return programStates.get(currentIndex);
//    }

    @Override
    public void addProgramState(ProgramState state) {
        programStates.add(state);
    }

    @Override
    public void removeProgramState(ProgramState state) {
        programStates.remove(state);
    }

    @Override
    public int getCurrentIndex() {
        return this.currentIndex;
    }

    @Override
    public void setCurrentIndex(int index) throws RepositoryInvalidProgramIndexException {
        if (index < 0 || index >= programStates.size())
            throw new RepositoryInvalidProgramIndexException("Invalid program state index");
        this.currentIndex = index;
    }

    @Override
    public void logProgramStateExecution(ProgramState prgState) throws IOException {
        PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
        logFile.println("Program State ID: " + prgState.getId());
        logFile.println(prgState.executionStack().toString());
        logFile.println(prgState.symbolTable().toString());
        logFile.println(prgState.heap().toString());
        logFile.println(prgState.out().toString());
        logFile.println(prgState.fileTable().toString());
        logFile.println("--------------------------------------------------");
        logFile.flush();
    }





//    public void initializeRepository() {
//        this.currentIndex = 0;
//        this.programStates = new ArrayList<>();
//
//        for (Statement stmt : hardcodedPrograms) {
//            ProgramState programState = new ProgramState(new StackExecutionStack<>(), new MapSymbolTable<>(), new ListOut<>(),new MapFileTable(),new HeapTable<>(), stmt);
//            this.programStates.add(programState);
//        }
//    }

}
