package com.clay.boot.web.domain;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

@Data
@JacksonXmlRootElement
public class Person {

    private Long id;
    private String userName;
    private String email;
    private Integer age;

}