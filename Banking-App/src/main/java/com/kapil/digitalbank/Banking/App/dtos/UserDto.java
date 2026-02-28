package com.kapil.digitalbank.Banking.App.dtos;


import com.kapil.digitalbank.Banking.App.Entities.BankAcc;
import com.kapil.digitalbank.Banking.App.Entities.Transactions;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private UUID id;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private String password;

    private String role;

    private List<BankAcc> accounts;

    private PasswordUpdateDto userUpdationDto;


}
