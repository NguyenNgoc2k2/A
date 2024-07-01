package com.company.freshermanagement.controller;
import com.company.freshermanagement.entity.Fresher;
import com.company.freshermanagement.exception.ResourceNotFoundException;
import com.company.freshermanagement.service.FresherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/freshers")
public class FresherController {

    @Autowired
    private FresherService fresherService;

    @GetMapping
    public List<Fresher> getAllFreshers() {
        return fresherService.getAllFreshers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fresher> getFresherById(@PathVariable(value = "id") Long fresherId) throws ResourceNotFoundException {
        Fresher fresher = fresherService.getFresherById(fresherId);
        return ResponseEntity.ok().body(fresher);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Fresher createFresher(@RequestBody Fresher fresher) {
        return fresherService.createFresher(fresher);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Fresher> updateFresher(@PathVariable(value = "id") Long fresherId, @RequestBody Fresher fresherDetails) throws ResourceNotFoundException {
        Fresher updatedFresher = fresherService.updateFresher(fresherId, fresherDetails);
        return ResponseEntity.ok(updatedFresher);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteFresher(@PathVariable(value = "id") Long fresherId) throws ResourceNotFoundException {
        fresherService.deleteFresher(fresherId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public List<Fresher> searchFreshers(@RequestParam(value = "name", required = false) String name,
                                        @RequestParam(value = "programmingLanguage", required = false) String programmingLanguage,
                                        @RequestParam(value = "email", required = false) String email) {
        return fresherService.searchFreshers(name, programmingLanguage, email);
    }
}
