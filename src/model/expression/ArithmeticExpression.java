package model.expression;

import exceptions.ArithmeticDivBy0Exception;
import exceptions.ArithmeticFirstOpNotIntException;
import exceptions.ArithmeticInvalidOpException;
import exceptions.ArithmeticSecOpNotIntException;
import model.state.SymbolTable;
import model.type.IntType;
import model.value.IntegerValue;
import model.value.Value;

public class ArithmeticExpression implements Expression {
    Expression e1;
    Expression e2;
    char operator;

    public ArithmeticExpression(Expression e1, Expression e2, char operator) {
        this.e1 = e1;
        this.e2 = e2;
        this.operator = operator;
    }

    @Override
    public Value evaluate(SymbolTable<String, Value> symTable) throws ArithmeticDivBy0Exception,ArithmeticInvalidOpException,ArithmeticSecOpNotIntException,ArithmeticFirstOpNotIntException {
        Value v1;
        Value v2;
        v1 = e1.evaluate(symTable);
        if (v1.getType().equals(new IntType())) {
            v2 = e2.evaluate(symTable);
            if (v2.getType().equals(new IntType())) {
                IntegerValue i1 = (IntegerValue) v1;
                IntegerValue i2 = (IntegerValue) v2;
                int n1, n2;
                n1 = i1.value();
                n2 = i2.value();
                return switch (operator) {
                    case '+' -> new model.value.IntegerValue(n1 + n2);
                    case '-' -> new model.value.IntegerValue(n1 - n2);
                    case '*' -> new model.value.IntegerValue(n1 * n2);
                    case '/' -> {
                        if (n2 == 0) throw new ArithmeticDivBy0Exception("Division by zero");
                        yield new model.value.IntegerValue(n1 / n2);
                    }
                    default -> throw new ArithmeticInvalidOpException("Invalid operator");
                };
            } else
                throw new ArithmeticSecOpNotIntException("Second operand is not an integer");
        } else
            throw new ArithmeticFirstOpNotIntException("First operand is not an integer");
    }

    @Override
    public String toString() {
        return e1.toString() + " " + operator + " " + e2.toString();
    }
}

