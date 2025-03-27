package com.example.creditapp.services;

import com.example.creditapp.models.Client;
import com.example.creditapp.models.CreditApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreditApplicationServiceTest {

    @Mock
    private CreditApplicationRepository creditApplicationRepository;

    @Mock
    private CreditAgreementRepository creditAgreementRepository;

    @InjectMocks
    private CreditApplicationService creditApplicationService;

    @Test
    public void testSaveApplication_Approved() {
        Client client = new Client();
        client.setCreditHistoryRating(75); // >= 60

        CreditApplication application = new CreditApplication();
        application.setClient(client);
        application.setDesiredAmount(BigDecimal.valueOf(10000));

        when(creditApplicationRepository.save(any(CreditApplication.class))).thenAnswer(invocation -> {
            CreditApplication saved = invocation.getArgument(0);
            saved.setId(1L);
            return saved;
        });
        when(creditAgreementRepository.save(any())).thenReturn(null);

        CreditApplication savedApplication = creditApplicationService.saveApplication(application);

        assertNotNull(savedApplication);
        assertEquals("approved", savedApplication.getStatus());
        assertEquals(LocalDate.now(), savedApplication.getApplicationDate());
        verify(creditApplicationRepository, times(1)).save(application);
        verify(creditAgreementRepository, times(1)).save(any());
    }

    @Test
    public void testSaveApplication_Rejected() {
        Client client = new Client();
        client.setCreditHistoryRating(50); // < 60

        CreditApplication application = new CreditApplication();
        application.setClient(client);
        application.setDesiredAmount(BigDecimal.valueOf(10000));

        when(creditApplicationRepository.save(any(CreditApplication.class))).thenAnswer(invocation -> {
            CreditApplication saved = invocation.getArgument(0);
            saved.setId(1L);
            return saved;
        });

        CreditApplication savedApplication = creditApplicationService.saveApplication(application);

        assertNotNull(savedApplication);
        assertEquals("rejected", savedApplication.getStatus());
        assertEquals(LocalDate.now(), savedApplication.getApplicationDate());
        verify(creditApplicationRepository, times(1)).save(application);
        verify(creditAgreementRepository, never()).save(any());
    }

    @Test
    public void testGetAllApplications() {
        Client client = new Client();
        client.setFullName("Иван Иванов");

        CreditApplication app1 = new CreditApplication();
        app1.setClient(client);
        app1.setStatus("approved");

        CreditApplication app2 = new CreditApplication();
        app2.setClient(client);
        app2.setStatus("rejected");

        List<CreditApplication> applications = Arrays.asList(app1, app2);
        when(creditApplicationRepository.findAll()).thenReturn(applications);

        List<CreditApplication> result = creditApplicationService.getAllApplications();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("approved", result.get(0).getStatus());
        assertEquals("rejected", result.get(1).getStatus());
        verify(creditApplicationRepository, times(1)).findAll();
    }
}