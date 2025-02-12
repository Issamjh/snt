package snt.controllers;


import jakarta.transaction.Transactional;
import org.springframework.web.bind.annotation.*;
import snt.repositories.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import snt.entities.User;

import java.util.Date;


@RestController
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;
    Logger log = LoggerFactory.getLogger(UserController.class);


    @Autowired
    public UserController(UserRepository userRepository) {


        this.userRepository = userRepository;
    }


    @GetMapping("/all-users")
    private Iterable<User> send(String name)  {
        final Iterable<User> list = userRepository.findAll();

        list.forEach(user->System.out.println(user));

        return list;
    }
    @GetMapping("/find-user")
    private User find(@RequestParam  String name)  {


        return userRepository.findUserByUsername(name);
    }
    @PutMapping("/update")

    private User find(@RequestParam  String name,@RequestParam String email,@RequestParam boolean enabled) throws Exception {
        User user= userRepository.findUserByUsername(name);
        if(user==null)
            throw new Exception("No Such User "+name);
        user.setUsername(name);
        user.setEmail(email);
        user.setEnabled(enabled);
        user.setEnableTime(new Date());
        userRepository.save(user)     ;
        return user;
    }
}