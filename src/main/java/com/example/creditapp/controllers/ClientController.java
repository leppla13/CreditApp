package com.example.creditapp.controllers;

import com.example.creditapp.models.Client;
import com.example.creditapp.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public String listClients(Model model) {
        List<Client> clients = clientService.getAllClients();
        model.addAttribute("clients", clients);
        model.addAttribute("view", "clients/list");
        return "layout";
    }

    @GetMapping("/search")
    public String searchClients(@RequestParam("keyword") String keyword, Model model) {
        List<Client> clients = clientService.searchClients(keyword);
        model.addAttribute("clients", clients);
        model.addAttribute("view", "clients/list");
        return "layout";
    }

    @GetMapping("/new")
    public String newClientForm(Model model) {
        Client client = new Client();
        model.addAttribute("client", client);
        model.addAttribute("view", "clients/form");
        return "layout";
    }

    @PostMapping
    public String saveClient(@Valid @ModelAttribute("client") Client client, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("client", client);
            model.addAttribute("view", "clients/form");
            return "layout";
        }
        clientService.saveClient(client);
        return "redirect:/clients";
    }
}
