package model.statement;

import exceptions.FileCloseException;
import exceptions.FileNotOpenException;
import exceptions.InvalidTypeException;
import model.expression.Expression;
import model.state.ProgramState;
import model.value.StringValue;

import java.io.BufferedReader;
import java.io.IOException;

public record CloseRFileStatement(Expression expression) implements Statement {

    @Override
    public ProgramState execute(ProgramState state) {
        var value = expression.evaluate(state.symbolTable(), state.heap());

        if (!(value instanceof StringValue fileNameValue)) {
            throw new InvalidTypeException("Type must be String");
        }

        if (!state.fileTable().isOpen(fileNameValue)) {
            throw new FileNotOpenException("File " + fileNameValue.value() + " is not opened");

        }

        BufferedReader br = state.fileTable().getOpenFile(fileNameValue);
        try {
            br.close();
        } catch (IOException e) {
            throw new FileCloseException("Could not close file " + fileNameValue.value());
        }

        state.fileTable().closeFile(fileNameValue);
        return state;
    }

    @Override
    public String toString() {
        return "closeRFile(" + expression.toString() + ")";
    }

    @Override
    public Statement deepCopy() {
        return new CloseRFileStatement(expression.deepCopy());
    }

}