package dev.sharma.akash.api.restaurant.menu.repository;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dev.sharma.akash.api.restaurant.menu.model.MenuItem;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Selection;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class MenuItemRepositoryImpl implements MenuItemRepository {

	@Autowired
	private EntityManager entityManager;

	private CriteriaQuery<MenuItem> createMenuItemQuery(List<String> columns, Map<String, Object> map) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<MenuItem> query = criteriaBuilder.createQuery(MenuItem.class);
		Root<MenuItem> root = query.from(MenuItem.class);

		Selection<?>[] selections = columns.stream().map(column -> root.get(column).alias(column))
				.toArray(Selection<?>[]::new);

		query.select(criteriaBuilder.construct(MenuItem.class, selections));
		for (Map.Entry<String, Object> mapEntry : map.entrySet()) {
			query.where(entityManager.getCriteriaBuilder().equal(root.get(mapEntry.getKey()), mapEntry.getValue()));
		}
		return query;
	}

	@Override
	public List<MenuItem> findAll(List<String> columns) {
		log.info("Fetching all MenuItems");
		CriteriaQuery<MenuItem> query = createMenuItemQuery(columns, Collections.emptyMap());

		TypedQuery<MenuItem> typedQuery = entityManager.createQuery(query);
		List<MenuItem> menuItems = typedQuery.getResultList();
		log.info("Successfully fetched {} MenuItems", menuItems.size());
		return menuItems;
	}

	@Override
	public List<MenuItem> findAllByCategory(String category, List<String> columns) {
		log.info("Fetching all MenuItems for {} category", category);
		Map<String, Object> map = new HashMap<>();
		map.put("category", category);
		CriteriaQuery<MenuItem> query = createMenuItemQuery(columns, map);

		TypedQuery<MenuItem> typedQuery = entityManager.createQuery(query);
		List<MenuItem> menuItems = typedQuery.getResultList();
		log.info("Successfully fetched {} MenuItems for {} category", menuItems.size(), category);
		return menuItems;
	}
}
