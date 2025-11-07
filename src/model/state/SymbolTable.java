package model.state;

import model.type.Type;
import model.value.Value;

public interface SymbolTable<S, V> {
    boolean isDefined(S variableName);

    Type getType(S variableName);

    void declareVariable(S variableName, Type type);

    void update(S variableName, V value);

    Value getValue(S variableName);

    String toString();

}
