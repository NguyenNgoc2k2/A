package com.company.freshermanagement.service;

import com.company.freshermanagement.entity.Center;
import com.company.freshermanagement.entity.Fresher;
import com.company.freshermanagement.exception.ResourceNotFoundException;
import com.company.freshermanagement.repository.CenterRepository;
import com.company.freshermanagement.repository.FresherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CenterService {

    @Autowired
    private CenterRepository centerRepository;

    @Autowired
    private FresherRepository fresherRepository;

    public List<Center> getAllCenters() {
        return centerRepository.findAll();
    }

    public Center addCenter(Center center) {
        return centerRepository.save(center);
    }

    public void deleteCenter(Long id) {
        Center center = centerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Center not found with id: " + id));
        centerRepository.delete(center);
    }

    public Center updateCenter(Center centerDetails) {
        Center center = centerRepository.findById(centerDetails.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Center not found with id: " + centerDetails.getId()));
        center.setName(centerDetails.getName());
        center.setDirector(centerDetails.getDirector());
        return centerRepository.save(center);
    }

    public Center getCenterById(Long id) {
        return centerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Center not found with id: " + id));
    }

    public Center assignFresherToCenter(Long centerId, Long fresherId) {
        Center center = centerRepository.findById(centerId)
                .orElseThrow(() -> new ResourceNotFoundException("Center not found with id: " + centerId));
        Fresher fresher = fresherRepository.findById(fresherId)
                .orElseThrow(() -> new ResourceNotFoundException("Fresher not found with id: " + fresherId));
        fresher.setCenter(center);
        fresherRepository.save(fresher);
        return center;
    }
}
