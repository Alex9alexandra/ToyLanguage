package model.statement;

import model.expression.Expression;
import model.state.ProgramState;
import model.state.SymbolTable;
import model.type.Type;

public record PrintStatement(Expression expression) implements Statement {

    @Override
    public ProgramState execute(ProgramState state)  {
        state.out().add(expression.evaluate(state.symbolTable(), state.heap()));
        return null;
    }

    @Override
    public String toString() {
        return "print(" + expression.toString() + ")";
    }

    @Override
    public Statement deepCopy() {
        return new PrintStatement(expression.deepCopy());
    }

    @Override
    public SymbolTable<String, Type> typeCheck(SymbolTable<String, Type> typeEnv)  {
        expression.typeCheck(typeEnv);
        return typeEnv;
    }
}
