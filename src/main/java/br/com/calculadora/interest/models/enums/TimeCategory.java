package br.com.calculadora.interest.models.enums;

public enum TimeCategory {

    DAY(1, "Day", 1, 0.033),
    MONTH(2, "Month", 30, 1.0),
    BIMESTER(3, "Bimester", 60, 2.0),
    TRIMESTER(4, "Trimester", 90, 3.0),
    SEMESTER(5, "Semester", 180, 6.0),
    YEAR(6, "Year", 360, 12.0);

    private Integer code;
    private String desc;
    private Integer days;
    private Double months;

    TimeCategory(Integer code, String desc, Integer days, Double months) {
        this.code = code;
        this.desc = desc;
        this.days = days;
        this.months = months;
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

    public Double getMonths() {
        return months;
    }
}
