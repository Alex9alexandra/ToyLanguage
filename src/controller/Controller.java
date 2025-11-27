package controller;

import exceptions.*;
import model.value.RefValue;
import model.value.Value;
import repository.IRepository;
import model.state.ExecutionStack;
import model.state.ProgramState;
import model.statement.Statement;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
                program.heap().setContent(unsafeGarbageCollector(program.symbolTable().getContent().values(),program.heap().getContent()));
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

    public Map<Integer, Value> unsafeGarbageCollector(Collection<Value> symTableValues, Map<Integer,Value> heap) {

        List<Integer> roots = getAddrFromTable(symTableValues);
        List<Integer> reacheble= computeReachebleAddresses(roots,heap);
        return heap.entrySet().stream()
                .filter(e -> reacheble.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    List<Integer> getAddrFromTable(Collection<Value> symTableValues)
    {
        return symTableValues.stream()
                .filter(v->v instanceof RefValue)
                .map(v->((RefValue) v).getAddress())
                .collect(Collectors.toList());
    }

    List<Integer> computeReachebleAddresses(List<Integer> startingAddresses,Map<Integer,Value> heap) {
        List<Integer> reacheble=new java.util.ArrayList<>(startingAddresses);
        boolean changed;
        do{
            changed=false;
            List<Integer> newAddress=heap.entrySet().stream()
                    .filter(entry->reacheble.contains(entry.getKey()))
                    .map(Map.Entry::getValue)
                    .filter(v->v instanceof RefValue)
                    .map(v->((RefValue) v).getAddress())
                    .filter(addr->!reacheble.contains(addr))
                    .collect(Collectors.toList());
            if(!newAddress.isEmpty()) {
                reacheble.addAll(newAddress);
                changed=true;
            }

        }while(changed);
        return reacheble;

    }
}
