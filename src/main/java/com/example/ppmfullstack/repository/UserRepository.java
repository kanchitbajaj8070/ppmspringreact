package com.example.ppmfullstack.repository;

import com.example.ppmfullstack.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
    User findUserByUsername(String username);
    User getById( Long id);
}
