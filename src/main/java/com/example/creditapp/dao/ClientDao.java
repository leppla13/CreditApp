package com.example.creditapp.dao;

import com.example.creditapp.models.Client;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClientDao {

    @Autowired
    private SessionFactory sessionFactory;

    public List<Client> getAllClients() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Client", Client.class).list();
        }
    }

    public Client saveClient(Client client) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.saveOrUpdate(client);
            session.getTransaction().commit();
            return client;
        }
    }

    public List<Client> searchClients(String keyword) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "from Client c where lower(c.fullName) like lower(:keyword) or c.passport like :keyword or c.phone like :keyword";
            Query<Client> query = session.createQuery(hql, Client.class);
            query.setParameter("keyword", "%" + keyword.toLowerCase() + "%");
            return query.list();
        }
    }
}