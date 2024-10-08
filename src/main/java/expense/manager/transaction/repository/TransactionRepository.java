package expense.manager.transaction.repository;

import expense.manager.transaction.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, String> {
    List<TransactionEntity> findAllByUserId(String userId);
}
