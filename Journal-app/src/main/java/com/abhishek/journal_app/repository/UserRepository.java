package com.abhishek.journal_app.repository;

import com.abhishek.journal_app.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    User findByUserName(String username);
}
