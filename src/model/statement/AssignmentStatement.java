package model.statement;

import exceptions.AsignmentTypeMismatchException;
import exceptions.VariableNotDefinedException;
import model.expression.Expression;
import model.value.Value;
import model.state.ProgramState;
import model.state.SymbolTable;

public record AssignmentStatement(Expression expression, String variableName)
        implements Statement {

    @Override
    public ProgramState execute(ProgramState state) throws VariableNotDefinedException, AsignmentTypeMismatchException {
        SymbolTable<String, Value> symbolTable = state.symbolTable();
        if (!symbolTable.isDefined(variableName)) {
            throw new VariableNotDefinedException("Variable not defined");
        }
        Value value = expression.evaluate(symbolTable, state.heap());
        if (!value.getType().equals(symbolTable.getType(variableName))) {
            throw new AsignmentTypeMismatchException("Type mismatch");
        }
        symbolTable.update(variableName, value);
        return null;
    }

    @Override
    public String toString() {
        return variableName + " = " + expression.toString();
    }

    @Override
    public Statement deepCopy() {
        return new AssignmentStatement(expression.deepCopy(), variableName);
    }
}
