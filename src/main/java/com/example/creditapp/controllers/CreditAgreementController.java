package com.example.creditapp.controllers;

import com.example.creditapp.models.CreditAgreement;
import com.example.creditapp.services.CreditAgreementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/agreements")
public class CreditAgreementController {

    @Autowired
    private CreditAgreementService creditAgreementService;

    @GetMapping
    public String listAgreements(Model model) {
        List<CreditAgreement> agreements = creditAgreementService.getAllAgreements();
        model.addAttribute("agreements", agreements);
        model.addAttribute("view", "agreements/list");
        return "layout";
    }

    @GetMapping("/signed")
    public String listSignedAgreements(Model model) {
        List<CreditAgreement> agreements = creditAgreementService.getSignedAgreements();
        model.addAttribute("agreements", agreements);
        model.addAttribute("view", "agreements/list");
        return "layout";
    }

    @PostMapping("/sign/{id}")
    public String signAgreement(@PathVariable("id") Long id) {
        creditAgreementService.signAgreement(id);
        return "redirect:/agreements";
    }
}