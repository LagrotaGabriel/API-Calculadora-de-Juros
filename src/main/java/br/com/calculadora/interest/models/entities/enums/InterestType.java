package br.com.calculadora.interest.models.entities.enums;

public enum InterestType {

    SIMPLE(1, "Simple Interest"),
    COMPOUND(2, "Compound Interest");

    private Integer code;
    private String desc;

    InterestType(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
