package com.kapil.digitalbank.Banking.App.dtos;


import com.kapil.digitalbank.Banking.App.Entities.BankAcc;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private String id;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private String password;

    private String role;

    private List<BankAcc> accounts;

    private PasswordUpdateDto passwordUpdateDto;
}
