package com.abhishek.journal_app.service;

import com.abhishek.journal_app.entity.JournalEntry;
import com.abhishek.journal_app.entity.User;
import com.abhishek.journal_app.repository.JournalRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Slf4j
public class JournalService {

    private JournalRepository journalRepository;

    private UserService userService;

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName) {
        try {
            User user = userService.findByUserName(userName);
            journalEntry.setDate(LocalDate.now());
            JournalEntry saved = journalRepository.save(journalEntry);
            user.getEntries().add(saved);
            userService.saveUser(user);
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while saving the entry.", e);
        }
    }

    public void saveEntry(JournalEntry journalEntry) {
        journalRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll() {
        return journalRepository.findAll();
    }

    public JournalEntry findById(String  id) {
        return journalRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Transactional
    public boolean deleteById(String  id, String userName) {
        boolean removed = false;
        try {
            User user = userService.findByUserName(userName);
            removed = user.getEntries().removeIf(x -> x.getId().equals(id));
            if (removed) {
                userService.saveUser(user);
                journalRepository.deleteById(id);
            }
        } catch (Exception e) {
            log.error("Error ",e);
            throw new RuntimeException("An error occurred while deleting the entry.", e);
        }
        return removed;
    }
}
