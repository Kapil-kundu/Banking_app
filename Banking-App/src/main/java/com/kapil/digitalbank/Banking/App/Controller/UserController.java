package com.kapil.digitalbank.Banking.App.Controller;


import com.kapil.digitalbank.Banking.App.Service.AccountService;
import com.kapil.digitalbank.Banking.App.Service.UserService;
import com.kapil.digitalbank.Banking.App.dtos.UserDto;
import com.kapil.digitalbank.Banking.App.dtos.PasswordUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AccountService accountService;

    // Creating a User
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserDto user) {
        if(user.getFirstName().isEmpty() || user.getEmail().isEmpty() || user.getPhone().isEmpty() || user.getPassword().isEmpty()) {
            return ResponseEntity.badRequest().body("Enter valid details");
        }
        userService.createUser(user);
        return ResponseEntity.ok("User created Successfully");
    }

    // Changing Password of a user
    @PutMapping()
    public ResponseEntity<?> changePassword(@RequestBody PasswordUpdateDto passwordUpdateDto) {

        userService.updatePassword(passwordUpdateDto);
        return ResponseEntity.ok("Password Changed Successfully");
    }




}
