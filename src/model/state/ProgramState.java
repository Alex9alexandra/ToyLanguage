package model.state;

import model.statement.Statement;
import model.value.Value;

public class ProgramState {
    ExecutionStack<Statement> executionStack;
    SymbolTable<String, Value> symbolTable;
    Out<Value> out;
    Statement originalProgram;

    public ProgramState(ExecutionStack<Statement> executionStack, SymbolTable<String, Value> symbolTable, Out<Value> out, Statement program) {
        this.executionStack = executionStack;
        this.symbolTable = symbolTable;
        this.out = out;
        this.originalProgram = deepCopy(program);
        this.executionStack.push(program);
    }

    @Override
    public String toString() {
        return "Execution Stack: " + executionStack.toString() + "\n" +
                "Symbol Table: " + symbolTable.toString() + "\n" +
                "Output: " + out.toString() + "\n";
    }

    private Statement deepCopy(Statement program) {
        return program;
    }

    public void setExecutionStack(ExecutionStack<Statement> executionStack) {
        this.executionStack = executionStack;
    }

    public ExecutionStack<Statement> executionStack() {
        return executionStack;
    }

    public void setSymbolTable(SymbolTable<String, Value> symbolTable) {
        this.symbolTable = symbolTable;
    }

    public SymbolTable<String, Value> symbolTable() {
        return symbolTable;
    }

    public void setOut(Out<Value> out) {
        this.out = out;
    }

    public Out<Value> out() {
        return out;
    }

    public void setOriginalProgram(Statement program) {
        this.originalProgram = program;
    }

    public Statement originalProgram() {
        return originalProgram;
    }

}

