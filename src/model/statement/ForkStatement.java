package model.statement;

import exceptions.AsignmentTypeMismatchException;
import exceptions.IfStatementNotEvalToBoolException;
import exceptions.VariableAlreadyDeclaredException;
import exceptions.VariableNotDefinedException;
import model.state.ProgramState;
import model.state.StackExecutionStack;
import model.state.SymbolTable;
import model.value.Value;

public class ForkStatement implements Statement {
    Statement statement;

    public ForkStatement(Statement statement) {
        this.statement = statement;
    }

    @Override
    public ProgramState execute(ProgramState state) throws VariableAlreadyDeclaredException, IfStatementNotEvalToBoolException, VariableNotDefinedException, AsignmentTypeMismatchException {
        StackExecutionStack<Statement> newStack=new StackExecutionStack<>();
        newStack.push(statement);

        SymbolTable<String,model.value.Value> newSymbolTable=(SymbolTable<String, Value>) state.symbolTable().deepCopy();
        return new ProgramState(newStack,newSymbolTable,state.out(),state.fileTable(),state.heap(),statement);
    }

    @Override
    public Statement deepCopy() {
        return new ForkStatement(statement.deepCopy());
    }

    @Override
    public String toString() {
        return "fork("+ statement.toString() +")";
    }
}
