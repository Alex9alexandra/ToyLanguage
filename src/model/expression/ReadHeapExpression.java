package model.expression;

import exceptions.*;
import model.state.Heap;
import model.state.SymbolTable;
import model.value.RefValue;
import model.value.Value;

public class ReadHeapExpression implements Expression {
    Expression expression;

    public ReadHeapExpression(Expression expression) {
        this.expression = expression;
    }

    @Override
    public Value evaluate(SymbolTable<String, Value> symTable, Heap<Value> heap) throws ArithmeticDivBy0Exception, ArithmeticInvalidOpException, ArithmeticSecOpNotIntException, ArithmeticFirstOpNotIntException, LogicSecOpNotBoolException, LogicFirstOpNotBoolException, VariableNotDefinedException {
        Value evaluated=expression.evaluate(symTable,heap);

        if(! (evaluated instanceof RefValue refValue)) {
            throw new NotRefValueEvaluatedException("The expression is not of RefType");
        }
        int address= refValue.getAddress();
        if(!heap.containsAddress(address)) {
            throw new AddressNotInHeapException("The address is not in the heap");
        }
        return heap.read(address);
    }

    @Override
    public Expression deepCopy() {
        return new ReadHeapExpression(expression.deepCopy());
    }

    @Override
    public String toString() {
        return "rH(" + expression.toString() + ")";
    }
}
