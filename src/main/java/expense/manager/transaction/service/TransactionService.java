package expense.manager.transaction.service;

import expense.manager.common.dto.transaction.request.TransactionRequest;
import expense.manager.common.dto.transaction.response.TransactionResponse;
import expense.manager.common.dto.wallet.request.UpdateWalletBalanceRequest;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.List;

public interface TransactionService {

    List<TransactionResponse> findAll(Jwt jwt);

    TransactionResponse addTransaction(TransactionRequest transactionRequest, Jwt jwt);

    TransactionResponse updateWalletBalance(String walletId, UpdateWalletBalanceRequest updateWalletBalanceRequest);

    TransactionResponse findById(String transactionId);
}
