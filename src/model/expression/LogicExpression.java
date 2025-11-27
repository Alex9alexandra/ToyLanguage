package model.expression;

import exceptions.*;
import model.state.Heap;
import model.state.SymbolTable;
import model.type.BoolType;
import model.value.BooleanValue;
import model.value.Value;

public class LogicExpression implements Expression {
    Expression e1;
    Expression e2;
    int operator; // 1=and, 2=or

    public LogicExpression(Expression e1, Expression e2, int operator) {
        this.e1 = e1;
        this.e2 = e2;
        this.operator = operator;
    }

    @Override
    public Value evaluate(SymbolTable<String, Value> symTable, Heap<Value> heap) throws  LogicSecOpNotBoolException,LogicFirstOpNotBoolException {
        Value v1;
        Value v2;
        v1 = e1.evaluate(symTable,heap);
        if (v1.getType().equals(new BoolType())) {
            v2 = e2.evaluate(symTable,heap);
            if (v2.getType().equals(new BoolType())) {
                BooleanValue i1 = (BooleanValue) v1;
                BooleanValue i2 = (BooleanValue) v2;
                boolean n1;
                boolean n2;
                n1 = i1.value();
                n2 = i2.value();
                return switch (operator) {
                    case 1 -> new model.value.BooleanValue(n1 && n2);
                    case 2 -> new model.value.BooleanValue(n1 || n2);
                    default -> null;
                };
            } else
                throw new LogicSecOpNotBoolException("Second operand is not a boolean");
        } else
            throw new LogicFirstOpNotBoolException("First operand is not a boolean");
    }

    @Override
    public String toString() {
        String op;
        switch (operator) {
            case 1 -> op = "and";
            case 2 -> op = "or";
            default -> op = "unknown";
        }
        return "("+ e1.toString() + " " + op + " " + e2.toString()+")";
    }

    @Override
    public Expression deepCopy() {
        return new LogicExpression(e1.deepCopy(), e2.deepCopy(), operator);
    }

}
