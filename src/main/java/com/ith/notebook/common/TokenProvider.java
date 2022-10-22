package com.ith.notebook.common;

import com.ith.notebook.member.model.dto.Member;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Slf4j
@Service
public class TokenProvider {
    // 아래 SECRET_KEY를 해싱한 이후의 길이가 256비트를 넘어야 하나보다. 길이를 키우자.
    private static final String SECRET_KEY = "Idon22fFavA1NyB23LAJFJ@23#Gai!daklf;j2-0fj!!fjd921Ah44dea293DFs4";
    private static final Key KEY = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));

    public String create(Member member){
        Date expiryDate = Date.from(Instant.now().plus(1, ChronoUnit.DAYS));

        return Jwts.builder()
                .signWith(KEY,SignatureAlgorithm.HS512)
                .setSubject(member.getId())
                .setIssuer("Creator's Notebook")
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .compact();
    }

    public String validateAndGetId(String token){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

}
