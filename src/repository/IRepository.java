package repository;

import exceptions.RepositoryInvalidProgramIndexException;
import model.state.ProgramState;

import java.io.IOException;
import java.util.List;

public interface IRepository {
    public List<ProgramState> getProgramStates();

    public ProgramState getCurrentProgramState();

    void addProgramState(ProgramState state);

    void removeProgramState(ProgramState state);

    int getCurrentIndex();

    void setCurrentIndex(int index) throws RepositoryInvalidProgramIndexException;

    void logProgramStateExecution() throws IOException;
}
