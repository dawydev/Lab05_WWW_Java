package vn.edu.iuh.Lab05;

import com.neovisionaries.i18n.CountryCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import vn.edu.iuh.Lab05.backend.models.Account;
import vn.edu.iuh.Lab05.backend.models.Address;
import vn.edu.iuh.Lab05.backend.models.Candidate;
import vn.edu.iuh.Lab05.backend.models.Company;
import vn.edu.iuh.Lab05.backend.models.Job;
import vn.edu.iuh.Lab05.backend.repositories.AddressRepository;
import vn.edu.iuh.Lab05.backend.repositories.CandidateRepository;
import vn.edu.iuh.Lab05.backend.repositories.AccountRepository;
import vn.edu.iuh.Lab05.backend.repositories.CompanyRepository;
import vn.edu.iuh.Lab05.backend.repositories.JobRepository;

import java.time.LocalDate;
import java.util.Random;

@SpringBootApplication
public class LabWeek05Application {

	public static void main(String[] args) {
		SpringApplication.run(LabWeek05Application.class, args);
	}

}