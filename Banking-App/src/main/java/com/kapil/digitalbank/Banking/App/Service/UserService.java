package com.kapil.digitalbank.Banking.App.Service;

import com.kapil.digitalbank.Banking.App.Entities.User;
import com.kapil.digitalbank.Banking.App.Repositories.UserRepo;
import com.kapil.digitalbank.Banking.App.dtos.PasswordUpdateDto;
import com.kapil.digitalbank.Banking.App.dtos.UserDto;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
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
        User user = modelmapper.map(userDto, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepo.save(user);
        return modelmapper.map(savedUser, UserDto.class);
    }

    public void updatePassword(String uuid, PasswordUpdateDto passwordUpdateDto) {
       UUID id = UUID.fromString(uuid);
        User user = userRepo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

       if(!passwordEncoder.matches(passwordUpdateDto.getOldPassword(), user.getPassword())) {
          throw new RuntimeException("Old password is incorrect");
       } else {
           user.setPassword(passwordEncoder.encode(passwordUpdateDto.getNewPassword()));
       }
    }

}
