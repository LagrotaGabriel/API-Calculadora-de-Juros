package br.com.calculadora.interest.models.enums;

public enum FetchedParameter {

    INTEREST(1, "Interest"),
    APPLIED(2, "Applied"),
    RATE(3, "Interest rate"),
    TIME(4, "Time"),
    AMOUNT(5, "Amount");

    Integer code;
    String desc;

    FetchedParameter(Integer code, String desc) {
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
