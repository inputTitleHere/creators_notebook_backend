package com.ith.notebook.member.controller;

import com.ith.notebook.common.TokenProvider;
import com.ith.notebook.common.dto.ResponseDTO;
import com.ith.notebook.member.model.dto.Member;
import com.ith.notebook.member.model.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequestMapping("/member")
@RestController
@SuppressWarnings(value = "unused")
public class MemberController {
    @Autowired
    private TokenProvider tokenProvider;
    @Autowired
    private MemberService memberService;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @GetMapping("/test")
    public ResponseEntity<?> dbConnectTest(){
        List<Member> m = memberService.getMemberTest();
        log.debug("test member = {}",m);
        return ResponseEntity.ok(m);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Member member, HttpServletResponse response){
        log.debug("member : {}",member);
        Member memberFound = memberService.findById(member.getId());
        log.debug("memberFound = {}",memberFound);
        if(memberFound!=null && passwordEncoder.matches(member.getPassword(), memberFound.getPassword())) {
            log.debug("아이디와 비번이 일치합니다.");
            final String token = tokenProvider.create(member);
            Map<String, Object> res = new HashMap<>();
            res.put("token",token);

            Member responseMember =
                    Member.builder()
                    .id(memberFound.getId())
                    .name(memberFound.getName())
                    .tier(memberFound.getTier())
                    .build();

            res.put("member",responseMember);
            return ResponseEntity.ok(res);
        }else{
            return ResponseEntity.ok(ResponseDTO.builder().error("아이디 또는 비밀번호가 틀렸습니다.").build());
        }
    }

    /**
     * - id 중복검증 실행 메소드 <br>
     * - ResponseEntity : 사용가능한 아이디 = usable, 사용불가능한 아이디 = used
     *
     */
    @GetMapping("/checkUsableId")
    public ResponseEntity<?> checkUsableId(@RequestBody String id){
        Member member = memberService.findById(id);
        if(member==null) return ResponseEntity.ok("usable");
        else return ResponseEntity.ok("used");
    }

    /**
     * 회원가입 기능을 수행하는 register 메소드. <br>
     * 단, 아이디 중복검증과 같은 것은 위의 checkUsableId으로 확인하도록 하며, 비밀번호 조건 검사, 이메일 검사 등은 별개로 실행한다. <br>
     * 비밀번호 암호화를 수행한다.
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Member member){
        log.debug("New Member Register = {}",member);

        try{
            member.setPassword(passwordEncoder.encode(member.getPassword()));
            Member returnedMember = memberService.create(member);
            log.debug("신규 회원가입 : {}",returnedMember);
        }catch(Exception e){
            ResponseDTO<?> responseDTO = ResponseDTO.builder().error(e.getMessage()).build();
            return ResponseEntity.badRequest().body(responseDTO);
        }
        return ResponseEntity.ok(null);
    }

}
