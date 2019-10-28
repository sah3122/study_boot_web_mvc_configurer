package me.study.study_boot_web_mvc_confiturer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    // handler interceptor
    // preHandle
    // 요청 처리
    // postHandle
    // 뷰 렌더링
    // afterCompletion
    // 비동기 요청시엔 post, after는 실행되지 않고 해당 메소드는 역순 호출

    /**
     * interceptor
     * 실행순서
     * preHandle 1
     * preHandle 2
     * 요청 처리
     * postHandle 2
     * postHandle 1
     * 뷰렌더링
     * afterCompletion 2
     * afterCompletion 1
     *
     * handler intercepotor vs servlet filter
     * 서블릿보다 구체적인 처리가 가능하다.
     * 서블릿은 보다 일반적인 용도의 기능을 구현하는데 사용해야한다.
     */

    @GetMapping("/hello")
    // 등록한 formatter는 request param에서도 동작한다.
    //JPA 사용시 SpringBoot 에서 ID 관련된 Converter를 자동으로 등록해주어서 사용 가능.
    public String hello(@RequestParam("id") Person person){
        return "hello " + person.getName();
    }
}
