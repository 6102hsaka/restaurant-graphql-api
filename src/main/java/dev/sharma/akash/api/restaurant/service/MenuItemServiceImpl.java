package dev.sharma.akash.api.restaurant.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import dev.sharma.akash.api.restaurant.model.MenuItem;
import dev.sharma.akash.api.restaurant.repository.MenuItemRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MenuItemServiceImpl implements MenuItemService {

	@Autowired
	private MenuItemRepository menuItemRepository;

	@Async
	@Override
	public CompletableFuture<List<MenuItem>> getAllMenuItem(List<String> columns) {
		log.info("Processing request to get all menu items");
		return CompletableFuture.supplyAsync(() -> menuItemRepository.findAll(columns));
	}

	@Async
	@Override
	public CompletableFuture<List<MenuItem>> getAllMenuItemByCategory(String category, List<String> columns) {
		log.info("Processing request to get all MenuItems for {} category", category);
		return CompletableFuture.supplyAsync(() -> menuItemRepository.findAllByCategory(category, columns));
	}

}
