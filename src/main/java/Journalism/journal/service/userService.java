package Journalism.journal.service;


import Journalism.journal.entity.User;
import Journalism.journal.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class userService {

    @Autowired
    private userRepository userrepository;

    public void createUser(User user){
        userrepository.save(user);
    }
    public List<User> getAllusers(){
        return userrepository.findAll();
    }
    public User getUserByUserName(String username){
        return userrepository.findByusername(username);
    }
    public void SaveUser(User user){
        userrepository.save(user);
    }

}
