package model.state;


import model.value.Value;
import model.type.Type;

import java.util.HashMap;
import java.util.Map;

public class MapSymbolTable<S, V> implements SymbolTable<S, V> {

    private final Map<S, V> map = new HashMap<>();

    @Override
    public boolean isDefined(S variableName) {
        return map.containsKey(variableName);
    }

    @Override
    public Type getType(S variableName) {
        return ((Value) map.get(variableName)).getType();
    }

    @Override
    public void declareVariable(S variableName, Type type) {
        map.put(variableName, (V) type.getDefaultValue());
    }

    @Override
    public void update(S variableName, V value) {
        map.put(variableName, value);
    }

    @Override
    public Value getValue(S variableName) {
        return (Value) map.get(variableName);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("SymTable:\n");
        for (S key : map.keySet()) {
            result.append(key.toString())
                    .append(" -> ")
                    .append(map.get(key).toString())
                    .append("\n");
        }
        return result.toString();
    }

    @Override
    public Map<S, V> getContent() {
        return map;
    }
}
