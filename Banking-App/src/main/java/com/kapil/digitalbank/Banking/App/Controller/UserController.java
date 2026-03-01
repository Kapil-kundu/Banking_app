package com.kapil.digitalbank.Banking.App.Controller;


import com.kapil.digitalbank.Banking.App.Entities.AppUser;
import com.kapil.digitalbank.Banking.App.Repositories.UserRepo;
import com.kapil.digitalbank.Banking.App.Service.AccountService;
import com.kapil.digitalbank.Banking.App.Service.UserService;
import com.kapil.digitalbank.Banking.App.dtos.ChangeEmailDto;
import com.kapil.digitalbank.Banking.App.dtos.ChangePhoneDto;
import com.kapil.digitalbank.Banking.App.dtos.UserDto;
import com.kapil.digitalbank.Banking.App.dtos.PasswordUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AccountService accountService;

    // Creating a User
    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody UserDto user) {
        if(user.getFirstName().isEmpty() || user.getEmail().isEmpty() || user.getPhone().isEmpty() || user.getPassword().isEmpty()) {
            return ResponseEntity.badRequest().body("Enter valid details");
        }
        userService.createUser(user);
        return ResponseEntity.ok("User created Successfully");
    }

    // Changing Password of a user
    @PutMapping("/updatePassword")
    public ResponseEntity<?> changePassword(@RequestBody PasswordUpdateDto passwordUpdate) {
        if(userService.updatePassword(passwordUpdate)) {
            return ResponseEntity.ok("Password changed Successfully");
        } else {
            return ResponseEntity.badRequest().body("Old Password is incorrect");
        }
    }

    // Changing Email of a User
    @GetMapping("/change-email")
    public ResponseEntity<?> changeEmail(@RequestBody ChangeEmailDto changeEmailDto) {
        String msg = userService.changeEmail(changeEmailDto);
        char check = msg.charAt(0);
        StringBuilder sb = new StringBuilder(msg);
        if(check == '0') {
            sb.deleteCharAt(0);
            return ResponseEntity.badRequest().body(sb);
        } else {
            sb.deleteCharAt(0);
            return ResponseEntity.ok(sb);
        }
    }

    //Changing Phone number of a User
    @GetMapping("/change-phone")
    public ResponseEntity<?> changePhone(@RequestBody ChangePhoneDto changePhoneDto) {
        String msg = userService.changePhone(changePhoneDto);
        char check = msg.charAt(0);
        StringBuilder sb = new StringBuilder(msg);
        if(check == '0') {
            sb.deleteCharAt(0);
            return ResponseEntity.badRequest().body(sb);
        } else {
            sb.deleteCharAt(0);
            return ResponseEntity.ok(sb);
        }

    }

    





}
