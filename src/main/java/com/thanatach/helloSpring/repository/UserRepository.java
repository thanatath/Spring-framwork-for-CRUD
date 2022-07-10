package com.thanatach.helloSpring.repository;

import com.thanatach.helloSpring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User , Integer> {
    User findByName(String user_name);
}
