package com.example.creditapp.config;

import com.example.creditapp.models.Client;
import com.example.creditapp.models.CreditAgreement;
import com.example.creditapp.models.CreditApplication;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class HibernateConfig {

    @Bean
    public SessionFactory sessionFactory() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Client.class);
        configuration.addAnnotatedClass(CreditAgreement.class);
        configuration.addAnnotatedClass(CreditApplication.class);
        configuration.configure("hibernate.cfg.xml");
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        return configuration.buildSessionFactory(serviceRegistry);
    }
}
