package com.postgresql.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.postgresql.demo.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
