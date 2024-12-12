package vn.edu.iuh.Lab05;

import com.neovisionaries.i18n.CountryCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import vn.edu.iuh.Lab05.backend.models.Address;
import vn.edu.iuh.Lab05.backend.models.Candidate;
import vn.edu.iuh.Lab05.backend.repositories.AddressRepository;
import vn.edu.iuh.Lab05.backend.repositories.CandidateRepository;

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

	//	@Bean
	@Bean
	CommandLineRunner initData() {
		return args -> {
			Random rnd = new Random();
			for (int i = 1; i <= 500; i++) {
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
		};
	}

}
