package com.company.freshermanagement.service;

import com.company.freshermanagement.entity.Fresher;
import com.company.freshermanagement.repository.FresherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FresherService {

    @Autowired
    private FresherRepository fresherRepository;

    public List<Fresher> getAllFreshers() {
        return fresherRepository.findAll();
    }

    public Fresher addFresher(Fresher fresher) {
        return fresherRepository.save(fresher);
    }

    public void deleteFresher(Long id) {
        fresherRepository.deleteById(id);
    }

    public Fresher updateFresher(Fresher fresher) {
        return fresherRepository.save(fresher);
    }

    public Optional<Fresher> getFresherById(Long id) {
        return fresherRepository.findById(id);
    }

    // Phương thức tìm kiếm Freshers theo tên, ngôn ngữ lập trình hoặc email
    public List<Fresher> searchFreshers(String name, String programmingLanguage, String email) {
        if (name != null && !name.isEmpty()) {
            return fresherRepository.findByNameContainingIgnoreCase(name);
        } else if (programmingLanguage != null && !programmingLanguage.isEmpty()) {
            return fresherRepository.findByProgrammingLanguageContainingIgnoreCase(programmingLanguage);
        } else if (email != null && !email.isEmpty()) {
            return fresherRepository.findByEmailContainingIgnoreCase(email);
        } else {
            return fresherRepository.findAll();
        }
    }
}
