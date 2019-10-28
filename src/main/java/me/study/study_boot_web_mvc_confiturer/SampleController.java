package me.study.study_boot_web_mvc_confiturer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    @GetMapping("/hello")
    // 등록한 formatter는 request param에서도 동작한다.
    //JPA 사용시 SpringBoot 에서 ID 관련된 Converter를 자동으로 등록해주어서 사용 가능.
    public String hello(@RequestParam("id") Person person){
        return "hello " + person.getName();
    }
}
