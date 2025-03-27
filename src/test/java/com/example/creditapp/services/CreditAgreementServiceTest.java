package com.example.creditapp.services;

import com.example.creditapp.models.CreditAgreement;
import com.example.creditapp.models.CreditApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreditAgreementServiceTest {

    @Mock
    private CreditAgreementRepository creditAgreementRepository;

    @InjectMocks
    private CreditAgreementService creditAgreementService;

    @Test
    public void testSignAgreement_Success() {
        CreditApplication application = new CreditApplication();
        CreditAgreement agreement = new CreditAgreement();
        agreement.setId(1L);
        agreement.setApplication(application);
        agreement.setSigningStatus("not signed");

        when(creditAgreementRepository.findById(1L)).thenReturn(Optional.of(agreement));
        when(creditAgreementRepository.save(any(CreditAgreement.class))).thenAnswer(invocation -> invocation.getArgument(0));

        CreditAgreement signedAgreement = creditAgreementService.signAgreement(1L);

        assertNotNull(signedAgreement);
        assertEquals("signed", signedAgreement.getSigningStatus());
        assertEquals(LocalDate.now(), signedAgreement.getSigningDate());
        verify(creditAgreementRepository, times(1)).findById(1L);
        verify(creditAgreementRepository, times(1)).save(agreement);
    }

    @Test
    public void testSignAgreement_AlreadySigned() {
        CreditApplication application = new CreditApplication();
        CreditAgreement agreement = new CreditAgreement();
        agreement.setId(1L);
        agreement.setApplication(application);
        agreement.setSigningStatus("signed");

        when(creditAgreementRepository.findById(1L)).thenReturn(Optional.of(agreement));

        CreditAgreement result = creditAgreementService.signAgreement(1L);

        assertNotNull(result);
        assertEquals("signed", result.getSigningStatus());
        verify(creditAgreementRepository, times(1)).findById(1L);
        verify(creditAgreementRepository, never()).save(any());
    }

    @Test
    public void testGetSignedAgreements() {
        CreditApplication application = new CreditApplication();
        CreditAgreement agreement = new CreditAgreement();
        agreement.setApplication(application);
        agreement.setSigningStatus("signed");

        List<CreditAgreement> agreements = List.of(agreement);
        when(creditAgreementRepository.findBySigningStatus("signed")).thenReturn(agreements);

        List<CreditAgreement> result = creditAgreementService.getSignedAgreements();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("signed", result.get(0).getSigningStatus());
        verify(creditAgreementRepository, times(1)).findBySigningStatus("signed");
    }
}