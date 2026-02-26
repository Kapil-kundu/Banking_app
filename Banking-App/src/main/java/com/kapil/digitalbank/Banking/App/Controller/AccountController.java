package com.kapil.digitalbank.Banking.App.Controller;

import com.kapil.digitalbank.Banking.App.Entities.AccountType;
import com.kapil.digitalbank.Banking.App.Entities.AppUser;
import com.kapil.digitalbank.Banking.App.Repositories.AccountRepo;
import com.kapil.digitalbank.Banking.App.Repositories.UserRepo;
import com.kapil.digitalbank.Banking.App.Service.AccountService;
import com.kapil.digitalbank.Banking.App.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/account")
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

    @PostMapping("/create-account/{uuid}")
    public ResponseEntity<?> createAccount(@PathVariable String uuid, @RequestParam AccountType accountType) {

        UUID id = UUID.fromString(uuid);
        // checking if user exist or not
        AppUser appUser = userRepo.findById(id).orElseThrow(() -> new RuntimeException("User not exists with this id"));

        // cheking if account already exists with given account type
        boolean exists = accountRepo.existsByAppUser_IdAndAccountType(id, accountType);

        if(exists) {
            throw new RuntimeException("Account already exists with this type");
        }

        //Now Creating a new Account
        accountService.openAccount(appUser, accountType);
        return ResponseEntity.ok("Account Created Successfully");
    }
}
