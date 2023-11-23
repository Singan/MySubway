package com.traffic.common.enums;


public enum WeekType {
    MONDAY("monday","월"),
    TUESDAY("tuesday","화"),
    WEDNESDAY("wednesday","수"),
    THURSDAY("thursday","목"),
    FRIDAY("friday","금"),
    SATURDAY("saturday","토"),
    SUNDAY("sunday","일");
    private final String label;
    private final String value;
    WeekType(String label, String value) {
        this.label = label;
        this.value = value;
    }
    public String getLabel() {
        return this.label;
    }
    public String getValue() {
        return value;
    }

}
