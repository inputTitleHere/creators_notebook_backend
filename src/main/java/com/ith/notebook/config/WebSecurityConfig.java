package com.ith.notebook.config;

import com.ith.notebook.filter.JwtAuthenticationFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.filter.CorsFilter;

/**
 * 책에서는 WebSecuriotyConfigurerAdapter를 extends해서 사용하라 하는데 이게 depricated 되었다.
 * https://blog.naver.com/PostView.naver?blogId=h850415&logNo=222755455272&parentCategoryNo=&categoryNo=37&viewDate=&isShowPopularPosts=true&from=search
 * 위 블로그글에서 보는 대로 SecurityFilterChain @bean 등록으로 해보겠다.
 */
@Slf4j
@EnableWebSecurity
public class WebSecurityConfig {
  @Autowired
  JwtAuthenticationFilter jwtAuthenticationFilter;
  /**
   * Web.xml대신에 HttpSecurity를 이용해 시큐리티 관련 설정을 진행.
   */
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
            .cors()
            .and()
            .csrf()
            .disable()
            .httpBasic()
            .disable()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests() // -> "/"와 "/member/**" 의 경로는 인증 안해도 됨을 명시
            .antMatchers("/","/member/**","/project/**").permitAll() // 그냥 콤마찍고 늘려도 될듯
            .anyRequest()
//            .permitAll();
            .authenticated(); // 그 외의 모든 주소에 대해 인증해야함
    // 필터 등록부, 매 요청마다, corsfilter이후, jwtAuthenticationFilter 실행
    http.addFilterBefore(jwtAuthenticationFilter,CorsFilter.class);
    return http.build();
  }

}
