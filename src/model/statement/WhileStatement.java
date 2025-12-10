package model.statement;

import exceptions.*;
import model.expression.Expression;
import model.state.ProgramState;
import model.state.SymbolTable;
import model.type.BoolType;
import model.type.Type;
import model.value.BooleanValue;
import model.value.Value;

public class WhileStatement implements Statement{
    Expression expression;
    Statement body;

    public WhileStatement(Expression expression, Statement body) {
        this.expression = expression;
        this.body = body;
    }

    @Override
    public ProgramState execute(ProgramState state) throws WhileStatementNotEvalToBoolException,VariableAlreadyDeclaredException, IfStatementNotEvalToBoolException, VariableNotDefinedException, AsignmentTypeMismatchException {
        Value val=expression.evaluate(state.symbolTable(), state.heap());

        if(val instanceof BooleanValue(boolean value)) {
            if(!value) {
                return state;
            }
            else
            {
                state.executionStack().push(this);
                state.executionStack().push(body);
            }
        }
        else
            throw new WhileStatementNotEvalToBoolException("While condition not evaluated to boolean");
        return null;
    }

    @Override
    public Statement deepCopy() {
        return new WhileStatement(expression.deepCopy(),body.deepCopy());
    }

    @Override
    public SymbolTable<String, Type> typeCheck(SymbolTable<String, Type> typeEnv) throws WhileStatementNotEvalToBoolException {
        Type t=expression.typeCheck(typeEnv);
        if(!t.equals(new BoolType()))
            throw new WhileStatementNotEvalToBoolException("While condition not evaluated to boolean");
        body.typeCheck(typeEnv.deepCopy());
        return typeEnv;
    }

    @Override
    public String toString() {
        return "while(" + expression.toString() + ") { " + body.toString() + " }";
    }
}
