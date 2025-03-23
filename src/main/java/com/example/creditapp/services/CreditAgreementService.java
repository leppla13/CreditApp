package com.example.creditapp.services;

import com.example.creditapp.models.CreditAgreement;
import com.example.creditapp.dao.CreditAgreementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CreditAgreementService {

    @Autowired
    private CreditAgreementRepository creditAgreementRepository;

    public List<CreditAgreement> getAllAgreements() {
        return creditAgreementRepository.findAll();
    }

    public CreditAgreement getAgreementById(Long id) {
        Optional<CreditAgreement> optionalAgreement = creditAgreementRepository.findById(id);
        return optionalAgreement.orElse(null);
    }

    public CreditAgreement signAgreement(Long id) {
        CreditAgreement agreement = getAgreementById(id);
        if (agreement != null && "not signed".equals(agreement.getSigningStatus())) {
            agreement.setSigningDate(LocalDate.now());
            agreement.setSigningStatus("signed");
            creditAgreementRepository.save(agreement);
        }
        return agreement;
    }

    public List<CreditAgreement> getSignedAgreements() {
        return creditAgreementRepository.findBySigningStatus("signed");
    }
}