package controllers;

import entity.OwnerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import repository.UserEntity;
import security.PasswordConfig;
import security.UserService;
import service.OwnerService;

import java.util.Objects;

@RestController
@ComponentScan("security")
@RequestMapping
public class UserController {

    private final UserService userService;
    private final OwnerService ownerService;
    private final PasswordConfig passwordConfig;

    @Autowired
    public UserController(UserService userService, OwnerService ownerService, PasswordConfig passwordConfig) {
        this.userService = userService;
        this.ownerService = ownerService;
        this.passwordConfig = passwordConfig;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/register")
    public void createUser(@RequestBody UserEntity user) {
        user.setPassword(passwordConfig.passwordEncoder().encode(user.getPassword()));
        if(user.getOwner() == null) {
            OwnerEntity ownerEntity = ownerService.GetOwners().stream().filter(owner -> Objects.equals(owner.getName(), user.getUsername())).findAny().orElse(null);
            if(ownerEntity != null)
                user.setOwner(ownerEntity);
        }
        userService.AddUser(user);
    }
}
