package model.value;

import model.type.IntType;
import model.type.StrType;
import model.type.Type;

public record StringValue(String value) implements Value {

    @Override
    public Type getType() {
        return new StrType();
    }

    @Override
    public Value getValue() {
        return this;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        StringValue that = (StringValue) other;
        return value.equals(that.value);
    }

    @Override
    public Value deepCopy() {
        return new StringValue(value);
    }

    public String getInnerValue() {
        return value;
    }
}