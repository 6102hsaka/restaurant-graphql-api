package dev.sharma.akash.api.restaurant.user.controller;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.RestController;

import dev.sharma.akash.api.restaurant.user.model.User;
import dev.sharma.akash.api.restaurant.user.service.UserService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@PreAuthorize("isAuthenticated()")
	@MutationMapping
	public CompletableFuture<User> addUser(@AuthenticationPrincipal Jwt jwt) {
		log.info("Received request to save user");
		return userService.addUser(jwt);
	}
}
