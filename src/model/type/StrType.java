package model.type;

import model.value.Value;

public class StrType implements Type{

    @Override
    public boolean equals(Object another) {
        return another instanceof StrType;
    }

    @Override
    public String toString() {
        return "string";
    }

    @Override
    public Value getDefaultValue() {
        return new model.value.StringValue("");
    }

    @Override
    public Type deepCopy() {
        return new StrType();
    }
}
