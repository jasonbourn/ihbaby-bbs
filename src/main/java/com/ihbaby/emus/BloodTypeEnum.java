package com.ihbaby.emus;

public enum BloodTypeEnum {

    A(1, "A型"),

    B(2, "B型"),

    O(3, "O型"),

    AB(4,"AB型");

    private final int value;

    private final String name;

    BloodTypeEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int value() {
        return value;
    }

    public static BloodTypeEnum valueOf(int value) {
        for (BloodTypeEnum e : BloodTypeEnum.values()) {
            if (e.value() == value) {
                return e;
            }
        }
        return null;
    }

    public String labelOf(int val) {
        if (valueOf(val) != null) {
            return valueOf(val).name;
        }
        return null;
    }

    public String toString() {
        return this.name;
    }
}
