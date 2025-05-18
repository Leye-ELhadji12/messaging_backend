package com.messaging.repository;

import com.messaging.entity.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
      List<User> findAllByUsernameIn(List<String> participants);
}
