package model.expression;

import exceptions.*;
import model.state.SymbolTable;
import model.value.Value;

public interface Expression {
    Value evaluate(SymbolTable<String, Value> symTable) throws ArithmeticDivBy0Exception, ArithmeticInvalidOpException, ArithmeticSecOpNotIntException, ArithmeticFirstOpNotIntException, LogicSecOpNotBoolException,LogicFirstOpNotBoolException,VariableNotDefinedException;

    String toString();
}
