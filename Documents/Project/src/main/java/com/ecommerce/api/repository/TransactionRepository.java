package com.ecommerce.api.repository;
import com.ecommerce.api.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
public interface TransactionRepository extends JpaRepository<Transaction, Long> {}
