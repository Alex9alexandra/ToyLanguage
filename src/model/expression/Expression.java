package model.expression;

import exceptions.*;
import model.state.Heap;
import model.state.SymbolTable;
import model.type.Type;
import model.value.Value;

public interface Expression {
    Value evaluate(SymbolTable<String, Value> symTable, Heap<Value> heap) throws ArithmeticDivBy0Exception, ArithmeticInvalidOpException, ArithmeticSecOpNotIntException, ArithmeticFirstOpNotIntException, LogicSecOpNotBoolException,LogicFirstOpNotBoolException,VariableNotDefinedException;

    String toString();
    Expression deepCopy();

    //Type typeCheck(SymbolTable<String,Type> typeEnv) throws Exception;
}
