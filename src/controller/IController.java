package controller;

import exceptions.*;
import model.state.ProgramState;

import java.util.List;

public interface IController {
    //ProgramState oneStepExecution(ProgramState state) ;

    void allStepExecution() throws Exception;

    void displayCurrentProgramState();

    void introduceProgramState(ProgramState program);

    boolean getDisplayFlag();

    void setDisplayFlag(boolean flag);

    int getProgramStateID();

    //void setProgramStateID(int id) ;

    List<ProgramState> removeCompletedPrograms(List<ProgramState> inProgramList);

    void oneStepForAllPrograms(List<ProgramState> programList) throws InterruptedException;

    void initializeProgramState(int programID) ;
}
