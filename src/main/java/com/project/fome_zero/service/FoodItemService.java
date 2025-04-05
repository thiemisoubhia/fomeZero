package com.project.fome_zero.service;

import com.project.fome_zero.model.FoodItem;
import com.project.fome_zero.model.Restaurant;
import com.project.fome_zero.repository.FoodItemRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class FoodItemService {
   @Autowired
   private FoodItemRepository foodItemRepository;

   public FoodItemService() {
   }

   public List<FoodItem> getAllFoodItems() {
      return this.foodItemRepository.findAll();
   }

   public void addFoodItem(FoodItem foodItem) {
      this.foodItemRepository.save(foodItem);
   }

   public void reserveFoodItem(Long id) {
      FoodItem foodItem = (FoodItem)this.foodItemRepository.findById(id).orElseThrow(() -> {
         return new RuntimeException("FoodItem not found");
      });
      foodItem.setReserved(true);
      this.foodItemRepository.save(foodItem);
   }

   public List<FoodItem> getFoodItemsByRestaurant(Restaurant restaurant) {
      return this.foodItemRepository.findByRestaurant(restaurant);
   }

   public void updateReservationStatus(Long id, boolean reserved) {
      FoodItem foodItem = (FoodItem)this.foodItemRepository.findById(id).orElseThrow(() -> {
         return new RuntimeException("Item não encontrado");
      });
      foodItem.setReserved(reserved);
      this.foodItemRepository.save(foodItem);
   }

   public void delete(Long id) {
      if (!this.foodItemRepository.existsById(id)) {
         throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Alimento não encontrado com ID: " + id);
      } else {
         this.foodItemRepository.deleteById(id);
      }
   }
}
