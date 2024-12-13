package vn.edu.iuh.Lab05.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.iuh.fit.backend.models.Job;


public interface JobRepository extends JpaRepository<Job, Long> {
}
