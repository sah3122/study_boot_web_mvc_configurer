package me.study.study_boot_web_mvc_confiturer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    @GetMapping("/hello/{name}")
    // 등록한 formatter는 request param에서도 동작한다.
    public String hello(@PathVariable("name") Person person){
        return "hello " + person.getName();
    }
}
