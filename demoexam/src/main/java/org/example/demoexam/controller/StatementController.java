package org.example.demoexam.controller;

import lombok.RequiredArgsConstructor;
import org.example.demoexam.model.Statement;
import org.example.demoexam.model.User;
import org.example.demoexam.service.StatementService;
import org.example.demoexam.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class StatementController {

    private final StatementService statementService;
    private final UserService userService;

    @GetMapping("/statements")
    public String myStatementsPage(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        User user = userService.getByLogin(userDetails.getUsername());
        model.addAttribute("statements", user.getStatements());
        return "statements";
    }

    @GetMapping("/statements/add")
    public String addStatementPage() {
        return "add_statement";
    }

    @PostMapping("/statements/add")
    public String addStatement(@AuthenticationPrincipal UserDetails userDetails, Statement statement) {
        User user = userService.getByLogin(userDetails.getUsername());
        statementService.save(statement, user);
        return "redirect:/statements";
    }

}
