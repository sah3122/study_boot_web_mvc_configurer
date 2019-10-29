package me.study.study_boot_web_mvc_confiturer;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.CacheControl;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Configuration
public class WebConfig implements WebMvcConfigurer {
//    Spring Boot 에서는 Formatter를 따로 등록하지 않고 Bean만 등록되어 있어도 된다.
//    @Override
//    public void addFormatters(FormatterRegistry registry) {
//        registry.addFormatter(new PersonFormatter());
//    }

    // resource handler 는 기본적으로 스프링 부트에서 제공해준다.
    // resource handler 는 우선순위가 가장 낮다
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/mobile/**")
                .addResourceLocations("classpath:/mobile/") // classpath를 주지 않으면 main/java 하위 webapp 폴더하위에서 찾게 된다.
                .setCacheControl(CacheControl.maxAge(10, TimeUnit.MINUTES))
                .resourceChain(true);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new GreettingInterceptor()).order(0);
        registry.addInterceptor(new AnotherInterceptor())
                .addPathPatterns("/hi/**").order(-1);
    }

    /**
     * HTTP 메시지 컨버터
     * 요청 본문에서 메시지를 읽어 들이거나 (RequestBody) 응답 본문에 메시지를 작성할 때 ResponseBody 사용
     * 새로운 메시지 컨버터를 사용할 경우 의존성을 추가하는 방법으로 컨버터를 등록할것.
     */
    @Override
    // 해당 메소드를 이용해서 컨버터를 추가하면 기본 컨버터를 사용 할 수 없다. extends를 사용할것.
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

    }
}
