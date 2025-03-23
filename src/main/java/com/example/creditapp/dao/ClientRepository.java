package com.example.creditapp.dao;

import com.example.creditapp.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query("SELECT c FROM Client c WHERE c.fullName LIKE %:keyword% OR c.passport LIKE %:keyword% OR c.phone LIKE %:keyword%")
    List<Client> searchClients(String keyword);
}