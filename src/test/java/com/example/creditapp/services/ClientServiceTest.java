package com.example.creditapp.services;

import com.example.creditapp.dao.ClientRepository;
import com.example.creditapp.models.Client;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService;

    @Test
    public void testSaveClient() {
        Client client = new Client();
        client.setFullName("Иван Иванов");
        client.setPassport("1234 567890");
        client.setPhone("+79991234567");
        client.setResidentialAddress("г. Москва, ул. Ленина, д. 1");
        client.setCreditHistoryRating(75);

        when(clientRepository.save(any(Client.class))).thenReturn(client);

        Client savedClient = clientService.saveClient(client);

        assertNotNull(savedClient);
        assertEquals("Иван Иванов", savedClient.getFullName());
        assertEquals("1234 567890", savedClient.getPassport());
        assertEquals("+79991234567", savedClient.getPhone());
        assertEquals("г. Москва, ул. Ленина, д. 1", savedClient.getResidentialAddress());
        assertEquals(75, savedClient.getCreditHistoryRating());
        verify(clientRepository, times(1)).save(client);
    }

    @Test
    public void testGetAllClients() {
        Client client1 = new Client();
        client1.setFullName("Иван Иванов");
        client1.setCreditHistoryRating(75);

        Client client2 = new Client();
        client2.setFullName("Петр Петров");
        client2.setCreditHistoryRating(90);

        List<Client> clients = Arrays.asList(client1, client2);
        when(clientRepository.findAll()).thenReturn(clients);

        List<Client> result = clientService.getAllClients();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Иван Иванов", result.get(0).getFullName());
        assertEquals(75, result.get(0).getCreditHistoryRating());
        assertEquals("Петр Петров", result.get(1).getFullName());
        assertEquals(90, result.get(1).getCreditHistoryRating());
        verify(clientRepository, times(1)).findAll();
    }

    @Test
    public void testSearchClients() {
        Client client = new Client();
        client.setFullName("Иван Иванов");
        client.setPassport("1234 567890");
        client.setPhone("+79991234567");

        List<Client> clients = List.of(client);
        when(clientRepository.searchClients("Иван")).thenReturn(clients);

        List<Client> result = clientService.searchClients("Иван");

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Иван Иванов", result.get(0).getFullName());
        verify(clientRepository, times(1)).searchClients("Иван");
    }
}