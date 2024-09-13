package expense.manager.transaction.controller;

import expense.manager.common.dto.transaction.request.TransactionRequest;
import expense.manager.common.dto.transaction.response.TransactionResponse;
import expense.manager.common.dto.wallet.request.UpdateWalletBalanceRequest;
import expense.manager.transaction.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<TransactionResponse> findById(@PathVariable(value = "id") String transactionId) {
        return ResponseEntity.ok(transactionService.findById(transactionId));
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<TransactionResponse>> findAll(@AuthenticationPrincipal Jwt jwt) {
        log.info("find all transactions called");
        return ResponseEntity.ok(transactionService.findAll(jwt));
    }

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<TransactionResponse> addTransaction(@Valid @RequestBody TransactionRequest transactionRequest, @AuthenticationPrincipal Jwt jwt) {
        log.info("Adding transaction");
        return ResponseEntity.status(HttpStatus.CREATED).body(transactionService.addTransaction(transactionRequest, jwt));
    }

    @PutMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<TransactionResponse> updateWalletBalance(@PathVariable(value = "id") String walletId, @Valid @RequestBody UpdateWalletBalanceRequest updateWalletBalanceRequest) {
        return ResponseEntity.ok(transactionService.updateWalletBalance(walletId, updateWalletBalanceRequest));
    }

}
