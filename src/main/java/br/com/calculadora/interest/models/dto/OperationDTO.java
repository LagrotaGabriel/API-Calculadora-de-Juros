package br.com.calculadora.interest.models.dto;

import br.com.calculadora.interest.models.enums.InterestTypeEnum;
import br.com.calculadora.interest.models.enums.TimeCategory;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class OperationDTO {

    private Long id;
    private LocalDateTime localDateTime;
    private Double applied;
    private Double amount;
    private Double interestRate;
    private Double interest;
    private Integer time;
    private TimeCategory timeCategory;
    private InterestTypeEnum interestTypeEnum;

}
