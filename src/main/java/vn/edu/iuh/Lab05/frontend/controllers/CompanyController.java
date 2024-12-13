package vn.edu.iuh.Lab05.frontend.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import vn.edu.iuh.Lab05.backend.models.Account;
import vn.edu.iuh.Lab05.backend.models.Company;
import vn.edu.iuh.Lab05.backend.models.Job;
import vn.edu.iuh.Lab05.backend.repositories.CompanyRepository;
import vn.edu.iuh.Lab05.backend.repositories.JobRepository;

import java.util.List;

@Controller
public class CompanyController {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private JobRepository jobRepository;

    @GetMapping("/company/dashboard")
    public String showDashboard(Model model, HttpSession session) {
        Account loggedInUser = (Account) session.getAttribute("loggedInUser");
        if (loggedInUser != null && "COMPANY".equals(loggedInUser.getRole())) {
            Company company = companyRepository.findByAccount(loggedInUser);
            List<Job> jobs = jobRepository.findByCompany(company);
            model.addAttribute("company", company);
            model.addAttribute("jobs", jobs);
            return "company/dashboard";
        }
        return "redirect:/login";
    }
}