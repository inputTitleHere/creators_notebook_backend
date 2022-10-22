package com.ith.notebook.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/test")
public class Test {

  // AuthenticationPrincipal으로 id를 가져올 수 있다. -> 아마 JwtAuthenticationFilter에서 등록해줬을 것.
  @GetMapping("/test")
  public ResponseEntity tester(@AuthenticationPrincipal String id){
    log.debug("id : {}",id);
    return ResponseEntity.ok("hahaha TEST pass");
  }


}
