package br.com.calculadora.interest.models.enums;

public enum TimeCategory {

    DAY(1, "Day"),
    MONTH(2, "Month"),
    YEAR(3, "Year");

    private Integer code;
    private String desc;

    TimeCategory(Integer code, String desc) {
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
