package model.value;

import model.type.RefType;
import model.type.Type;

public class RefValue implements Value{
    int address;
    Type locationType;

    public RefValue(int address, Type locationType) {
        this.address = address;
        this.locationType = locationType;
    }

    public int getAddress() {
        return address;
    }

    @Override
    public Type getType() {
        return new RefType(locationType);
    }

    @Override
    public Value getValue() {
        return this;
    }

    @Override
    public Value deepCopy() {
        return new RefValue(address, locationType);
    }

    @Override
    public String toString() {
        return "RefValue(" + address + ", " + locationType.toString() + ")";
    }

    @Override
    public boolean equals(Object another) {
        if (this == another) return true;
        if (another == null || getClass() != another.getClass()) return false;
        RefValue that = (RefValue) another;
        return this.address == that.address && this.locationType.equals(that.locationType);
    }
}
