package me.study.study_boot_web_mvc_confiturer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.oxm.Marshaller;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;

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

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    Marshaller marshaller;

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

    @Test
    public void stringMessage() throws Exception {
        this.mockMvc.perform(get("/message").content("hello"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("hello"));
    }

    @Test
    public void jsonMessage() throws Exception {
        Person person = new Person();
        person.setId(0L);
        person.setName("dong");

        String jsonString = objectMapper.writeValueAsString(person);

        this.mockMvc.perform(get("/jsonMessage")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonString))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(0))
                .andExpect(jsonPath("$.name").value("dong"));
    }

    @Test
    public void xmlMessage() throws Exception {
        Person person = new Person();
        person.setId(0L);
        person.setName("dong");

        StringWriter stringWriter = new StringWriter();
        Result result = new StreamResult(stringWriter);
        marshaller.marshal(person, result);
        String xmlString = stringWriter.toString();


        this.mockMvc.perform(get("/jsonMessage")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(xmlString))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(xpath("person/name").string("dong"))
                .andExpect(xpath("person/id").string("0"));
    }
}