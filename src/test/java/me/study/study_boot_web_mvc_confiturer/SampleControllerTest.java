package me.study.study_boot_web_mvc_confiturer;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
//@WebMvcTest // WEB과 관련된 Bean만 등록해준다. component로 등록한Bean은 자동으로 등록 되지 않음.
@SpringBootTest
@AutoConfigureMockMvc
public class SampleControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    PersonRepository personRepository;
    @Test
    public void hello() throws Exception {
        //given
        Person person = new Person();
        person.setName("dong");

        Person save = personRepository.save(person);
        //JPA 사용시 SpringBoot 에서 ID 관련된 Converter를 자동으로 등록해주어서 사용 가능.
        this.mockMvc.perform(get("/hello").param("id", save.getId().toString()))
                .andDo(print())
                .andExpect(content().string("hello"));

        //when

        //then
    }

    @Test
    public void helloStatic() throws Exception {
        //given
        this.mockMvc.perform(get("/index.html"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString("hello index")));

        //when

        //then
    }

    @Test
    public void helloMobile() throws Exception {
        //given
        this.mockMvc.perform(get("/mobile/index.html"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString("hello mobile")))
                .andExpect(header().exists(HttpHeaders.CACHE_CONTROL));

        //when

        //then
    }

}