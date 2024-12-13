package vn.edu.iuh.Lab05.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.edu.iuh.Lab05.backend.models.Company;
import vn.edu.iuh.Lab05.backend.models.Job;


public interface JobRepository extends JpaRepository<Job, Long> {
        List<Job> findByCompany(Company company);

}
