package com.abhishek.journal_app.repository;

import com.abhishek.journal_app.entity.JournalEntry;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalRepository extends MongoRepository<JournalEntry, String> {

}
