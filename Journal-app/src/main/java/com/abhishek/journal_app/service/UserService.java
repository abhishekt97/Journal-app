package com.abhishek.journal_app.service;

import com.abhishek.journal_app.entity.JournalEntry;
import com.abhishek.journal_app.entity.User;
import com.abhishek.journal_app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User createNewUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

    public User updateUser(User user, String username) {

        User oldUser = userRepository.findByUserName(username);

        if(oldUser != null){
            oldUser.setUsername(user.getUsername());
            oldUser.setPassword(user.getPassword());
        }
        return userRepository.save(oldUser);
    }

    public List<JournalEntry> getUserEntries(String username) {
        User userInDb = userRepository.findByUserName(username);
        List<JournalEntry> userEntries = userInDb.getEntries();

        if(userEntries != null && !userEntries.isEmpty())
            return userEntries;
        else {
            throw new NullPointerException("No entry for user.");
        }
    }
    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }
    public void saveUser(User user) {
        userRepository.save(user);
    }
}
