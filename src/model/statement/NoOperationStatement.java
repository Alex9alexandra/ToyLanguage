package model.statement;

import model.state.ProgramState;
import model.state.SymbolTable;
import model.type.Type;

public class NoOperationStatement implements Statement {
    @Override
    public ProgramState execute(ProgramState state) {
        return null;
    }

    @Override
    public String toString() {
        return "nop";
    }

    @Override
    public Statement deepCopy() {
        return new NoOperationStatement();
    }

    @Override
    public SymbolTable<String, Type> typeCheck(SymbolTable<String, Type> typeEnv)  {
        return typeEnv;
    }
}
