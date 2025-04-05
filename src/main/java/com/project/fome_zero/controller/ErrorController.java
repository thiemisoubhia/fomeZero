package com.project.fome_zero.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {
   public ErrorController() {
   }

   @GetMapping({"/errorPage"})
   public String showErrorPage() {
      return "errorPage";
   }
}
