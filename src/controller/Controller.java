package controller;

import exceptions.*;
import model.state.*;
import model.value.RefValue;
import model.value.Value;
import programs.Programs;
import repository.IRepository;
import model.statement.Statement;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Controller implements IController {
    IRepository repo;
    boolean displayFlag;
    ExecutorService executor;

    public Controller(IRepository repo) {
        this.repo = repo;
    }


//    @Override
//    public ProgramState oneStepExecution(ProgramState state) throws ControllerExecutionStackIsEmptyException {
//        ExecutionStack<Statement> stack = state.executionStack();
//        if (stack.isEmpty())
//            throw new ControllerExecutionStackIsEmptyException("Execution stack is empty");
//        Statement currentStatement = stack.pop();
//        return currentStatement.execute(state);
//    }

//    @Override
//    public void allStepExecution() throws Exception {
//        ProgramState program = repo.getCurrentProgramState();
//        displayCurrentProgramState();
//        repo.logProgramStateExecution();
//        while (!program.executionStack().isEmpty()) {
//            try {
//                oneStepExecution(program);
//                displayCurrentProgramState();
//                repo.logProgramStateExecution();
//                program.heap().setContent(unsafeGarbageCollector(program.symbolTable().getContent().values(),program.heap().getContent()));
//                repo.logProgramStateExecution();
//            } catch (Exception e) {
//                System.out.println(e.getMessage());
//                break;
//            }
//        }
//    }

    public void allStepExecution() throws Exception {
        executor = Executors.newFixedThreadPool(2);
        List<ProgramState> programList = removeCompletedPrograms(repo.getPrgList());
        while (programList.size() > 0) {
            if(displayFlag==true)
                displayCurrentProgramState();

            Heap<Value> heap = programList.get(0).heap();
            Map<Integer,Value> newHeap= unsafeGarbageCollector(
                    programList.stream()
                            .map(prg -> prg.symbolTable().getContent().values())
                            .flatMap(Collection::stream)
                            .collect(Collectors.toList()),
                    heap.getContent()
            );
            heap.setContent(newHeap);
            programList = removeCompletedPrograms(programList);
            if(programList.size()==0)
                break;
            try{
                oneStepForAllPrograms(programList);
            } catch(Exception e)
            {
                System.out.println("Runtime error: "+e.getMessage());
                break;
            }

            programList = removeCompletedPrograms(repo.getPrgList());

        }
        executor.shutdownNow();
        repo.setPrgList(programList);
    }

    @Override
    public void displayCurrentProgramState() {
        if (displayFlag == true)
            repo.getPrgList().forEach(prg->System.out.println(prg.toString()));
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

//    @Override
//    public void setProgramStateID(int id) {
//        this.repo.setCurrentIndex(id);
//    }

    @Override
    public List<ProgramState> removeCompletedPrograms(List<ProgramState> inProgramList) {
        return inProgramList.stream().filter(p->p.isNotCompleted()).collect(Collectors.toList());
    }

    @Override
    public void oneStepForAllPrograms(List<ProgramState> programList) throws InterruptedException {
        programList.forEach(prg-> {

            try {
                repo.logProgramStateExecution(prg);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });
        List<Callable<ProgramState>> callList =programList.stream().map((ProgramState p)->(Callable<ProgramState>)(p::oneStepExecution)).collect(Collectors.toList());
        List<ProgramState> newProgramList=executor.invokeAll(callList).stream()
                .map(future->
                {try {
                    return future.get();
                }catch(ExecutionException e){
                    if(e.getCause() instanceof ProgramStateStackIsEmptyException){
                        return null;
                    }
                    else {
                        throw new RuntimeException(e.getCause());
                    }
                }
                catch(Exception e){
                    throw new RuntimeException(e);
                }
                })
                .filter(p->p!=null)
                .collect(Collectors.toList());
        programList.addAll(newProgramList);
        programList.forEach(prg-> {
                    try {
                        repo.logProgramStateExecution(prg);
                    } catch (IOException e)
                    {
                        throw new RuntimeException(e);
                    }
                });
        repo.setPrgList(programList);
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

    @Override
    public void initializeProgramState(int programIndex)
    {
        Statement stmt = Programs.hardcodedPrograms.get(programIndex);

        stmt.typeCheck(new MapSymbolTable<>());

        ProgramState programState = new ProgramState(
                new StackExecutionStack<>(),
                new MapSymbolTable<>(),
                new ListOut<>(),
                new MapFileTable(),
                new HeapTable<>(),
                stmt
        );
        repo.setPrgList(List.of(programState));
    }
}
