package com.example.creditapp.models;

import jakarta.persistence.*;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "credit_applications")
public class CreditApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @NotNull(message = "Желаемая сумма обязательна")
    @DecimalMin(value = "10000.00", message = "Желаемая сумма должна быть больше 10.000")
    @DecimalMax(value = "10000000.00", message = "Желаемая сумма не может превышать 10.000.000")
    @Column(name = "desired_amount")
    private BigDecimal desiredAmount;

    @Column(name = "application_date")
    private LocalDate applicationDate;

    @Column(name = "status")
    private String status;

    public CreditApplication() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public BigDecimal getDesiredAmount() {
        return desiredAmount;
    }

    public void setDesiredAmount(BigDecimal desiredAmount) {
        this.desiredAmount = desiredAmount;
    }

    public LocalDate getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(LocalDate applicationDate) {
        this.applicationDate = applicationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
