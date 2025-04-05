package com.project.fome_zero.service;

import com.project.fome_zero.model.Volunteer;
import com.project.fome_zero.repository.VolunteerRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class VolunteerService {
   private final VolunteerRepository repository;

   public VolunteerService(VolunteerRepository repository) {
      this.repository = repository;
   }

   public Volunteer registerVolunteer(Volunteer volunteer) {
      return (Volunteer)this.repository.save(volunteer);
   }

   public List<Volunteer> getAllVolunteers() {
      return this.repository.findAll();
   }

   public void deleteVolunteer(Long id) {
      this.repository.deleteById(id);
   }
}
