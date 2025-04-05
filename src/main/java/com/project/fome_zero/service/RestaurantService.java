package com.project.fome_zero.service;

import com.project.fome_zero.model.Restaurant;
import com.project.fome_zero.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class RestaurantService implements UserDetailsService {
   @Autowired
   private RestaurantRepository restaurantRepository;

   public RestaurantService() {
   }

   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      Restaurant restaurant = (Restaurant)this.restaurantRepository.findByUsername(username).orElseThrow(() -> {
         return new UsernameNotFoundException("Usuário não encontrado: " + username);
      });
      System.out.println("Usuário encontrado: " + restaurant.getUsername());
      System.out.println("Senha armazenada: " + restaurant.getPassword());
      return User.withUsername(restaurant.getUsername()).password(restaurant.getPassword()).roles(new String[]{"USER"}).build();
   }
}
