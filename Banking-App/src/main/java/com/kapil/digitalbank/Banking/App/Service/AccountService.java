package com.kapil.digitalbank.Banking.App.Service;

import com.kapil.digitalbank.Banking.App.Entities.AccountType;
import com.kapil.digitalbank.Banking.App.Entities.BankAcc;
import com.kapil.digitalbank.Banking.App.Entities.AppUser;
import com.kapil.digitalbank.Banking.App.Repositories.AccountRepo;
import com.kapil.digitalbank.Banking.App.Repositories.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AccountService {

    @Autowired
    private AccountRepo accountRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserService userService;

    //Opening account of user
    public String openAccount(AppUser appUser, AccountType accountType) {
        String account = generateAccNo(appUser, accountType);

        BankAcc bankAcc = new BankAcc();
        bankAcc.setAccountNumber(account);
        bankAcc.setAccountType(accountType);
        bankAcc.setAppUser(appUser);
        bankAcc.setBalance(0.0); // set initial balance 0
        accountRepo.save(bankAcc);
        return account;
    }


    // Account By Id
    public Optional<BankAcc> AccountById(UUID id) {
        Optional<BankAcc> acc = accountRepo.getAccountById(id);
        return acc;
    }


    //Generating unique account number
    public String generateAccNo(AppUser appUser, AccountType accountType) {
        Random random = new Random();
        String accountNumber;

        do {
            long number = 1000000000L +
                    (long)(random.nextDouble() * 9000000000L);
            accountNumber = String.valueOf(number);

        } while(accountRepo.existsByAccountNumber(accountNumber));

        return accountNumber;
    }

    // Checking Balance
    public Double checkBalance(String accountNumber) {
        Optional<BankAcc> acc = accountRepo.findByAccountNumber(accountNumber);
        if(acc.isPresent()) {
            return acc.get().getBalance();
        } else {
            throw new RuntimeException("Account Number does not exist please try again");
        }
    }
}
