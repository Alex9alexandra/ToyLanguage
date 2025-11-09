package model.statement;

import exceptions.AsignmentTypeMismatchException;
import exceptions.IfStatementNotEvalToBoolException;
import exceptions.VariableAlreadyDeclaredException;
import exceptions.VariableNotDefinedException;
import model.state.ProgramState;


public interface Statement {
    ProgramState execute(ProgramState state) throws VariableAlreadyDeclaredException,IfStatementNotEvalToBoolException,VariableNotDefinedException, AsignmentTypeMismatchException;

    String toString();
    Statement deepCopy();
}
