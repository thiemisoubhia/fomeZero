// Source code is decompiled from a .class file using FernFlower decompiler.
package com.project.fome_zero.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
   public SecurityConfig() {
   }

   @Bean
   public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
      http.authorizeHttpRequests((auth) -> {
         ((AuthorizeHttpRequestsConfigurer.AuthorizedUrl) ((AuthorizeHttpRequestsConfigurer.AuthorizedUrl) ((AuthorizeHttpRequestsConfigurer.AuthorizedUrl) ((AuthorizeHttpRequestsConfigurer.AuthorizedUrl) ((AuthorizeHttpRequestsConfigurer.AuthorizedUrl) ((AuthorizeHttpRequestsConfigurer.AuthorizedUrl) ((AuthorizeHttpRequestsConfigurer.AuthorizedUrl) ((AuthorizeHttpRequestsConfigurer.AuthorizedUrl) auth
               .requestMatchers(new String[] { "/", "/index", "/registerRestaurant/**", "/restaurantArea/reserve/**",
                     "/images/**", "/css/**", "/sucessPage", "/reservaSucess" }))
               .permitAll().requestMatchers(new String[] { "/volunteer" })).permitAll()
               .requestMatchers(new String[] { "/volunteers/register" })).permitAll()
               .requestMatchers(new String[] { "/volunteers" })).authenticated()
               .requestMatchers(new String[] { "/volunteers/**" })).authenticated()
               .requestMatchers(new String[] { "/h2-console/**" })).permitAll()
               .requestMatchers(new String[] { "/restaurantArea/**" })).authenticated().anyRequest()).authenticated();
      }).formLogin((login) -> {
         ((FormLoginConfigurer) ((FormLoginConfigurer) ((FormLoginConfigurer) login.loginPage("/login")
               .loginProcessingUrl("/login")).defaultSuccessUrl("/restaurantArea", true)).failureUrl("/login?error"))
               .permitAll();
      }).logout((logout) -> {
         logout.logoutUrl("/logout").logoutSuccessUrl("/index").permitAll();
      }).csrf((csrf) -> {
         csrf.disable();
      }).headers((headers) -> {
         headers.frameOptions().sameOrigin();
      });
      return (SecurityFilterChain) http.build();
   }

   @Bean
   public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
   }
}
