package expense.manager.transaction.service.impl;

import expense.manager.common.dto.transaction.request.TransactionRequest;
import expense.manager.common.dto.transaction.response.TransactionResponse;
import expense.manager.common.dto.wallet.request.UpdateWalletBalanceRequest;
import expense.manager.common.dto.wallet.response.WalletResponse;
import expense.manager.exception.custom.RecordNotFoundException;
import expense.manager.transaction.client.WalletClient;
import expense.manager.transaction.entity.TransactionEntity;
import expense.manager.transaction.repository.TransactionRepository;
import expense.manager.transaction.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final WalletClient walletClient;

    @Override
    public TransactionResponse findById(String transactionId) {
        TransactionEntity savedEntity = transactionRepository.findById(transactionId).orElseThrow();
        return new ModelMapper().map(savedEntity, TransactionResponse.class);
    }

    @Override
    public List<TransactionResponse> findAll(Jwt jwt) {
        String userId = jwt.getSubject();
        List<TransactionEntity> transactionEntities = transactionRepository.findAllByUserId(userId);
        return new ModelMapper().map(transactionEntities, new TypeToken<List<TransactionResponse>>() {
        }.getType());
    }

    @Override
    public TransactionResponse addTransaction(TransactionRequest transactionRequest, Jwt jwt) {
        ResponseEntity<WalletResponse> walletResponseResponseEntity = walletClient.findById(transactionRequest.getWalletId());
        if (walletResponseResponseEntity.getStatusCode().is2xxSuccessful()) {
            String userId = jwt.getSubject();
            TransactionEntity entityToSave = new ModelMapper().map(transactionRequest, TransactionEntity.class);
            entityToSave.setUserId(userId);
            entityToSave.setId(null);
            transactionRepository.save(entityToSave);
            return new ModelMapper().map(entityToSave, TransactionResponse.class);
        } else
            throw new RecordNotFoundException();

    }

    @Override
    public TransactionResponse updateWalletBalance(String walletId, UpdateWalletBalanceRequest updateWalletBalanceRequest) {
        return null;
    }

}
