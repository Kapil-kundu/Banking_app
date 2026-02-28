package com.kapil.digitalbank.Banking.App.Repositories;

import com.kapil.digitalbank.Banking.App.Entities.AccountType;
import com.kapil.digitalbank.Banking.App.Entities.BankAcc;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AccountRepo extends JpaRepository<BankAcc, UUID> {

    boolean existsByAccountNumber(String accountNumber);

    boolean existsByAppUser_EmailAndAccountType(String email, AccountType type);
    Optional<BankAcc> getAccountById(UUID id);
}
