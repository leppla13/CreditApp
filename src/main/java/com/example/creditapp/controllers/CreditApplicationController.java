package com.example.creditapp.controllers;

import com.example.creditapp.models.Client;
import com.example.creditapp.models.CreditApplication;
import com.example.creditapp.services.ClientService;
import com.example.creditapp.services.CreditApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/credits")
public class CreditApplicationController {

    @Autowired
    private CreditApplicationService creditApplicationService;

    @Autowired
    private ClientService clientService;

    @GetMapping
    public String listApplications(Model model) {
        List<CreditApplication> applications = creditApplicationService.getAllApplications();
        model.addAttribute("applications", applications);
        model.addAttribute("view", "credits/list");
        return "layout";
    }

    @GetMapping("/approved")
    public String listApprovedApplications(Model model) {
        List<CreditApplication> applications = creditApplicationService.getApprovedApplications();
        model.addAttribute("applications", applications);
        model.addAttribute("view", "credits/list");
        return "layout";
    }

    @GetMapping("/new")
    public String newApplicationForm(Model model) {
        CreditApplication application = new CreditApplication();
        model.addAttribute("application", application);
        model.addAttribute("clients", clientService.getAllClients());
        model.addAttribute("view", "credits/form");
        return "layout";
    }

    @PostMapping
    public String saveApplication(@RequestParam("client") Long clientId, @RequestParam("desiredAmount") BigDecimal desiredAmount) {
        Client client = clientService.getAllClients().stream()
                .filter(c -> c.getId().equals(clientId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Клиент не найден"));

        CreditApplication application = new CreditApplication();
        application.setClient(client);
        application.setDesiredAmount(desiredAmount);

        creditApplicationService.saveApplication(application);

        return "redirect:/credits";
    }
}