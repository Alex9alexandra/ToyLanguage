package model.statement;

import exceptions.*;
import model.state.ProgramState;
import model.state.SymbolTable;
import model.type.Type;


public interface Statement {
    ProgramState execute(ProgramState state) throws VariableAlreadyDeclaredException,IfStatementNotEvalToBoolException,VariableNotDefinedException, AsignmentTypeMismatchException;

    String toString();
    Statement deepCopy();
    SymbolTable<String, Type> typeCheck(SymbolTable<String,Type> typeEnv) throws AsignmentTypeMismatchException, InvalidTypeException,IfStatementNotEvalToBoolException,WhileStatementNotEvalToBoolException;
}
