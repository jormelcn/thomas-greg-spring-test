package com.jorge.thomas.test.app.auth.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.jorge.thomas.test.app.auth.models.User;

public interface UserRepository extends MongoRepository<User, String> {
  Optional<User> findFirstByUsername(String username);
}
