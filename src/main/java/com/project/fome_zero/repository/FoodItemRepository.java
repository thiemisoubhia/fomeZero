package com.project.fome_zero.repository;

import com.project.fome_zero.model.FoodItem;
import com.project.fome_zero.model.Restaurant;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodItemRepository extends JpaRepository<FoodItem, Long> {
   List<FoodItem> findByRestaurant(Restaurant restaurant);
}
