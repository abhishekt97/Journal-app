package com.abhishek.journal_app.entity;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "journal_entries")
@Data
public class JournalEntry {

    @Id
    private String id;
    @NonNull
    private String title;
    private String content;
    private LocalDate date;
}
