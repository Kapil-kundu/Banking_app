package com.kapil.digitalbank.Banking.App.Service;

import com.kapil.digitalbank.Banking.App.Entities.AppUser;
import com.kapil.digitalbank.Banking.App.Repositories.UserRepo;
import com.kapil.digitalbank.Banking.App.dtos.PasswordUpdateDto;
import com.kapil.digitalbank.Banking.App.dtos.UserDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService {
    @Autowired
    private final UserRepo userRepo;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    private final ModelMapper modelmapper;



    public UserDto createUser(UserDto userDto) {
        userDto.setRole("USER");
        AppUser appUser = modelmapper.map(userDto, AppUser.class);
        String encoded = passwordEncoder.encode(appUser.getPassword());
        appUser.setPassword(encoded);
        System.out.println(encoded);
        AppUser savedAppUser = userRepo.save(appUser);
        return modelmapper.map(savedAppUser, UserDto.class);
    }

    public void updatePassword(PasswordUpdateDto passwordUpdateDto) {
       String uuid = passwordUpdateDto.getUuid();
       UUID id = UUID.fromString(uuid);
       AppUser appUser = userRepo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
       System.out.println(appUser);
       String existingPassword = appUser.getPassword();
       String oldPassword = passwordUpdateDto.getOldPassword();
       if(!passwordEncoder.matches(existingPassword, oldPassword)) {
          throw new BadCredentialsException("Old password is incorrect");
       } else {
           appUser.setPassword(passwordEncoder.encode(passwordUpdateDto.getNewPassword()));
           userRepo.save(appUser);
       }
    }

}
