package vn.edu.iuh.Lab05.frontend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import vn.edu.iuh.Lab05.backend.models.Company;
import vn.edu.iuh.Lab05.backend.models.Job;
import vn.edu.iuh.Lab05.backend.repositories.CompanyRepository;
import vn.edu.iuh.Lab05.backend.repositories.JobRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class CompanyController {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private JobRepository jobRepository;

    @GetMapping("dashboard")
    public String showDashboard(Model model) {
        List<Company> companies = companyRepository.findAll();
        Map<Company, List<Job>> companyJobsMap = companies.stream()
                .collect(Collectors.toMap(company -> company, company -> jobRepository.findByCompany(company)));
        model.addAttribute("companyJobsMap", companyJobsMap);
        return "company/dashboard";
    }
}