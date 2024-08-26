package expense.manager.transaction.service;

import expense.manager.common.dto.transaction.request.TransactionRequest;
import expense.manager.common.dto.transaction.response.TransactionResponse;

import java.util.List;

public interface TransactionService {

    List<TransactionResponse> findAll();

    TransactionResponse addTransaction(TransactionRequest transactionRequest);

    TransactionResponse updateTransaction(String transactionId, TransactionRequest transactionRequest);

    TransactionResponse findById(String transactionId);
}
