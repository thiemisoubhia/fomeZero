package com.project.fome_zero.controller;

import com.project.fome_zero.model.Restaurant;
import com.project.fome_zero.repository.RestaurantRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/registerRestaurant"})
public class RestaurantController {
   @Autowired
   private RestaurantRepository restaurantRepository;
   @Autowired
   private PasswordEncoder passwordEncoder;

   public RestaurantController() {
   }

   @PostMapping({"/addRestaurant"})
   @Transactional
   public String registerRestaurant(@ModelAttribute Restaurant restaurant, Model model) {
      try {
         System.out.println("Tentando cadastrar restaurante: " + restaurant.getUsername());
         restaurant.setPassword(this.passwordEncoder.encode(restaurant.getPassword()));
         System.out.println("Senha codificada: " + restaurant.getPassword());
         this.restaurantRepository.save(restaurant);
         System.out.println("Restaurante salvo com sucesso!");
         model.addAttribute("successMessage", "Restaurante cadastrado com sucesso! Faça login.");
         return "redirect:/login";
      } catch (Exception var4) {
         System.out.println("Erro ao cadastrar restaurante: " + var4.getMessage());
         model.addAttribute("errorMessage", "Erro ao cadastrar o restaurante. Verifique os dados.");
         return "registerRestaurant";
      }
   }
}
