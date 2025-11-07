package repository;

import exceptions.RepositoryInvalidProgramIndexException;
import model.state.*;
import model.statement.Statement;

import static programs.Programs.hardcodedPrograms;

import java.util.ArrayList;
import java.util.List;

public class Repository implements IRepository {
    List<ProgramState> programStates;
    int currentIndex;

    public Repository() {
        this.initializeRepository();
    }

    @Override
    public List<ProgramState> getProgramStates() {
        return programStates;
    }

    @Override
    public ProgramState getCurrentProgramState() {
        return programStates.get(currentIndex);
    }

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

    public void initializeRepository() {
        this.currentIndex = 0;
        this.programStates = new ArrayList<>();

        for (Statement stmt : hardcodedPrograms) {
            ProgramState programState = new ProgramState(new StackExecutionStack<>(), new MapSymbolTable<>(), new ListOut<>(), stmt);
            this.programStates.add(programState);
            //System.out.println(this.programStates.size());
        }
    }

}
