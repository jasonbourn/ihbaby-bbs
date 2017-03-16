package com.ihbaby.emus;

public enum AskDoctorTypeEnum {

    ASK_1_3(1, "1-3月"),

    ASK_4_7(2, "4-7月"),

    ASK_8_10(3, "8-10孕");

    private final int value;

    private final String name;

    AskDoctorTypeEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int value() {
        return value;
    }

    public static AskDoctorTypeEnum valueOf(int value) {
        for (AskDoctorTypeEnum e : AskDoctorTypeEnum.values()) {
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
