package com.example.segomarketnew.repository;

import com.example.segomarketnew.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

   Optional<User> findByName(String userName);

  boolean existsByNameAndEmail (String name, String email);


}
