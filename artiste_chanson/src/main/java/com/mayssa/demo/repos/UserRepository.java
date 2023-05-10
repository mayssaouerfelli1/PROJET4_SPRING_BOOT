package com.mayssa.demo.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mayssa.demo.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
User findByUsername (String username);
}