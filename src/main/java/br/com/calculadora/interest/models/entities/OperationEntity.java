package br.com.calculadora.interest.models.entities;

import br.com.calculadora.interest.models.entities.enums.InterestType;
import br.com.calculadora.interest.models.entities.enums.TimeCategory;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter @Setter
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
    @Column(name = "timeStamp", nullable = false, unique = false, updatable = true)
    private LocalDateTime localDateTime;

    @NotNull
    @Column(name = "applied", nullable = false, unique = false, updatable = true)
    private Double applied;

    @NotNull
    @Column(name = "interestRate", nullable = false, unique = false, updatable = true)
    private Double interestRate;

    @NotNull
    @Column(name = "time", nullable = false, unique = false, updatable = true)
    private Integer time;

    @Column(name = "interest", nullable = true, unique = false, updatable = true)
    private Double interest;

    @Column(name = "amount", nullable = true, unique = false, updatable = true)
    private Double amount;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "timeCategory", nullable = false, unique = false, updatable = true)
    private TimeCategory timeCategory;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "interestType", nullable = false, unique = false, updatable = true)
    private InterestType interestType;

}
