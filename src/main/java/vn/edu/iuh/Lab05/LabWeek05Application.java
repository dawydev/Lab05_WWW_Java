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

	@Autowired
	private CandidateRepository candidateRepository;
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private CompanyRepository companyRepository;
	@Autowired
	private JobRepository jobRepository;

	@Bean
	CommandLineRunner initData(AccountRepository accountRepository) {
		return args -> {
			Random rnd = new Random();
			for (int i = 1; i <= 20; i++) {
				Address add = new Address(rnd.nextInt(1, 1000) + "", "Quang Trung", "HCM",
						rnd.nextInt(70000, 80000) + "", CountryCode.VN);
				addressRepository.save(add);

				Candidate can = new Candidate("Name #" + i,
						LocalDate.of(1998, rnd.nextInt(1, 13), rnd.nextInt(1, 29)),
						add,
						rnd.nextLong(1111111111L, 9999999999L) + "",
						"email_" + i + "@gmail.com", 1);
				candidateRepository.save(can);
				System.out.println("Added: " + can);
			}

			// Add more data entries here
			Address add1 = new Address("123", "Le Loi", "Hanoi", "100000", CountryCode.VN);
			addressRepository.save(add1);

			Candidate can1 = new Candidate("John Doe",
					LocalDate.of(1990, 5, 15),
					add1,
					"0123456789",
					"john.doe@example.com", 1);
			candidateRepository.save(can1);
			System.out.println("Added: " + can1);

			Address add2 = new Address("456", "Nguyen Hue", "Da Nang", "550000", CountryCode.VN);
			addressRepository.save(add2);

			Candidate can2 = new Candidate("Jane Smith",
					LocalDate.of(1985, 8, 25),
					add2,
					"0987654321",
					"jane.smith@example.com", 1);
			candidateRepository.save(can2);
			System.out.println("Added: " + can2);

			Account companyAccount = new Account();
			companyAccount.setUsername("company");
			companyAccount.setPassword("company123");
			companyAccount.setRole("COMPANY");
			accountRepository.save(companyAccount);

			// Add company data
			Company company1 = new Company();
			company1.setName("Company A");
			company1.setEmail("contact@companya.com");
			company1.setPhone("0123456789");
			company1.setAbout("Leading software development company");
			company1.setWebURL("https://www.companya.com");
			company1.setAddress(add1);
			companyRepository.save(company1);

			Company company2 = new Company();
			company2.setName("Company B");
			company2.setEmail("contact@companyb.com");
			company2.setPhone("0987654321");
			company2.setAbout("Innovative data solutions provider");
			company2.setWebURL("https://www.companyb.com");
			company2.setAddress(add2);
			companyRepository.save(company2);

			// Add job data
			Job job1 = new Job();
			job1.setName("Software Engineer");
			job1.setDescription("Develop and maintain software applications.");
			job1.setCompany(company1);
			jobRepository.save(job1);

			Job job2 = new Job();
			job2.setName("Data Scientist");
			job2.setDescription("Analyze and interpret complex data.");
			job2.setCompany(company1);
			jobRepository.save(job2);

			Job job3 = new Job();
			job3.setName("Project Manager");
			job3.setDescription("Manage and oversee projects.");
			job3.setCompany(company2);
			jobRepository.save(job3);

			Job job4 = new Job();
			job4.setName("UX Designer");
			job4.setDescription("Design user-friendly interfaces.");
			job4.setCompany(company2);
			jobRepository.save(job4);
		};
	}
}