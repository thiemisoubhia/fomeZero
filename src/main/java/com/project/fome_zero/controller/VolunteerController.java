package com.project.fome_zero.controller;

import com.project.fome_zero.model.Volunteer;
import com.project.fome_zero.service.VolunteerService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/volunteers"})
public class VolunteerController {
   private final VolunteerService service;

   public VolunteerController(VolunteerService service) {
      this.service = service;
   }

   @GetMapping({"/register"})
   public String showRegisterForm(Model model) {
      model.addAttribute("volunteer", new Volunteer());
      return "volunteer";
   }

   @PostMapping({"/register"})
   public String registerVolunteer(@ModelAttribute Volunteer volunteer) {
      this.service.registerVolunteer(volunteer);
      return "redirect:/sucessPage";
   }

   @GetMapping
   public String listVolunteers(Model model) {
      List<Volunteer> volunteers = this.service.getAllVolunteers();
      System.out.println("Voluntários encontrados: " + volunteers);
      model.addAttribute("volunteers", volunteers);
      return "volunteers";
   }

   @PostMapping({"/delete/{id}"})
   public String deleteVolunteer(@PathVariable Long id) {
      this.service.deleteVolunteer(id);
      return "redirect:/volunteers";
   }
}
