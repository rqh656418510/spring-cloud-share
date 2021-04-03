package com.clay.shop.swagger;

import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;

import java.lang.annotation.*;

/**
 * @author clay
 * @version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import(BeanValidatorPluginsConfiguration.class)
public @interface EnableBeanValidator {

}