package vn.edu.iuh.Lab05.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.iuh.Lab05.backend.models.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    Company findByEmail(String email);
}