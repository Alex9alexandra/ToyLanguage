package model.expression;

import model.state.SymbolTable;
import model.value.Value;

public class ValueExpression implements Expression {
    Value e;

    public ValueExpression(Value e) {
        this.e = e;
    }

    @Override
    public Value evaluate(SymbolTable<String, Value> symTable) {
        return e;
    }

    @Override
    public String toString() {
        return e.toString();
    }
}
