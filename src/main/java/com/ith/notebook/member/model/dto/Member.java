package com.ith.notebook.member.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    // 회원 고유 번호
//    @Id
//    @GeneratedValue
//    @Column(name = "uuid")
//    private long uuid;
    // 로그인용 아이디
    @Id
    @Column(name = "id",unique = true, nullable = false)
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Column(name="joined_at")
    private LocalDate joinedAt;

    @Convert(converter = MemberTierConverter.class)
    @Column(name="tier")
    private MemberTier tier;


    @PrePersist
    private void onCreate(){
        joinedAt=LocalDate.now();
    }

}
