package br.com.calculadora.interest.models.entities;

import br.com.calculadora.interest.models.enums.InterestTypeEnum;
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
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "operation")
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @NotNull
    @Column(name = "timeStamp", nullable = false, unique = false, updatable = true)
    private LocalDateTime localDateTime;

    @NotNull
    @Column(name = "applied", nullable = true, unique = false, updatable = true)
    private Double applied;

    @NotNull
    @Column(name = "amount", nullable = true, unique = false, updatable = true)
    private Double amount;

    @NotNull
    @Column(name = "interestRate", nullable = true, unique = false, updatable = true)
    private Double interestRate;

    @NotNull
    @Column(name = "interest", nullable = true, unique = false, updatable = true)
    private Double interest;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "interestType", nullable = false, unique = false, updatable = true)
    private InterestTypeEnum interestTypeEnum;

}
