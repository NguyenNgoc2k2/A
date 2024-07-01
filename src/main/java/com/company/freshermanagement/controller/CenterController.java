package com.company.freshermanagement.controller;

import com.company.freshermanagement.entity.Center;
import com.company.freshermanagement.exception.ResourceNotFoundException;
import com.company.freshermanagement.service.CenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/centers")
public class CenterController {

    @Autowired
    private CenterService centerService;

    @GetMapping
    public List<Center> getAllCenters() {
        return centerService.getAllCenters();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Center> getCenterById(@PathVariable(value = "id") Long centerId) throws ResourceNotFoundException {
        Center center = centerService.getCenterById(centerId);
        return ResponseEntity.ok().body(center);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Center createCenter(@RequestBody Center center) {
        return centerService.createCenter(center);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Center> updateCenter(@PathVariable(value = "id") Long centerId, @RequestBody Center centerDetails) throws ResourceNotFoundException {
        Center updatedCenter = centerService.updateCenter(centerId, centerDetails);
        return ResponseEntity.ok(updatedCenter);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteCenter(@PathVariable(value = "id") Long centerId) throws ResourceNotFoundException {
        centerService.deleteCenter(centerId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{centerId}/addFresher/{fresherId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Center> addFresherToCenter(@PathVariable(value = "centerId") Long centerId, @PathVariable(value = "fresherId") Long fresherId) throws ResourceNotFoundException {
        Center center = centerService.addFresherToCenter(centerId, fresherId);
        return ResponseEntity.ok(center);
    }
}
