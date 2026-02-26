package com.kapil.digitalbank.Banking.App.dtos;

import jakarta.persistence.GeneratedValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PasswordUpdateDto {
    private String uuid;
    private String oldPassword;
    private String newPassword;
}
