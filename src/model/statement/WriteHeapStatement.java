package model.statement;

import exceptions.AsignmentTypeMismatchException;
import exceptions.IfStatementNotEvalToBoolException;
import exceptions.VariableAlreadyDeclaredException;
import exceptions.VariableNotDefinedException;
import model.expression.Expression;
import model.state.ProgramState;
import model.type.RefType;
import model.value.RefValue;
import model.value.Value;

public class WriteHeapStatement implements Statement {
    String varName;
    Expression expression;
    public WriteHeapStatement(String varName, Expression expression) {
        this.varName = varName;
        this.expression = expression;
    }


    @Override
    public ProgramState execute(ProgramState state) throws VariableAlreadyDeclaredException, IfStatementNotEvalToBoolException, VariableNotDefinedException, AsignmentTypeMismatchException {

        var symbolTable = state.symbolTable();
        var heap = state.heap();

        if(!symbolTable.isDefined(varName))
        {
            throw new VariableNotDefinedException("Variable not defined: " + varName);
        }
        Value varValue = symbolTable.getValue(varName);
        if(!(varValue instanceof RefValue refValue))
        {
            throw new AsignmentTypeMismatchException("Variable " + varName + " is not of RefType");
        }
        int address=refValue.getAddress();
        if(!heap.containsAddress(address))
        {
            throw new VariableNotDefinedException("Address " + address + " is not defined in the heap");
        }
        Value evaluatedValue = expression.evaluate(symbolTable, heap);
        if(!evaluatedValue.getType().equals(refValue.getLocationType()))
        {
            throw new AsignmentTypeMismatchException("Type mismatch: variable " + varName + " and expression " + expression.toString());
        }
        heap.write(address, evaluatedValue);
        return state;
    }

    @Override
    public Statement deepCopy() {
        return new WriteHeapStatement(varName,expression.deepCopy());
    }

    @Override
    public String toString() {
        return "wH(" + varName + ", " + expression.toString() + ")";
    }
}
