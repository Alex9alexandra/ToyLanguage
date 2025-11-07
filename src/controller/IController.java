package controller;

import exceptions.*;
import model.state.ProgramState;

public interface IController {
    ProgramState oneStepExecution(ProgramState state) ;

    void allStepExecution() throws ControllerExecutionStackIsEmptyException ;

    void displayCurrentProgramState();

    void introduceProgramState(ProgramState program);

    boolean getDisplayFlag();

    void setDisplayFlag(boolean flag);

    int getProgramStateID();

    void setProgramStateID(int id) ;
}
