package model.expression;

import exceptions.*;
import model.state.Heap;
import model.state.SymbolTable;
import model.type.IntType;
import model.type.Type;
import model.value.BooleanValue;
import model.value.IntegerValue;
import model.value.Value;

public class RelationalExpression implements Expression {
    Expression exp1;
    Expression exp2;
    String operator;
    public RelationalExpression(Expression exp1, Expression exp2, String operator) {
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.operator = operator;
    }
    @Override
    public Value evaluate(SymbolTable<String, Value> symTable, Heap<Value> heap) throws ArithmeticDivBy0Exception, ArithmeticInvalidOpException, ArithmeticSecOpNotIntException, ArithmeticFirstOpNotIntException, LogicSecOpNotBoolException, LogicFirstOpNotBoolException, VariableNotDefinedException {
        Value v1;
        Value v2;
        v1 = exp1.evaluate(symTable,heap);
        if (v1.getType().equals(new IntType())) {
            v2 = exp2.evaluate(symTable,heap);
            if (v2.getType().equals(new IntType())) {
                IntegerValue i1 = (IntegerValue) v1;
                IntegerValue i2 = (IntegerValue) v2;
                int n1, n2;
                n1 = i1.value();
                n2 = i2.value();
                return switch (operator) {
                    case "<" -> new BooleanValue(n1 < n2 );
                    case "<=" -> new BooleanValue(n1 <= n2 );
                    case ">" -> new BooleanValue(n1 > n2 );
                    case ">=" -> new BooleanValue(n1 >= n2 );
                    case "==" -> new BooleanValue(n1 == n2 );
                    case "!=" -> new BooleanValue(n1 != n2 );
                    default -> throw new RelationalInvalidOperatorException("Invalid relational operator");
                };
            } else
                throw new ArithmeticSecOpNotIntException("Second operand is not an integer");
        } else
            throw new ArithmeticFirstOpNotIntException("First operand is not an integer");
    }


    @Override
    public Expression deepCopy() {
        return new RelationalExpression(exp1.deepCopy(), exp2.deepCopy(), operator);
    }

//    @Override
//    public Type typeCheck(SymbolTable<String, Type> typeEnv) throws Exception {
//        Type typ1,typ2;
//        typ1=exp1.typeCheck(typeEnv);
//        typ2=exp2.typeCheck(typeEnv);
//        if(typ1.equals(new IntType()))
//        {
//            if (typ2.equals(new IntType())){
//                return new IntType();
//            }
//            else
//                throw new ArithmeticSecOpNotIntException("Second operand is not an integer");
//        }
//        throw new ArithmeticFirstOpNotIntException("First operand is not an integer");
//    }

    @Override
    public String toString() {
        return "("+exp1.toString() + " " + operator + " " + exp2.toString()+")";
    }
}
