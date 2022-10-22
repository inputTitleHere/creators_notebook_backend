package com.ith.notebook.member.model.repository;


import com.ith.notebook.member.model.dto.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {
    public List<Member> findAll();

    Member findById(String id);
    Boolean existsById(String id);


}
