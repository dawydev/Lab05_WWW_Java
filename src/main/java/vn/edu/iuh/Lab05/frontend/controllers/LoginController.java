package vn.edu.iuh.Lab05.frontend.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import vn.edu.iuh.Lab05.backend.models.Account;
import vn.edu.iuh.Lab05.backend.repositories.AccountRepository;


@Controller
public class LoginController {

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(String username, String password, Model model, HttpSession session) {
        Account account = accountRepository.findByUsername(username);
        if (account != null && account.getPassword().equals(password)) {
            session.setAttribute("loggedInUser", account);
            if ("COMPANY".equals(account.getRole())) {
                return "redirect:/company/dashboard";
            } else if ("CANDIDATE".equals(account.getRole())) {
                return "redirect:/candidates/dashboard";
            }
        }
        model.addAttribute("error", "Invalid username or password");
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}