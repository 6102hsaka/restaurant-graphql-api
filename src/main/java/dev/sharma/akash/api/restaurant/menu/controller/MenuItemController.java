package dev.sharma.akash.api.restaurant.menu.controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.sharma.akash.api.restaurant.menu.model.MenuItem;
import dev.sharma.akash.api.restaurant.menu.service.MenuItemService;
import graphql.schema.DataFetchingFieldSelectionSet;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class MenuItemController {

	@Autowired
	private MenuItemService menuItemService;

	@QueryMapping
	public CompletableFuture<List<MenuItem>> getAllMenuItem(DataFetchingFieldSelectionSet set) {
		log.info("Received request to get all MenuItems");
		List<String> columns = set.getImmediateFields().stream()
				.map(column -> column.toString().replaceAll("MenuItem.", "")).collect(Collectors.toList());
		return menuItemService.getAllMenuItem(columns);
	}

	@QueryMapping
	public CompletableFuture<List<MenuItem>> getAllMenuItemByCategory(@Argument String category,
			DataFetchingFieldSelectionSet set) {
		log.info("Received request to get all MenuItems for {} category", category);
		List<String> columns = set.getImmediateFields().stream()
				.map(column -> column.toString().replaceAll("MenuItem.", "")).collect(Collectors.toList());
		return menuItemService.getAllMenuItemByCategory(category, columns);
	}
}
