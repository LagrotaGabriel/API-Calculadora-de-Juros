package br.com.calculadora.interest.resources.exceptions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class StandartErrorTest {

    @Test
    @DisplayName("Should test standart error setters")
    public void shouldTestStandartErrorSetters(){

        StandartError standartError = new StandartError();

        standartError.setTimeStamp(LocalDateTime.now());
        standartError.setStatus(400);
        standartError.setError("Falha de null pointer");
        standartError.setPath("/test");

        Assertions.assertEquals("StandartError(timeStamp=" + standartError.getTimeStamp() + ", status=400, " +
                "error=Falha de null pointer, path=/test)", standartError.toString());

    }

    @Test
    @DisplayName("Should test standart error all args constructor")
    public void shouldTestStandartErrorAllArgsConstructor(){

        StandartError standartError = new StandartError(
                LocalDateTime.now(),
                400,
                "Falha de null pointer",
                "/test");

        Assertions.assertEquals("StandartError(timeStamp=" + standartError.getTimeStamp() + ", status=400, " +
                "error=Falha de null pointer, path=/test)", standartError.toString());

    }

}
