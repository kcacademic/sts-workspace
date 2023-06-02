package com.kchandrakant.mongotests.repository;

import com.kchandrakant.mongotests.model.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface UserRepository extends ReactiveMongoRepository<User, String> {
}
