package com.example.creditapp.dao;

import com.example.creditapp.models.CreditApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditApplicationRepository extends JpaRepository<CreditApplication, Long> {

    List<CreditApplication> findByStatus(String status);
}