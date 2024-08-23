package expense.manager.transaction.controller;

import expense.manager.common.dto.transaction.request.TransactionRequest;
import expense.manager.common.dto.transaction.response.TransactionResponse;
import expense.manager.transaction.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<TransactionResponse>> findAll() {
        return ResponseEntity.ok(transactionService.findAll());
    }

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<TransactionResponse> addTransaction(@Valid @RequestBody TransactionRequest transactionRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(transactionService.addTransaction(transactionRequest));
    }

    @PutMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<TransactionResponse> updateTransaction(@PathVariable(value = "id") String transactionId, @Valid @RequestBody TransactionRequest transactionRequest) {
        return ResponseEntity.ok(transactionService.updateTransaction(transactionId, transactionRequest));
    }

}
