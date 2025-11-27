package model.statement;

import exceptions.FileNotOpenException;
import exceptions.FileReadException;
import exceptions.InvalidTypeException;
import exceptions.VariableNotDefinedException;
import model.expression.Expression;
import model.state.ProgramState;
import model.type.IntType;
import model.type.Type;
import model.value.IntegerValue;
import model.value.StringValue;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Objects;

public record ReadFileStatement(Expression expression, String varName) implements Statement {
    @Override
    public ProgramState execute(ProgramState state) {
        if (!state.symbolTable().isDefined(varName)) {
            throw new VariableNotDefinedException("Variable " + varName + " is not defined");
        }

        var varType = state.symbolTable().getType(varName);
        if (!(varType instanceof IntType)) {
            throw new InvalidTypeException("Variable " + varName + " must be of type int");
        }


        var value = expression.evaluate(state.symbolTable(), state.heap());
        if(!(value instanceof StringValue stringValue)){
            throw new InvalidTypeException("Type must be String");
        }
        StringValue fileNameValue=stringValue;
        String fileName=fileNameValue.value();
        if(!state.fileTable().isOpen(fileNameValue)){
            throw new FileNotOpenException("File " + fileName + " is not open");
        }
        BufferedReader br = state.fileTable().getOpenFile(fileNameValue);
        String line;
        try {
            line = br.readLine();
        } catch (IOException e) {
            throw new FileReadException(e.toString());
        }
        if(line == null){
            state.symbolTable().update(varName,new IntegerValue(0));
        }
        else{
            state.symbolTable().update(varName,new IntegerValue(Integer.parseInt(line)));
        }
        return state;
    }
    @Override
    public String toString() {
        return "readFile(" + expression.toString() + ", " + varName + ")";
    }

    @Override
    public Statement deepCopy() {
        return new ReadFileStatement(expression.deepCopy(), varName);
    }
}

