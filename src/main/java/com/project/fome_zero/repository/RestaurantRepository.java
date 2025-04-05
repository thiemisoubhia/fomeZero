package com.project.fome_zero.repository;

import com.project.fome_zero.model.Restaurant;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
   Optional<Restaurant> findByUsername(String username);
}
