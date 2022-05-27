package br.com.calculadora.interest.models.dto;

import br.com.calculadora.interest.models.enums.InterestType;
import br.com.calculadora.interest.models.enums.TimeCategory;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

public class OperationDTO {

    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    private Long id;

    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    private LocalDateTime localDateTime;

    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    private Double applied;

    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    private Double interestRate;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Double amount;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Double interest;

    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    private Integer time;

    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    private TimeCategory timeCategory;

    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    private InterestType interestType;

}
