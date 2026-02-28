package com.kapil.digitalbank.Banking.App.Controller;

import com.kapil.digitalbank.Banking.App.Entities.AccountType;
import com.kapil.digitalbank.Banking.App.Entities.AppUser;
import com.kapil.digitalbank.Banking.App.Entities.BankAcc;
import com.kapil.digitalbank.Banking.App.Repositories.AccountRepo;
import com.kapil.digitalbank.Banking.App.Repositories.UserRepo;
import com.kapil.digitalbank.Banking.App.Service.AccountService;
import com.kapil.digitalbank.Banking.App.Service.UserService;
import com.kapil.digitalbank.Banking.App.dtos.AccountDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/account")
@AllArgsConstructor
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepo accountRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<?> createAccount(@RequestBody AccountDto accountDto) {

        // Step 1: Check if user exists
        AppUser appUser = userRepo.findByEmail(accountDto.getEmail())
                .orElseThrow(() -> new RuntimeException("User not exists with this id"));

        // Step 2: Check if account already exists
        boolean exists = accountRepo.existsByAppUser_EmailAndAccountType(
                accountDto.getEmail(),
                accountDto.getAccountType()
        );

        if (exists) {
            throw new RuntimeException("Account already exists with this type");
        }
        // Step 3: Create account
        String accNo = accountService.openAccount(appUser, accountDto.getAccountType());
        return ResponseEntity.ok("Account Created Successfully and your account number is " + accNo);
    }

    // Check Balance
    @GetMapping("/checkBalance")
    public ResponseEntity<?> checkBalance(@RequestBody AccountDto accountDto) {

        // checking if bank account number is valid or not
        boolean exists = accountRepo.existsByAccountNumber(accountDto.getAccountNumber());
        if(exists) {
            return ResponseEntity.ok("Your Balance is " + accountService.checkBalance(accountDto.getAccountNumber()));
        } else {
            return ResponseEntity.notFound().build();
        }

    }




}
