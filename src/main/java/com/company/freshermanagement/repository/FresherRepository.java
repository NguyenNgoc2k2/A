package com.company.freshermanagement.repository;

import com.company.freshermanagement.entity.Fresher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface    FresherRepository extends JpaRepository<Fresher, Long> {
    List<Fresher> findByNameContainingIgnoreCase(String name);
    List<Fresher> findByProgrammingLanguageContainingIgnoreCase(String programmingLanguage);
    List<Fresher> findByEmailContainingIgnoreCase(String email);
}
