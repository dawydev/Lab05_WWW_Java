package vn.edu.iuh.Lab05.backend.ids;

import vn.edu.iuh.Lab05.backend.models.Job;
import vn.edu.iuh.Lab05.backend.models.Skill;

import java.util.Objects;

public class CandidateSkillID {
    private Job job;
    private Skill skill;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        CandidateSkillID that = (CandidateSkillID) o;

        if (!skill.equals(that.skill)) return false;
        return job.equals(that.job);
    }

    @Override
    public int hashCode() {
        int result = skill.hashCode();
        result = 31 * result + job.hashCode();
        return result;
    }
}
