package com.abhishek.journal_app.controller;

import com.abhishek.journal_app.entity.JournalEntry;
import com.abhishek.journal_app.service.JournalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/journal")
public class JournalController {

    private final JournalService journalService;


    @PostMapping("/save")
    public ResponseEntity<Void> createEntry(@RequestBody JournalEntry entry, @RequestParam String username){
        journalService.saveEntry(entry, username);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/entries")
    public ResponseEntity<List<JournalEntry>> findAll(){
        return new ResponseEntity<>(journalService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/entry/{id}")
    public ResponseEntity<JournalEntry> findEntryById(@PathVariable String id){
        return new ResponseEntity<>(journalService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteEntry(@PathVariable String id,@RequestParam String username){

        return new ResponseEntity<>(journalService.deleteById(id, username), HttpStatus.NO_CONTENT);
    }

}
