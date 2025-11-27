package model.statement;

import exceptions.*;
import model.expression.Expression;
import model.state.ProgramState;
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
        return state;
    }

    @Override
    public Statement deepCopy() {
        return new WhileStatement(expression.deepCopy(),body.deepCopy());
    }

    @Override
    public String toString() {
        return "while(" + expression.toString() + ") { " + body.toString() + " }";
    }
}
