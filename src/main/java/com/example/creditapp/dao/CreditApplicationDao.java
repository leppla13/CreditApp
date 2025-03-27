package com.example.creditapp.dao;

import com.example.creditapp.models.CreditApplication;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CreditApplicationDao {

    @Autowired
    private SessionFactory sessionFactory;

    public List<CreditApplication> getAllApplications() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from CreditApplication", CreditApplication.class).list();
        }
    }

    public CreditApplication saveApplication(CreditApplication application) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.saveOrUpdate(application);
            session.getTransaction().commit();
            return application;
        }
    }

    public List<CreditApplication> getApprovedApplications() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from CreditApplication where status = 'approved'", CreditApplication.class).list();
        }
    }
}