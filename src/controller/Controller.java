package controller;

import exceptions.*;
import repository.IRepository;
import model.state.ExecutionStack;
import model.state.ProgramState;
import model.statement.Statement;

public class Controller implements IController {
    IRepository repo;
    boolean displayFlag;

    public Controller(IRepository repo) {
        this.repo = repo;
    }


    @Override
    public ProgramState oneStepExecution(ProgramState state) throws ControllerExecutionStackIsEmptyException {
        ExecutionStack<Statement> stack = state.executionStack();
        if (stack.isEmpty())
            throw new ControllerExecutionStackIsEmptyException("Execution stack is empty");
        Statement currentStatement = stack.pop();
        return currentStatement.execute(state);
    }

    @Override
    public void allStepExecution() throws Exception {
        ProgramState program = repo.getCurrentProgramState();
        displayCurrentProgramState();
        repo.logProgramStateExecution();
        while (!program.executionStack().isEmpty()) {
            try {
                oneStepExecution(program);
                displayCurrentProgramState();
                repo.logProgramStateExecution();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                break;
            }
        }
    }

    @Override
    public void displayCurrentProgramState() {
        if (displayFlag == true)
            System.out.println(repo.getCurrentProgramState().toString());
    }

    @Override
    public void introduceProgramState(ProgramState program) {
        repo.addProgramState(program);
    }

    @Override
    public boolean getDisplayFlag() {
        return this.displayFlag;
    }

    @Override
    public void setDisplayFlag(boolean flag) {
        this.displayFlag = flag;
    }

    @Override
    public int getProgramStateID() {
        return this.repo.getCurrentIndex();
    }

    @Override
    public void setProgramStateID(int id) {
        this.repo.setCurrentIndex(id);
    }


}
