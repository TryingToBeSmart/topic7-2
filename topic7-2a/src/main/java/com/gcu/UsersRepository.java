package com.gcu;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsersRepository extends MongoRepository<UserEntity, String> {
	UserEntity findByUsername(String username);
}
