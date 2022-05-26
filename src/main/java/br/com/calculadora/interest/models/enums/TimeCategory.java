package br.com.calculadora.interest.models.enums;

public enum TimeCategory {

    DAY(1, "Day", 1),
    MONTH(2, "Month", 30),
    BIMESTER(3, "Bimester", 60),
    TRIMESTER(4, "Trimester", 90),
    SEMESTER(5, "Semester", 180),
    YEAR(6, "Year", 360);

    private Integer code;
    private String desc;
    private Integer days;

    TimeCategory(Integer code, String desc, Integer days) {
        this.code = code;
        this.desc = desc;
        this.days = days;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public Integer getDays() {
        return days;
    }
}
