package com.project.fome_zero.controller;

import com.project.fome_zero.model.FoodItem;
import com.project.fome_zero.service.FoodItemService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebController {
   @Autowired
   private FoodItemService foodItemService;

   public WebController() {
   }

   @GetMapping({"/"})
   public String getFoodItems(Model model) {
      List<FoodItem> foodItems = this.foodItemService.getAllFoodItems();
      model.addAttribute("foodItems", foodItems);
      return "index";
   }

   @GetMapping({"/login"})
   public String login(@RequestParam(value = "error",required = false) String error, Model model) {
      if (error != null) {
         model.addAttribute("error", "Nome de usuário ou senha inválidos. Tente novamente.");
      }

      return "login";
   }

   @GetMapping({"/webRestaurantArea"})
   public String restaurantArea(Authentication authentication) {
      return authentication != null && authentication.isAuthenticated() ? "restaurantArea" : "redirect:/login";
   }

   @GetMapping({"/listVolunteer"})
   public String listVolunteer(Authentication authentication) {
      return authentication != null && authentication.isAuthenticated() ? "redirect:/volunteers" : "redirect:/login";
   }

   @GetMapping({"/volunteer"})
   public String volunteer() {
      return "volunteer";
   }

   @GetMapping({"/reservaSucess"})
   public String reservaSucess() {
      return "reservaSucess";
   }

   @GetMapping({"/sucessPage"})
   public String sucessPage() {
      return "sucessPage";
   }

   @GetMapping({"/registerRestaurant"})
   public String registerRestaurant() {
      return "registerRestaurant";
   }
}
