package com.ith.notebook.member.model.dto;

import javax.persistence.AttributeConverter;

public class MemberTierConverter implements AttributeConverter<MemberTier,String> {

    @Override
    public String convertToDatabaseColumn(MemberTier memberTier) {
        return memberTier.toString();
    }

    @Override
    public MemberTier convertToEntityAttribute(String string) {
        return MemberTier.valueOf(string);
    }
}
