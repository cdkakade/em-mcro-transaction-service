package expense.manager.transaction.service.impl;

import expense.manager.common.dto.transaction.request.TransactionRequest;
import expense.manager.common.dto.transaction.response.TransactionResponse;
import expense.manager.transaction.entity.TransactionEntity;
import expense.manager.transaction.repository.TransactionRepository;
import expense.manager.transaction.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    @Override
    public TransactionResponse findById(String transactionId) {
        TransactionEntity savedEntity = transactionRepository.findById(transactionId).orElseThrow();
        return new ModelMapper().map(savedEntity, TransactionResponse.class);
    }

    @Override
    public List<TransactionResponse> findAll() {
        List<TransactionEntity> transactionEntities = transactionRepository.findAll();
        return new ModelMapper().map(transactionEntities, new TypeToken<List<TransactionResponse>>() {
        }.getType());
    }

    @Override
    public TransactionResponse addTransaction(TransactionRequest transactionRequest) {
        TransactionEntity entityToSave = new ModelMapper().map(transactionRequest, TransactionEntity.class);
        transactionRepository.save(entityToSave);
        return new ModelMapper().map(entityToSave, TransactionResponse.class);
    }

    @Override
    public TransactionResponse updateTransaction(String transactionId, TransactionRequest transactionRequest) {
        return null;
    }

}
