package dev.sharma.akash.api.restaurant.repository;

import java.util.List;

import dev.sharma.akash.api.restaurant.model.MenuItem;

public interface MenuItemRepository {
	public List<MenuItem> findAll(List<String> columns);

	public List<MenuItem> findAllByCategory(String category, List<String> columns);
}
