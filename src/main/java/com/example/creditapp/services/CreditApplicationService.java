package com.example.creditapp.services;

import com.example.creditapp.models.CreditAgreement;
import com.example.creditapp.models.CreditApplication;
import com.example.creditapp.dao.CreditAgreementRepository;
import com.example.creditapp.dao.CreditApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Service
public class CreditApplicationService {

    @Autowired
    private CreditApplicationRepository creditApplicationRepository;

    @Autowired
    private CreditAgreementRepository creditAgreementRepository;

    public List<CreditApplication> getAllApplications() {
        return creditApplicationRepository.findAll();
    }

    public CreditApplication saveApplication(CreditApplication application) {
        Integer creditRating = application.getClient().getCreditHistoryRating();
        Random random = new Random();

        double approvalProbability;
        if (creditRating == null || creditRating < 50) approvalProbability = random.nextDouble() * 0.2; // 0-20%
        else if (creditRating < 70) approvalProbability = 0.2 + random.nextDouble() * 0.4; // 20-60%
        else approvalProbability = 0.6 + random.nextDouble() * 0.3; // 60-90%

        boolean approved = random.nextDouble() < approvalProbability;

        application.setApplicationDate(LocalDate.now());
        if (approved) {
            application.setStatus("approved");
            int months = 1 + random.nextInt(12);
            int termDays = months * 30;
            BigDecimal approvedAmount = application.getDesiredAmount()
                    .multiply(BigDecimal.valueOf(0.5 + random.nextDouble() * 0.5));
            application = creditApplicationRepository.save(application);

            CreditAgreement agreement = new CreditAgreement();
            agreement.setApplication(application);
            agreement.setApprovedAmount(approvedAmount);
            agreement.setTermDays(termDays);
            agreement.setSigningStatus("not signed");
            creditAgreementRepository.save(agreement);
        }

        else {
            application.setStatus("rejected");
            application = creditApplicationRepository.save(application);
        }
        return application;
    }

    public List<CreditApplication> getApprovedApplications() {
        return creditApplicationRepository.findByStatus("approved");
    }
}