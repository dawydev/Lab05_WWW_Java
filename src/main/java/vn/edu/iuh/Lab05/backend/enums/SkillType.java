package vn.edu.iuh.Lab05.backend.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

public enum SkillType {
    UNSPECIFIC(0),
    TECHNICAL_SKILL(1),
    SOFT_SKILL(2);

    private final int value;

    SkillType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static SkillType fromValue(int value) {
        for (SkillType type : SkillType.values()) {
            if (type.value == value) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }

    @Converter(autoApply = true)
    public static class SkillTypeConverter implements AttributeConverter<SkillType, Integer> {
        @Override
        public Integer convertToDatabaseColumn(SkillType attribute) {
            return attribute != null ? attribute.getValue() : null;
        }

        @Override
        public SkillType convertToEntityAttribute(Integer dbData) {
            return dbData != null ? SkillType.fromValue(dbData) : null;
        }
    }
}