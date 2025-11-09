package model.value;

import model.type.IntType;
import model.type.Type;

public record IntegerValue(int value) implements Value {

    @Override
    public Type getType() {
        return new IntType();
    }

    @Override
    public Value getValue() {
        return this;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        IntegerValue that = (IntegerValue) other;
        return value == that.value;
    }

    @Override
    public Value deepCopy() {
        return new IntegerValue(value);
    }
}
