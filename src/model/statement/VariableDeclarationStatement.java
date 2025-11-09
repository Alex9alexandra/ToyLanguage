package model.statement;

import exceptions.VariableAlreadyDeclaredException;
import model.type.Type;
import model.state.ProgramState;

public record VariableDeclarationStatement(Type type, String variableName) implements Statement {


    @Override
    public ProgramState execute(ProgramState state) throws VariableAlreadyDeclaredException {
        var symbolTable = state.symbolTable();
        if (symbolTable.isDefined(variableName)) {
            throw new VariableAlreadyDeclaredException("Variable already defined");
        }
        symbolTable.declareVariable(variableName, type);
        return state;
    }

    @Override
    public String toString() {
        return type.toString() + " " + variableName;
    }

    @Override
    public Statement deepCopy() {
        return new VariableDeclarationStatement(type.deepCopy(), variableName);
    }
}
