package model.expression;

import exceptions.*;
import model.state.Heap;
import model.state.SymbolTable;
import model.value.Value;

public interface Expression {
    Value evaluate(SymbolTable<String, Value> symTable, Heap heap) throws ArithmeticDivBy0Exception, ArithmeticInvalidOpException, ArithmeticSecOpNotIntException, ArithmeticFirstOpNotIntException, LogicSecOpNotBoolException,LogicFirstOpNotBoolException,VariableNotDefinedException;

    String toString();
    Expression deepCopy();
}
