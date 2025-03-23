package com.example.creditapp.dao;

import com.example.creditapp.models.CreditAgreement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditAgreementRepository extends JpaRepository<CreditAgreement, Long> {

    List<CreditAgreement> findBySigningStatus(String signingStatus);
}