package com.kapil.digitalbank.Banking.App.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChangePhoneDto {
    private String email;
    private String password;
    private String oldPhone;
    private String newPhone;
}
