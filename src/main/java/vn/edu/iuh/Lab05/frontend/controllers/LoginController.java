package vn.edu.iuh.Lab05.frontend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import vn.edu.iuh.Lab05.backend.models.Account;
import vn.edu.iuh.Lab05.backend.models.Company;
import vn.edu.iuh.Lab05.backend.models.Job;
import vn.edu.iuh.Lab05.backend.repositories.AccountRepository;
import vn.edu.iuh.Lab05.backend.repositories.CompanyRepository;
import vn.edu.iuh.Lab05.backend.repositories.JobRepository;

import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private JobRepository jobRepository;

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(String username, String password, Model model) {
        Account account = accountRepository.findByUsername(username);
        if (account != null && account.getPassword().equals(password)) {
            if ("COMPANY".equals(account.getRole())) {
                Company company = companyRepository.findByEmail(account.getUsername());
                List<Job> jobs = jobRepository.findByCompany(company);
                model.addAttribute("company", company);
                model.addAttribute("jobs", jobs);
                return "companies/dashboard";
            }
        }
        model.addAttribute("error", "Invalid username or password");
        return "login";
    }

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        // Assuming the company is already authenticated and available in the session
        Account account = accountRepository.findByUsername("company");
        if (account != null) {
            Company company = companyRepository.findByEmail(account.getUsername());
            List<Job> jobs = jobRepository.findByCompany(company);
            model.addAttribute("company", company);
            model.addAttribute("jobs", jobs);
        }
        return "companies/dashboard";
    }
}