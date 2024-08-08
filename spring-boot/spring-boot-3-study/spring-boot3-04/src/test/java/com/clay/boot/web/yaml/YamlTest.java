package com.clay.boot.web.yaml;

import com.clay.boot.web.domain.Person;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;

/**
 * @author clay
 */
public class YamlTest {

    public static void main(String[] args) throws JsonProcessingException {
        Person person = new Person();
        person.setId(1L);
        person.setAge(18);
        person.setUserName("张三");
        person.setEmail("aaa@qq.com");

        YAMLFactory factory = new YAMLFactory().disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER);
        ObjectMapper mapper = new ObjectMapper(factory);
        String s = mapper.writeValueAsString(person);
        System.out.println(s);
    }

}
