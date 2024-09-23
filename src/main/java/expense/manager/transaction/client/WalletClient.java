package expense.manager.transaction.client;

import expense.manager.common.dto.wallet.request.UpdateWalletBalanceRequest;
import expense.manager.common.dto.wallet.response.WalletResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "wallet-service", dismiss404 = true)
public interface WalletClient {

    @PutMapping(value = "wallets/{id}", consumes = "application/json")
    ResponseEntity<WalletResponse> updateWalletBalance(@PathVariable("id") String id, @RequestBody UpdateWalletBalanceRequest updateWalletBalanceRequest);

    @GetMapping(value = "wallets/{id}", consumes = "application/json")
    ResponseEntity<WalletResponse> findById(@PathVariable("id") String id);
}
