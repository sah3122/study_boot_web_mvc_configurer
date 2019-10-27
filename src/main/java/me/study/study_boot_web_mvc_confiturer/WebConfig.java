package me.study.study_boot_web_mvc_confiturer;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
//    Spring Boot 에서는 Formatter를 따로 등록하지 않고 Bean만 등록되어 있어도 된다.
//    @Override
//    public void addFormatters(FormatterRegistry registry) {
//        registry.addFormatter(new PersonFormatter());
//    }
}
