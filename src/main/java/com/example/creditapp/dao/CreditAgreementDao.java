package com.example.creditapp.dao;

import com.example.creditapp.models.CreditAgreement;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CreditAgreementDao {

    @Autowired
    private SessionFactory sessionFactory;

    public List<CreditAgreement> getAllAgreements() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from CreditAgreement", CreditAgreement.class).list();
        }
    }

    public CreditAgreement getAgreementById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(CreditAgreement.class, id);
        }
    }

    public void updateAgreement(CreditAgreement agreement) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(agreement);
            session.getTransaction().commit();
        }
    }

    public CreditAgreement saveAgreement(CreditAgreement agreement) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.saveOrUpdate(agreement);
            session.getTransaction().commit();
            return agreement;
        }
    }

    public List<CreditAgreement> getSignedAgreements() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from CreditAgreement where signingStatus = 'signed'", CreditAgreement.class).list();
        }
    }
}