package model.statement;

import exceptions.AsignmentTypeMismatchException;
import exceptions.IfStatementNotEvalToBoolException;
import exceptions.VariableAlreadyDeclaredException;
import exceptions.VariableNotDefinedException;
import model.expression.Expression;
import model.state.ProgramState;
import model.state.SymbolTable;
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
        return null;
    }

    @Override
    public Statement deepCopy() {
        return new NewStatement(variableName,expression.deepCopy());
    }

    @Override
    public SymbolTable<String, Type> typeCheck(SymbolTable<String, Type> typeEnv) throws AsignmentTypeMismatchException {
        Type typevar=typeEnv.getType(variableName);
        Type typexp=expression.typeCheck(typeEnv);
        if(typevar.equals(new RefType(typexp)))
            return typeEnv;
        else
            throw new AsignmentTypeMismatchException("Variable " + variableName + " is not of RefType");
    }

    @Override
    public String toString() {
        return "new(" + variableName + ", " + expression.toString() + ")";
    }
}
