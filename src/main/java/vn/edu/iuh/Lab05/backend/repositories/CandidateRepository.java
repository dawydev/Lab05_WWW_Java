package vn.edu.iuh.Lab05.backend.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import vn.edu.iuh.Lab05.backend.models.Account;
import vn.edu.iuh.Lab05.backend.models.Candidate;
import vn.edu.iuh.Lab05.backend.models.Skill;

//@RepositoryRestResource(collectionResourceRel = "candidates", path = "candidates")
//public interface CandidateRepository extends PagingAndSortingRepository<Candidate, Long> {
public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    Page<Candidate> findAllByStatus(int status, Pageable pageable);
    Candidate findByAccount(Account account);
    List<Candidate> findByCandidateSkillsSkillIn(List<Skill> skills);

}
