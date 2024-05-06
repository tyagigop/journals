package Journalism.journal.controller;


import Journalism.journal.entity.User;
import Journalism.journal.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
public class userController {

    @Autowired
    private userService user_service;

    @GetMapping
    public List<User> getUsers(){
        return user_service.getAllusers();
    }
    @PostMapping
    public boolean createUser(@RequestBody User user){
        user_service.createUser(user);
        return true;
    }
    @PutMapping("/{username}")
    public ResponseEntity<String> updateUser(@RequestBody User user,@PathVariable String username){
        User userInDatabase = user_service.getUserByUserName(username);
        System.out.println(username);
        if(userInDatabase != null){
            userInDatabase.setUsername(user.getUsername());
            userInDatabase.setPassword(user.getPassword());
            user_service.SaveUser(userInDatabase);
            return new ResponseEntity<String>("User Updated",HttpStatus.OK);
        }
        return new ResponseEntity<String>("User Updation failed",HttpStatus.NOT_ACCEPTABLE);


    }


}
