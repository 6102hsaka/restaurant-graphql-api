package dev.sharma.akash.api.restaurant.user.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.security.oauth2.jwt.Jwt;

import dev.sharma.akash.api.restaurant.user.model.User;

public interface UserService {

	public CompletableFuture<User> addUser(Jwt jwt);
}
