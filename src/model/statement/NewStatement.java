package model.statement;

import exceptions.AsignmentTypeMismatchException;
import exceptions.IfStatementNotEvalToBoolException;
import exceptions.VariableAlreadyDeclaredException;
import exceptions.VariableNotDefinedException;
import model.expression.Expression;
import model.state.ProgramState;
import model.type.RefType;
import model.type.Type;
import model.value.IntegerValue;
import model.value.RefValue;
import model.value.Value;

public record NewStatement(String variableName, Expression expression) implements Statement {
    @Override
    public ProgramState execute(ProgramState state) throws VariableAlreadyDeclaredException, IfStatementNotEvalToBoolException, VariableNotDefinedException, AsignmentTypeMismatchException {
        var symbolTable = state.symbolTable();
        var heap = state.heap();

        if(!symbolTable.isDefined(variableName))
        {
            throw new VariableNotDefinedException("Variable not defined: " + variableName);
        }
        var varType=symbolTable.getType(variableName);
        if(!(varType instanceof RefType refType))
        {
            throw new AsignmentTypeMismatchException("Variable " + variableName + " is not of RefType");
        }

        Value value = expression.evaluate(symbolTable, heap);
        if(!value.getType().equals(refType.getInner()))
        {
            throw new AsignmentTypeMismatchException("Type mismatch: variable " + variableName + " and expression " + expression.toString());
        }
        int address=heap.allocate(value);
        symbolTable.update(variableName, new RefValue(address,refType.getInner()));
        return state;
    }

    @Override
    public Statement deepCopy() {
        return new NewStatement(variableName,expression.deepCopy());
    }

    @Override
    public String toString() {
        return "new(" + variableName + ", " + expression.toString() + ")";
    }
}
