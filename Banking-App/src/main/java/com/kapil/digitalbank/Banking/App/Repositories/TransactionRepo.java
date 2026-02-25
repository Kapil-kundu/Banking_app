package com.kapil.digitalbank.Banking.App.Repositories;

import jakarta.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import com.kapil.digitalbank.Banking.App.Entities.Transactions;

import java.util.UUID;

public interface TransactionRepo extends JpaRepository<Transactions, UUID> {
}
