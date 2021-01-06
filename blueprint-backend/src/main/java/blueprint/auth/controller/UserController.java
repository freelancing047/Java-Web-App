package blueprint.auth.controller;


import blueprint.auth.model.entity.User;
import blueprint.auth.model.repo.UserRepository;
import blueprint.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.security.Principal;

@RestController
@RequestMapping(value = "/uaa")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    UserService service;
    @Autowired
    UserRepository repository;

    @Autowired
    public UserController(){

    }


    @GetMapping(value = "/currentUser")
    public User getUser(Principal principal, HttpServletResponse response) {
        if (principal != null) {
            User user = userService.findByUserName(principal.getName());
            user.setPassword(null);
            return user;
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return null;
        }
    }

    @PostMapping(value = "/signup")
    public boolean saveUser(@Valid @RequestBody User user) {
        if (repository.findByUsername(user.getUsername()) == null) {
            //String username, String name,String email,String password
            service.save(new User(user.getUsername(), user.getName(), user.getEmail(), passwordEncoder.encode(user.getPassword()),user.getRoles()));
            return true;
        } else {
            return false;
        }
    }

    @RequestMapping("/user")
    @ResponseStatus(HttpStatus.OK)
    public Principal getUser(Principal principal, HttpServletRequest request) {
        return principal;
    }

}