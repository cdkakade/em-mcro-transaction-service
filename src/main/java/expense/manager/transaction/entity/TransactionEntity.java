package expense.manager.transaction.entity;

import expense.manager.common.dto.transaction.TransactionType;
import expense.manager.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity(name = "transaction")
@Table(name = "transaction")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class TransactionEntity extends BaseEntity {

    @Column
    private BigDecimal amount;

    @Column
    @Enumerated(EnumType.STRING)
    private TransactionType type;
}
