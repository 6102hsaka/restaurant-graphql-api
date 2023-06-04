package dev.sharma.akash.api.restaurant.user.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "restaurant_user")
public class User {
	@Id
	private String email;
	private String name;
	private String imageUrl;
}
