package br.com.calculadora.interest.models.entities.enums;

import br.com.calculadora.interest.models.entities.OperationEntity;
import br.com.calculadora.interest.models.mocks.OperationEntityBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TimeCategoryTest {

    @Test
    public void shouldTestTimeCategoryWithDayEnum(){

        OperationEntity operationEntity = OperationEntityBuilder.builder()
                .withDayTimeCategory()
                .build();

        String timeCategoryDayEnum = operationEntity.getTimeCategory().getCode() + ", " +
                operationEntity.getTimeCategory().getDesc() + ", " + operationEntity.getTimeCategory().getDays() + ", "
                + operationEntity.getTimeCategory().getMonths();

        Assertions.assertEquals("1, Day, 1, 0.033", timeCategoryDayEnum);

    }

    @Test
    public void shouldTestTimeCategoryWithMonthEnum(){

        OperationEntity operationEntity = OperationEntityBuilder.builder()
                .withMonthTimeCategory()
                .build();

        String timeCategoryMonthEnum = operationEntity.getTimeCategory().getCode() + ", " +
                operationEntity.getTimeCategory().getDesc() + ", " + operationEntity.getTimeCategory().getDays() + ", "
                + operationEntity.getTimeCategory().getMonths();

        Assertions.assertEquals("2, Month, 30, 1.0", timeCategoryMonthEnum);

    }

    @Test
    public void shouldTestTimeCategoryWithBimesterEnum(){

        OperationEntity operationEntity = OperationEntityBuilder.builder()
                .withBimesterTimeCategory()
                .build();

        String timeCategoryBimesterEnum = operationEntity.getTimeCategory().getCode() + ", " +
                operationEntity.getTimeCategory().getDesc() + ", " + operationEntity.getTimeCategory().getDays() + ", "
                + operationEntity.getTimeCategory().getMonths();

        Assertions.assertEquals("3, Bimester, 60, 2.0", timeCategoryBimesterEnum);

    }

    @Test
    public void shouldTestTimeCategoryWithTrimesterEnum(){

        OperationEntity operationEntity = OperationEntityBuilder.builder()
                .withTrimesterTimeCategory()
                .build();

        String timeCategoryTrimesterEnum = operationEntity.getTimeCategory().getCode() + ", " +
                operationEntity.getTimeCategory().getDesc() + ", " + operationEntity.getTimeCategory().getDays() + ", "
                + operationEntity.getTimeCategory().getMonths();

        Assertions.assertEquals("4, Trimester, 90, 3.0", timeCategoryTrimesterEnum);

    }

    @Test
    public void shouldTestTimeCategoryWithSemesterEnum(){

        OperationEntity operationEntity = OperationEntityBuilder.builder()
                .withSemesterTimeCategory()
                .build();

        String timeCategorySemesterEnum = operationEntity.getTimeCategory().getCode() + ", " +
                operationEntity.getTimeCategory().getDesc() + ", " + operationEntity.getTimeCategory().getDays() + ", "
                + operationEntity.getTimeCategory().getMonths();

        Assertions.assertEquals("5, Semester, 180, 6.0", timeCategorySemesterEnum);

    }

    @Test
    public void shouldTestTimeCategoryWithYearEnum(){

        OperationEntity operationEntity = OperationEntityBuilder.builder()
                .withYearTimeCategory()
                .build();

        String timeCategoryYearEnum = operationEntity.getTimeCategory().getCode() + ", " +
                operationEntity.getTimeCategory().getDesc() + ", " + operationEntity.getTimeCategory().getDays() + ", "
                + operationEntity.getTimeCategory().getMonths();

        Assertions.assertEquals("6, Year, 360, 12.0", timeCategoryYearEnum);

    }

}
