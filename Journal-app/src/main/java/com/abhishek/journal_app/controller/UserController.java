package com.abhishek.journal_app.controller;

import com.abhishek.journal_app.entity.JournalEntry;
import com.abhishek.journal_app.entity.User;
import com.abhishek.journal_app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @GetMapping("/all")
    public ResponseEntity<List<User>> getUsers(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<User> createUser(@RequestBody User user){
        return new ResponseEntity<>(userService.createNewUser(user), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id){
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody User user,@RequestParam String username){
        return new ResponseEntity<>(userService.updateUser(user, username), HttpStatus.CREATED);
    }


    @GetMapping("/entries")
    public ResponseEntity<List<JournalEntry>> getAllEntriesOfUser(@RequestParam String username){
        return new ResponseEntity<>(userService.getUserEntries(username), HttpStatus.OK);
    }
}
