package vn.edu.iuh.Lab05.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.iuh.fit.backend.models.JobSkill;
import vn.edu.iuh.fit.backend.models.Skill;

public interface JobSkillRepository extends JpaRepository<JobSkill, Skill> {
}