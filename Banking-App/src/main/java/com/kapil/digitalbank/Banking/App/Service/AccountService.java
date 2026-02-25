package com.kapil.digitalbank.Banking.App.Service;

import com.kapil.digitalbank.Banking.App.Entities.AccountType;
import com.kapil.digitalbank.Banking.App.Entities.BankAcc;
import com.kapil.digitalbank.Banking.App.Entities.User;
import com.kapil.digitalbank.Banking.App.Repositories.AccountRepo;
import com.kapil.digitalbank.Banking.App.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
public class AccountService {

    @Autowired
    private AccountRepo accountRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserService userService;

    //Opening account of user
    public void openAccount(User user, AccountType accountType) {
        String account = generateAccNo();

        BankAcc bankAcc = new BankAcc();
        bankAcc.setAccountNumber(account);
        bankAcc.setAccountType(accountType);
        bankAcc.setUser(user);
    }


    // Account By Id
    public Optional<BankAcc> AccountById(UUID id) {
        Optional<BankAcc> acc = accountRepo.getAccountById(id);
        return acc;
    }


    //Generating unique account number
    private String generateAccNo() {
        Random random = new Random();
        String accountNumber;

        do {
            long number = 1000000000L +
                    (long)(random.nextDouble() * 9000000000L);
            accountNumber = String.valueOf(number);

        } while(accountRepo.existsByAccountNumber(accountNumber));

        return accountNumber;
    }
}
