package com.clay.boot.web.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/**
 * @author clay
 */
public class YamlHttpMessageConverter extends AbstractHttpMessageConverter<Object> {

    private final ObjectMapper objectMapper;

    public YamlHttpMessageConverter() {
        // 指定支持的媒体类型
        super(new MediaType("text", "yaml", StandardCharsets.UTF_8));
        // 初始化YAML工具
        YAMLFactory yamlFactory = new YAMLFactory().disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER);
        this.objectMapper = new ObjectMapper(yamlFactory);
    }

    /**
     * 支持的类
     */
    @Override
    protected boolean supports(Class<?> clazz) {
        // TODO 只处理对象类型，不处理基本类型（如 int）
        return true;
    }

    /**
     * 处理方法参数(@RequestBody)
     */
    @Override
    protected Object readInternal(Class<?> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }

    /**
     * 处理方法返回值(@ResponseBody)
     */
    @Override
    protected void writeInternal(Object returnValue, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        try (OutputStream outputStream = outputMessage.getBody()) {
            this.objectMapper.writeValue(outputStream, returnValue);
        }
    }

}
