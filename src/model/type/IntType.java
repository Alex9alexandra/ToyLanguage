package model.type;

import model.value.Value;

public class IntType implements Type {

    @Override
    public boolean equals(Object other) {
        return other instanceof IntType;
    }

    @Override
    public String toString() {
        return "int";
    }

    @Override
    public Value getDefaultValue() {
        return new model.value.IntegerValue(0);
    }
}
