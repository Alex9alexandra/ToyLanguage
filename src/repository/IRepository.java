package repository;

import exceptions.RepositoryInvalidProgramIndexException;
import model.state.ProgramState;

import java.io.IOException;
import java.util.List;

public interface IRepository {
    public List<ProgramState> getPrgList();

    //public ProgramState getCurrentProgramState();

    void addProgramState(ProgramState state);

    void removeProgramState(ProgramState state);

    int getCurrentIndex();

    void setCurrentIndex(int index) throws RepositoryInvalidProgramIndexException;

    void logProgramStateExecution(ProgramState prgState) throws IOException;

    void setPrgList(List<ProgramState> prgList);


}
