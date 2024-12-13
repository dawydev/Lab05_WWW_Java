package vn.edu.iuh.Lab05.backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.edu.iuh.Lab05.backend.enums.SkillType;

import java.util.List;

@Entity
@Table(name = "skill")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "skill_id")
    private long id;

    @Column(name = "skill_name", nullable = false, length = 150)
    private String skillName;

    @Convert(converter = SkillType.SkillTypeConverter.class)
    @Column(name = "skill_type", nullable = false)
    private SkillType type;

    @Column(name = "skill_desc", nullable = false, length = 300)
    private String skillDescription;

    @OneToMany(mappedBy = "skill")
    private List<JobSkill> jobSkills;
}