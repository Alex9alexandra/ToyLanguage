package model.statement;

import exceptions.*;
import model.expression.Expression;
import model.state.ProgramState;
import model.state.SymbolTable;
import model.type.StrType;
import model.type.Type;
import model.value.StringValue;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public record OpenRFileStatement(Expression expression) implements Statement{
    @Override
    public ProgramState execute(ProgramState state) throws VariableAlreadyDeclaredException, IfStatementNotEvalToBoolException, VariableNotDefinedException, AsignmentTypeMismatchException {
        var value = expression.evaluate(state.symbolTable(), state.heap());
        if(!(value instanceof StringValue stringValue)){
            throw new InvalidTypeException("Type must be String");
        }
        StringValue fileNameValue=stringValue;
        String fileName=fileNameValue.value();
        if(state.fileTable().isOpen(fileNameValue)){
            throw new FileAlreadyOpenException("File already open");
        }

        BufferedReader bufferReader;
        try {
            bufferReader = new BufferedReader(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            throw new FileOpenException("Cannot open file: " + fileName);
        }
        state.fileTable().addOpenFile(fileNameValue, bufferReader);

        return null;
    }

    @Override
    public String toString() {
        return "openRFile(" + expression.toString() + ")";    }

    @Override
    public Statement deepCopy() {
        return new OpenRFileStatement(expression.deepCopy());
    }

    @Override
    public SymbolTable<String, Type> typeCheck(SymbolTable<String, Type> typeEnv) throws InvalidTypeException {
        Type typexp=expression.typeCheck(typeEnv);
        if(typexp.equals(new StrType()))
            return typeEnv;
        else
            throw new InvalidTypeException("Type must be String");
    }
}
