package dev.sharma.akash.api.restaurant.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import dev.sharma.akash.api.restaurant.model.MenuItem;

public interface MenuItemService {

	public CompletableFuture<List<MenuItem>> getAllMenuItem(List<String> columns);

	public CompletableFuture<List<MenuItem>> getAllMenuItemByCategory(String category, List<String> columns);

}
