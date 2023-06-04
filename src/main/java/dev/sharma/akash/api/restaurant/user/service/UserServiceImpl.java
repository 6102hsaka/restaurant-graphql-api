package dev.sharma.akash.api.restaurant.user.service;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import dev.sharma.akash.api.restaurant.user.model.User;
import dev.sharma.akash.api.restaurant.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Async
	@Override
	public CompletableFuture<User> addUser(Jwt jwt) {
		log.info("Processing request to save user");
		return CompletableFuture.supplyAsync(() -> {
			String email = jwt.getClaimAsString("email");
			if (email == null) {
				log.error("Email not found in claims");
				return null;
			}
			Optional<User> userOptional = userRepository.findById(email);
			if (userOptional.isPresent()) {
				User user = userOptional.get();
				log.info("User {} already exists", user.getEmail());
				return user;
			}
			String name = jwt.getClaimAsString("name");
			String imageUrl = jwt.getClaimAsString("picture");
			User user = userRepository.save(new User(email, name, imageUrl));
			log.info("Successfully saved user {}", user.getEmail());
			return user;
		});
	}
}
