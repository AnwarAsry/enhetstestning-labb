package org.iths.enhetstestninglabb.controller;

import org.iths.enhetstestninglabb.service.ATMService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/balance")
public class BalanceController {
    private final ATMService atmService;

    public BalanceController(ATMService atmService) {
        this.atmService = atmService;
    }

    @GetMapping()
    public String showBalance(Model model) {
        model.addAttribute("balance", atmService.getBalance());
        return "balance";
    }
}
