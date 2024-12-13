package vn.edu.iuh.Lab05.backend.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

public enum SkillLevel {
    MASTER(5),
    PROFESSIONAL(4),
    ADVANCED(3),
    INTERMEDIATE(2),
    BEGINNER(1);

    private final int value;

    SkillLevel(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static SkillLevel fromValue(int value) {
        for (SkillLevel level : SkillLevel.values()) {
            if (level.value == value) {
                return level;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }

    @Converter(autoApply = true)
    public static class SkillLevelConverter implements AttributeConverter<SkillLevel, Integer> {
        @Override
        public Integer convertToDatabaseColumn(SkillLevel attribute) {
            return attribute != null ? attribute.getValue() : null;
        }

        @Override
        public SkillLevel convertToEntityAttribute(Integer dbData) {
            return dbData != null ? SkillLevel.fromValue(dbData) : null;
        }
    }
}