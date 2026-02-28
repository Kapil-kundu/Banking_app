package com.kapil.digitalbank.Banking.App.dtos;

import com.kapil.digitalbank.Banking.App.Entities.AccountType;
import com.kapil.digitalbank.Banking.App.Entities.AppUser;
import com.kapil.digitalbank.Banking.App.Entities.Transactions;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {

    private String email;

    private String accountNumber;

    private Double balance;

    private AccountType accountType;

    private AppUser appUser;

    private List<Transactions> transactions;
}
