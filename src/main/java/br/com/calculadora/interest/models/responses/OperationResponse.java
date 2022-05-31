package br.com.calculadora.interest.models.responses;

import br.com.calculadora.interest.models.entities.enums.InterestType;
import br.com.calculadora.interest.models.entities.enums.TimeCategory;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class OperationResponse {

    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @JsonProperty(value = "timestamp", access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime localDateTime;

    @JsonProperty(value = "capital", access = JsonProperty.Access.READ_ONLY)
    private Double applied;

    @JsonProperty(value = "taxa", access = JsonProperty.Access.READ_ONLY)
    private Double interestRate;

    @JsonProperty(value = "montante", access = JsonProperty.Access.READ_ONLY)
    private Double amount;

    @JsonProperty(value = "juros", access = JsonProperty.Access.READ_ONLY)
    private Double interest;

    @JsonProperty(value = "tempo", access = JsonProperty.Access.READ_ONLY)
    private Integer time;

    @JsonProperty(value = "periodicidade", access = JsonProperty.Access.READ_ONLY)
    private TimeCategory timeCategory;

    @JsonProperty(value = "tipo", access = JsonProperty.Access.READ_ONLY)
    private InterestType interestType;

}
