package com.project.fome_zero.controller;

import com.project.fome_zero.model.FoodItem;
import com.project.fome_zero.model.Restaurant;
import com.project.fome_zero.repository.FoodItemRepository;
import com.project.fome_zero.repository.RestaurantRepository;
import org.springframework.ui.Model;
import com.project.fome_zero.service.FoodItemService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class FoodController {
   private final FoodItemRepository foodItemRepository;
   private final RestaurantRepository restaurantRepository;
   @Autowired
   private FoodItemService foodService;

   public FoodController(FoodItemRepository foodItemRepository, RestaurantRepository restaurantRepository) {
      this.foodItemRepository = foodItemRepository;
      this.restaurantRepository = restaurantRepository;
   }

   @GetMapping({"/restaurantArea"})
   public String showRestaurant(Model model) {
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      String username = authentication.getName();
      Optional<Restaurant> restaurantOpt = this.restaurantRepository.findByUsername(username);
      if (restaurantOpt.isPresent()) {
         model.addAttribute("restaurant", restaurantOpt.get());
      } else {
         model.addAttribute("restaurant", (Object)null);
      }

      return "restaurantArea";
   }

   @PostMapping({"/restaurantArea/add"})
   public String addFoodItem(@RequestParam String name, @RequestParam String description, Model model) {
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      String username = authentication.getName();
      Optional<Restaurant> restaurantOpt = this.restaurantRepository.findByUsername(username);
      if (restaurantOpt.isPresent()) {
         Restaurant restaurant = (Restaurant)restaurantOpt.get();
         FoodItem foodItem = new FoodItem();
         foodItem.setName(name);
         foodItem.setDescription(description);
         foodItem.setRestaurant(restaurant);
         this.foodItemRepository.save(foodItem);
         model.addAttribute("successMessage", "Alimento cadastrado com sucesso!");
         model.addAttribute("restaurant", restaurant);
      } else {
         model.addAttribute("errorMessage", "Erro ao cadastrar alimento: Restaurante não encontrado.");
      }

      return "restaurantArea";
   }

   @PostMapping("/restaurantArea/reserve/{id}")
   public String reserveFoodItem(@PathVariable Long id, @RequestParam(required = false) boolean reserved, Model model) {
      Optional<FoodItem> foodItemOpt = foodItemRepository.findById(id);
      if (foodItemOpt.isPresent()) {
         FoodItem foodItem = (FoodItem)foodItemOpt.get();
         foodItem.setReserved(reserved);
         this.foodItemRepository.save(foodItem);
         if (reserved) {
            model.addAttribute("nome", foodItem.getName());
            model.addAttribute("endereco", foodItem.getRestaurant().getAddress());
            return "reservaSucess";
        }
      }
      
      return "redirect:/";
   }

   @PostMapping({"/restaurantArea/excluir/{id}"})
   public String excluir(@PathVariable Long id, Model model) {
      try {
         if (id <= 0L) {
            throw new IllegalArgumentException("ID inválido para exclusão.");
         } else {
            this.foodService.delete(id);
            return "redirect:/";
         }
      } catch (ResponseStatusException var4) {
         model.addAttribute("errorMessage", var4.getReason());
         return "redirect:/errorPage";
      } catch (Exception var5) {
         System.err.println("Erro ao excluir item: " + var5.getMessage());
         return "redirect:/errorPage";
      }
   }
}
