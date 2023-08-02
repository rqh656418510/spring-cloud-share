package com.clay.boot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @author clay
 */
@Profile({"prod", "test"})
@Configuration
public class SecurityConfiguration {

}
