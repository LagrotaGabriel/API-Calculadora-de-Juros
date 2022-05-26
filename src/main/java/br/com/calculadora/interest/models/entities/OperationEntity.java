package br.com.calculadora.interest.models.entities;

import br.com.calculadora.interest.models.enums.FetchedParameter;
import br.com.calculadora.interest.models.enums.InterestType;
import br.com.calculadora.interest.models.enums.TimeCategory;
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
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "timeStamp", nullable = true, unique = false, updatable = true)
    private LocalDateTime localDateTime;

    @Column(name = "applied", nullable = true, unique = false, updatable = true)
    private Double applied;

    @Column(name = "amount", nullable = true, unique = false, updatable = true)
    private Double amount;

    @Column(name = "interestRate", nullable = true, unique = false, updatable = true)
    private Double interestRate;

    @Column(name = "interest", nullable = true, unique = false, updatable = true)
    private Double interest;

    @Column(name = "time", nullable = true, unique = false, updatable = true)
    private Integer time;

    @Enumerated(EnumType.STRING)
    @Column(name = "fetchedParameter", nullable = false, unique = false, updatable = true)
    private FetchedParameter fetchedParameter;

    @Enumerated(EnumType.STRING)
    @Column(name = "timeCategory", nullable = true, unique = false, updatable = true)
    private TimeCategory timeCategory;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "interestType", nullable = false, unique = false, updatable = true)
    private InterestType interestType;

}
