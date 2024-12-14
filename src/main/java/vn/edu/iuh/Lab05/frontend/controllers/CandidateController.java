package vn.edu.iuh.Lab05.frontend.controllers;

import com.neovisionaries.i18n.CountryCode;

import jakarta.servlet.http.HttpSession;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import vn.edu.iuh.Lab05.backend.models.*;
import vn.edu.iuh.Lab05.backend.repositories.AddressRepository;
import vn.edu.iuh.Lab05.backend.repositories.CandidateRepository;
import vn.edu.iuh.Lab05.backend.repositories.JobRepository;
import vn.edu.iuh.Lab05.backend.services.CandidateServices;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/candidates")
public class CandidateController {
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private CandidateServices candidateServices;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private JobRepository jobRepository;

    @GetMapping("/list")
    public String showCandidateList(Model model) {
        model.addAttribute("candidates", candidateRepository.findAll());
        return "candidates/list_no_paging";
    }
    

    @GetMapping("")
    public String showCandidateListPaging(Model model,
                                          @RequestParam("page") Optional<Integer> page,
                                          @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);
        /*Page<Candidate> candidatePage= candidateServices.findPaginated(
                PageRequest.of(currentPage - 1, pageSize)
        );*/
        Page<Candidate> candidatePage = candidateServices.findAll(currentPage - 1,
                pageSize, "id", "asc");

        model.addAttribute("candidatePage", candidatePage);

        int totalPages = candidatePage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "candidates/list";
    }

    @GetMapping("/show-add-form")
    public ModelAndView add(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        Candidate candidate = new Candidate();
        candidate.setAddress(new Address());
        modelAndView.addObject("candidate", candidate);
        modelAndView.addObject("address", candidate.getAddress());
        modelAndView.addObject("countries", CountryCode.values());
        modelAndView.setViewName("candidates/add");
        return modelAndView;
    }

    @PostMapping("/add")
    public String addCandidate(
            @ModelAttribute("candidate") Candidate candidate,
            @ModelAttribute("address") Address address,
            BindingResult result, Model model) {
        addressRepository.save(address);
        candidate.setAddress(address);
        candidateRepository.save(candidate);
        return "redirect:/candidates";
    }

    @GetMapping("/show-edit-form/{id}")
    public ModelAndView edit(@PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView();
        Optional<Candidate> opt = candidateRepository.findById(id);
        if (opt.isPresent()) {
            Candidate candidate = opt.get();
            modelAndView.addObject("candidate", candidate);
            modelAndView.addObject("address", candidate.getAddress());
            modelAndView.addObject("countries", CountryCode.values());
            modelAndView.setViewName("candidates/update");
        }
        return modelAndView;
    }

    @PostMapping("/update")
    public String update(
            @ModelAttribute("candidate") Candidate candidate,
            @ModelAttribute("address") Address address,
            BindingResult result, Model model) {
        addressRepository.save(address);
        candidateRepository.save(candidate);
        return "redirect:/candidates";
    }

    @GetMapping("/delete/{id}")
    public String delete(
            @PathVariable("id") long id
    ) {
        Optional<Candidate> opt = candidateRepository.findById(id);
        if (opt.isPresent()) {
            Candidate candidate = opt.get();
            candidate.setStatus(-1);
            candidateRepository.save(candidate);
        }
        return "redirect:/candidates";
    }

    @GetMapping("/dashboard")
    public String showDashboard(Model model, HttpSession session) {
        Account loggedInUser = (Account) session.getAttribute("loggedInUser");
        if (loggedInUser != null && "CANDIDATE".equals(loggedInUser.getRole())) {
            Candidate candidate = candidateRepository.findByAccount(loggedInUser);
            model.addAttribute("candidate", candidate);
            model.addAttribute("skills", candidate.getCandidateSkills());

            // Gợi ý các công việc có skill phù hợp với ứng viên
            List<Skill> candidateSkills = candidate.getCandidateSkills().stream()
                    .map(CandidateSkill::getSkill)
                    .collect(Collectors.toList());
            List<Job> suggestedJobs = jobRepository.findByJobSkillsSkillIn(candidateSkills);
            model.addAttribute("suggestedJobs", suggestedJobs);

            // Lấy tất cả các công việc
            List<Job> allJobs = jobRepository.findAll();
            model.addAttribute("allJobs", allJobs);

            // Đề xuất các kỹ năng mà ứng viên chưa có
            Set<Skill> allSkills = allJobs.stream()
                    .flatMap(job -> job.getJobSkills().stream())
                    .map(JobSkill::getSkill)
                    .collect(Collectors.toSet());
            Set<Skill> candidateSkillSet = candidate.getCandidateSkills().stream()
                    .map(CandidateSkill::getSkill)
                    .collect(Collectors.toSet());
            allSkills.removeAll(candidateSkillSet);
            model.addAttribute("suggestedSkills", allSkills);

            return "candidates/dashboard";
        }
        return "redirect:/login";
    }

    @GetMapping("/search-jobs")
    public String searchJobs(@RequestParam String keyword, Model model, HttpSession session) {
        Account loggedInUser = (Account) session.getAttribute("loggedInUser");
        if (loggedInUser != null && "CANDIDATE".equals(loggedInUser.getRole())) {
            List<Job> searchResults = jobRepository.findByNameContainingOrDescriptionContaining(keyword, keyword);
            model.addAttribute("searchResults", searchResults);
            model.addAttribute("keyword", keyword);
            return "candidates/search-results";
        }
        return "redirect:/login";
    }
}
