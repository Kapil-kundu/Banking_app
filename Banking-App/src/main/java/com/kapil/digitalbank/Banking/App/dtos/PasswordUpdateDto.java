package com.kapil.digitalbank.Banking.App.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PasswordUpdateDto {
    private String oldPassword;
    private String newPassword;
    private String email;

}
