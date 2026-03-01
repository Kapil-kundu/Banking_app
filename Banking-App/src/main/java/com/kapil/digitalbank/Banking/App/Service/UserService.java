package com.kapil.digitalbank.Banking.App.Service;

import com.kapil.digitalbank.Banking.App.Entities.AppUser;
import com.kapil.digitalbank.Banking.App.Repositories.UserRepo;
import com.kapil.digitalbank.Banking.App.dtos.ChangeEmailDto;
import com.kapil.digitalbank.Banking.App.dtos.ChangePhoneDto;
import com.kapil.digitalbank.Banking.App.dtos.PasswordUpdateDto;
import com.kapil.digitalbank.Banking.App.dtos.UserDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public boolean updatePassword(PasswordUpdateDto passwordUpdate) {
       AppUser appUser = userRepo.findByEmail(passwordUpdate.getEmail()).orElseThrow(() -> new RuntimeException("User not found"));
       if(!passwordEncoder.matches(passwordUpdate.getOldPassword(), appUser.getPassword())) {
          return false;
       } else {
           appUser.setPassword(passwordEncoder.encode(passwordUpdate.getNewPassword()));
           userRepo.save(appUser);
           return true;
       }
    }

    public String changeEmail(ChangeEmailDto changeEmailDto) {
        boolean exist = userRepo.existsByEmail(changeEmailDto.getOldEmail());
        if(exist) {
            boolean newExist = userRepo.existsByEmail(changeEmailDto.getNewEmail());
            AppUser user = userRepo.findByEmail(changeEmailDto.getOldEmail())
                    .orElseThrow(() -> new RuntimeException("0Given Email does not exist"));
            if(newExist) {
                return "0New email already exists";
            } else {
                if(passwordEncoder.matches(changeEmailDto.getPassword() ,user.getPassword())){
                    user.setEmail(changeEmailDto.getNewEmail());
                    userRepo.save(user);
                    return "1Email changed Successfully";
                } else {
                    return "0Email or password is incorrect";
                }
            }
        } else {
            return "0Given email did not exist";
        }
    }

    public String changePhone(ChangePhoneDto changePhoneDto) {
        // check if user exist or not
        boolean exist = userRepo.existsByEmail(changePhoneDto.getEmail());
        if(exist) {
            AppUser user = userRepo.findByEmail(changePhoneDto.getEmail()).orElseThrow(() -> new RuntimeException("0 Email does not exist"));
            if(passwordEncoder.matches(changePhoneDto.getPassword(), user.getPassword())) {
                if(changePhoneDto.getNewPhone().equals(changePhoneDto.getOldPhone())) {
                    return "0New Number must be different from your old number";
                } else {
                    user.setPhone(changePhoneDto.getNewPhone());
                    userRepo.save(user);
                    return "1Phone Changed Successfully";
                }
            } else {
                return "0Email or password is incorrect";
            }
        } else{
            return "0Email does not exist";
        }
    }





}
