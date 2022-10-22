package com.ith.notebook.member.model.service;

import com.ith.notebook.member.model.dto.Member;

import java.util.List;

public interface MemberService {
    List<Member> getMemberTest();

    Member findById(String id);

    Member create(Member member);
}
