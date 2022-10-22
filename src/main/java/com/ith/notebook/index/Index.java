package com.ith.notebook.index;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/")
@Slf4j
@RestController
public class Index {

    @GetMapping("/")
    public void Index(){
        // 생각해보니 React으로 프런트 구성하면 index는 필요없잖아
        log.debug("메인페이지 로드함.");
    }

}
