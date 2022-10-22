package com.ith.notebook.member.model.service;

import com.ith.notebook.member.model.repository.MemberRepository;
import com.ith.notebook.member.model.dto.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.RollbackException;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional(rollbackOn = Exception.class)
public class MemberServiceImpl implements MemberService{

    @Autowired
    MemberRepository memberRepository;

    @Override
    public List<Member> getMemberTest() {
        return memberRepository.findAll();
    }

    @Override
    public Member findById(String id) {
        return memberRepository.findById(id);
    }

    @Override
    public Member create(Member member) {
        return memberRepository.save(member);
    }
}
