package org.example.demoexam.controller;

import lombok.RequiredArgsConstructor;
import org.example.demoexam.service.StatementService;
import org.example.demoexam.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController {

    private final StatementService statementService;
    private final UserService userService;

    @GetMapping("/admin")
    public String adminPage(Model model) {
        model.addAttribute("statements", statementService.getAll());
        return "admin";
    }

    @GetMapping("/delete")
    public String deleteStatement(@RequestParam Long id){
        statementService.delete(id);
        return "redirect:/admin";
    }

    @PostMapping("/reject")
    public String reject(Long id) {
        statementService.reject(id);
        return "redirect:/admin";
    }

    @PostMapping("/confirm")
    public String confirm(Long id) {
        statementService.confirm(id);
        return "redirect:/admin";
    }



}
