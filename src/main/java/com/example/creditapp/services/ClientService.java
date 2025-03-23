package com.example.creditapp.services;

import com.example.creditapp.models.Client;
import com.example.creditapp.dao.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }

    public List<Client> searchClients(String keyword) {
        return clientRepository.searchClients(keyword);
    }
}