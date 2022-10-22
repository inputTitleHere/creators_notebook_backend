package com.ith.notebook.filter;

import com.ith.notebook.common.TokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
  @Autowired
  private TokenProvider tokenProvider;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    try {
      String token = parseBearerToken(request);
      log.debug("JWT Token filter is running");
      log.debug("TOKEN : {}",token);
      if (token != null && !token.equalsIgnoreCase("null")) {
        String userId = tokenProvider.validateAndGetId(token); // JWT이므로 인가 서버에 요청 안해도 됨.
        log.debug("Authenticated user ID : {}", userId); // 인증완료된 상태.
        AbstractAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(userId, null, AuthorityUtils.NO_AUTHORITIES);
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        securityContext.setAuthentication(authentication);
        SecurityContextHolder.setContext(securityContext);
      }
    } catch (Exception e) {
      log.error("Could not set user authentication in security context", e);
    }
    filterChain.doFilter(request, response);
  }

  private String parseBearerToken(HttpServletRequest request) {
//    Cookie[] cookies = request.getCookies();
//    for(Cookie c : cookies) if("token".equalsIgnoreCase(c.getName())) return c.getValue();
//    return null;

    String bearerToken = request.getHeader("Authorization");
    if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
      return bearerToken.substring(7);
    }
    return null;
  }

}
