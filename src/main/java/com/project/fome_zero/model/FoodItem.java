package com.project.fome_zero.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class FoodItem {
   @Id
   @GeneratedValue(
      strategy = GenerationType.IDENTITY
   )
   private Long id;
   private String name;
   private String description;
   private boolean reserved;
   @ManyToOne
   @JoinColumn(
      name = "restaurant_id",
      nullable = false
   )
   private Restaurant restaurant;

   public FoodItem() {
   }

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getDescription() {
      return this.description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public boolean isReserved() {
      return this.reserved;
   }

   public void setReserved(boolean reserved) {
      this.reserved = reserved;
   }

   public Restaurant getRestaurant() {
      return this.restaurant;
   }

   public void setRestaurant(Restaurant restaurant) {
      this.restaurant = restaurant;
   }
}
