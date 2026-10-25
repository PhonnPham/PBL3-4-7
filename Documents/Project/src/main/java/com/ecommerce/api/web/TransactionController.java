package com.ecommerce.api.web;
import com.ecommerce.api.domain.Transaction;
import com.ecommerce.api.repository.TransactionRepository;
import com.ecommerce.api.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/v1/transactions") @RequiredArgsConstructor
public class TransactionController {
    private final TransactionRepository transactions; private final OrderRepository orders;
    @GetMapping("/{id}") public Transaction get(@PathVariable Long id, Authentication auth) {
        Transaction transaction = transactions.findById(id).orElseThrow();
        if (!orders.findById(transaction.getOrderId()).map(o -> o.getCustomerUsername().equals(auth.getName())).orElse(false)) {
            throw new AccessDeniedException("Not your transaction");
        }
        return transaction;
    }
}
