package br.com.calculadora.interest.models.entities;

import br.com.calculadora.interest.models.entities.enums.InterestType;
import br.com.calculadora.interest.models.entities.enums.TimeCategory;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity(name = "TB_OPERATION")
@SequenceGenerator(allocationSize = 1, sequenceName = "sq_operation", name="operation")
public class OperationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "operation")
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private Long id;

    @NotNull
    @Column(name = "timeStamp", nullable = false)
    private LocalDateTime localDateTime;

    @NotNull
    @Column(name = "applied", nullable = false)
    private Double applied;

    @NotNull
    @Column(name = "interestRate", nullable = false)
    private Double interestRate;

    @NotNull
    @Column(name = "time", nullable = false)
    private Integer time;

    @Column(name = "interest")
    private Double interest;

    @Column(name = "amount")
    private Double amount;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "timeCategory", nullable = false)
    private TimeCategory timeCategory;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "interestType", nullable = false)
    private InterestType interestType;

}
