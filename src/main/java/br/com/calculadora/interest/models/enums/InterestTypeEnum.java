package br.com.calculadora.interest.models.enums;

public enum InterestTypeEnum {

    SIMPLE(1, "Simple Interest"),
    COMPOUND(2, "Compound Interest");

    private Integer code;
    private String desc;

    InterestTypeEnum(Integer code, String desc) {
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
