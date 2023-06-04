package dev.sharma.akash.api.restaurant.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.sharma.akash.api.restaurant.user.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

}
