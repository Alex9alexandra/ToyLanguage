package model.statement;

import exceptions.IfStatementNotEvalToBoolException;
import model.expression.Expression;
import model.state.ProgramState;
import model.state.SymbolTable;
import model.type.BoolType;
import model.type.Type;
import model.value.BooleanValue;
import model.value.Value;

public record IfStatement(Expression condition, Statement thenBranch, Statement elseBranch) implements Statement {

    @Override
    public ProgramState execute(ProgramState state) throws IfStatementNotEvalToBoolException{
        Value result = condition.evaluate(state.symbolTable(), state.heap());
        if (result instanceof BooleanValue(boolean value)) {
            if (value) {
                state.executionStack().push(thenBranch);
            } else {
                state.executionStack().push(elseBranch);
            }
        } else {
            throw new IfStatementNotEvalToBoolException("Condition expression does not evaluate to a boolean.");
        }
        return null;
    }

    @Override
    public String toString() {
        return "if (" + condition.toString() + ") then (" + thenBranch.toString() + ") else (" + elseBranch.toString() + ")";
    }

    @Override
    public Statement deepCopy() {
        return new IfStatement(condition.deepCopy(), thenBranch.deepCopy(), elseBranch.deepCopy());
    }


    @Override
    public SymbolTable<String, Type> typeCheck(SymbolTable<String, Type> typeEnv) throws IfStatementNotEvalToBoolException {
        Type typexp=condition.typeCheck(typeEnv);
        if(typexp.equals(new BoolType()))
        {
            thenBranch.typeCheck(typeEnv.deepCopy());
            elseBranch.typeCheck(typeEnv.deepCopy());
            return typeEnv;
        }
        else
            throw new IfStatementNotEvalToBoolException("Condition expression does not evaluate to a boolean.");
    }
}

