package com.example.creditapp.services;

import com.example.creditapp.dao.CreditAgreementDao;
import com.example.creditapp.models.CreditAgreement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CreditAgreementService {

    @Autowired
    private CreditAgreementDao creditAgreementDao;

    public List<CreditAgreement> getAllAgreements() {
        return creditAgreementDao.getAllAgreements();
    }

    public CreditAgreement getAgreementById(Long id) {
        return creditAgreementDao.getAgreementById(id);
    }

    public CreditAgreement signAgreement(Long id) {
        CreditAgreement agreement = getAgreementById(id);
        if (agreement != null && "not signed".equals(agreement.getSigningStatus())) {
            agreement.setSigningDate(LocalDate.now());
            agreement.setSigningStatus("signed");
            creditAgreementDao.updateAgreement(agreement);
        }
        return agreement;
    }

    public List<CreditAgreement> getSignedAgreements() {
        return creditAgreementDao.getSignedAgreements();
    }
}