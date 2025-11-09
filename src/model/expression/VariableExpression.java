package model.expression;

import exceptions.VariableNotDefinedException;
import model.state.SymbolTable;
import model.value.Value;

public class VariableExpression implements Expression {

    String variableName;

    public VariableExpression(String variableName) {
        this.variableName = variableName;
    }

    @Override
    public Value evaluate(SymbolTable<String, Value> symTable) throws VariableNotDefinedException {
        if (!symTable.isDefined(variableName)) {
            throw new VariableNotDefinedException("Variable not defined");
        }
        return symTable.getValue(variableName);
    }

    @Override
    public String toString() {
        return variableName;
    }

    @Override
    public Expression deepCopy() {
        return new VariableExpression(variableName);
    }
}
