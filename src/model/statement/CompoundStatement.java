package model.statement;

import model.state.ProgramState;
import model.state.SymbolTable;
import model.type.Type;

public record CompoundStatement(Statement first, Statement second) implements Statement {


    @Override
    public ProgramState execute(ProgramState state) {
        var stack = state.executionStack();
        stack.push(second);
        stack.push(first);
        return null;
    }

    @Override
    public String toString() {
        return "(" + first.toString() + "; " + second.toString() + ")";
    }

    @Override
    public Statement deepCopy() {
        return new CompoundStatement(first.deepCopy(), second.deepCopy());
    }

    @Override
    public SymbolTable<String, Type> typeCheck(SymbolTable<String, Type> typeEnv)  {
        return second.typeCheck(first.typeCheck(typeEnv));
    }
}
