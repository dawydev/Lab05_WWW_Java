package vn.edu.iuh.Lab05.frontend.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.edu.iuh.Lab05.backend.enums.SkillLevel;
import vn.edu.iuh.Lab05.backend.models.*;
import vn.edu.iuh.Lab05.backend.repositories.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CompanyController {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private SkillRepository skillRepository;
    @Autowired
    private CandidateRepository candidateRepository;

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

    @GetMapping("/company/post-job")
    public String postJobForm(Model model) {
        List<Skill> skills = skillRepository.findAll();
        model.addAttribute("skills", skills);
        model.addAttribute("skillLevels", SkillLevel.values());
        return "company/post-job";
    }

    @PostMapping("/company/post-job")
    public String postJob(@RequestParam String jobName, @RequestParam String jobDescription, @RequestParam List<Long> skillIds, @RequestParam List<String> skillLevels, HttpSession session) {
        Account loggedInUser = (Account) session.getAttribute("loggedInUser");
        if (loggedInUser != null && "COMPANY".equals(loggedInUser.getRole())) {
            Company company = companyRepository.findByAccount(loggedInUser);
            Job job = new Job();
            job.setName(jobName);
            job.setDescription(jobDescription);
            job.setCompany(company); // Đảm bảo rằng đối tượng Company được thiết lập

            List<JobSkill> jobSkills = new ArrayList<>();
            for (int i = 0; i < skillIds.size(); i++) {
                Skill skill = skillRepository.findById(skillIds.get(i)).orElse(null);
                if (skill != null) {
                    JobSkill jobSkill = new JobSkill();
                    jobSkill.setSkill(skill);
                    jobSkill.setJob(job);
                    jobSkill.setSkillLevel(SkillLevel.valueOf(skillLevels.get(i)));
                    jobSkills.add(jobSkill);
                }
            }
            job.setJobSkills(jobSkills);
            jobRepository.save(job);
            return "redirect:/company/dashboard";
        }
        return "redirect:/login";
    }

    @GetMapping("/company/find-candidates")
    public String findCandidates(@RequestParam Long jobId, Model model) {
        Job job = jobRepository.findById(jobId).orElse(null);
        if (job != null) {
            List<Skill> jobSkills = job.getJobSkills().stream().map(JobSkill::getSkill).collect(Collectors.toList());
            List<Candidate> suitableCandidates = candidateRepository.findByCandidateSkillsSkillIn(jobSkills);
            model.addAttribute("suitableCandidates", suitableCandidates);
            model.addAttribute("job", job);
            return "company/suitable-candidates";
        }
        return "redirect:/company/dashboard";
    }
}