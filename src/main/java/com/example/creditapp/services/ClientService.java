package com.example.creditapp.services;

import com.example.creditapp.dao.ClientDao;
import com.example.creditapp.models.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientDao clientDao;

    public List<Client> getAllClients() {
        return clientDao.getAllClients();
    }

    public Client saveClient(Client client) {
        return clientDao.saveClient(client);
    }

    public List<Client> searchClients(String keyword) {
        return clientDao.searchClients(keyword);
    }
}