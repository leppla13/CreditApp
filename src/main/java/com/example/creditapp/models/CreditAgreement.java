package com.example.creditapp.models;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "credit_agreements")
public class CreditAgreement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "application_id", nullable = false)
    private CreditApplication application;

    @Column(name = "approved_amount")
    private BigDecimal approvedAmount;

    @Column(name = "term_days")
    private Integer termDays;

    @Column(name = "signing_date")
    private LocalDate signingDate;

    @Column(name = "signing_status")
    private String signingStatus;

    public CreditAgreement() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CreditApplication getApplication() {
        return application;
    }

    public void setApplication(CreditApplication application) {
        this.application = application;
    }

    public BigDecimal getApprovedAmount() {
        return approvedAmount;
    }

    public void setApprovedAmount(BigDecimal approvedAmount) {
        this.approvedAmount = approvedAmount;
    }

    public Integer getTermDays() {
        return termDays;
    }

    public void setTermDays(Integer termDays) {
        this.termDays = termDays;
    }

    public LocalDate getSigningDate() {
        return signingDate;
    }

    public void setSigningDate(LocalDate signingDate) {
        this.signingDate = signingDate;
    }

    public String getSigningStatus() {
        return signingStatus;
    }

    public void setSigningStatus(String signingStatus) {
        this.signingStatus = signingStatus;
    }
}