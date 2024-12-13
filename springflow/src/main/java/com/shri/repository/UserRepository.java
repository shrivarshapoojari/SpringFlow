package com.shri.repository;

import com.shri.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User,Long> {

    User findByEmail(String Email);
}
