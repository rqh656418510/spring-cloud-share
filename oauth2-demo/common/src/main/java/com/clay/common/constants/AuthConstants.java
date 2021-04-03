package com.clay.common.constants;

/**
 * @author clay
 * @version 1.0
 */
public class AuthConstants {

    /**
     * User Token
     */
    public static final String USER_TOKEN_KEY = "user_token";

    /**
     * User Detail
     */
    public static final String USER_PRINCIPAL_KEY = "user_principal";

    /**
     * User Permission
     */
    public static final String USER_AUTHORITIES_KEY = "user_authorities";

    /**
     * Web Application Index
     */
    public static final String WEB_APPLICATION_INDEX = "/";

    /**
     * Swagger2 API
     */
    public static final String API_SWAGGER2 = "/v2/api-docs";

    /**
     * Spring Boot Actuator API
     */
    public static final String API_SPRING_BOOT_ACTUATOR = "/actuator/**";

    /**
     * Spring Boot Admin Server Instances API
     */
    public static final String API_SPRING_BOOT_ADMIN_SERVER_INSTANCES = "/instances/**";

    /**
     * Spring Boot Admin Server Applications API
     */
    public static final String API_SPRING_BOOT_ADMIN_SERVER_APPLICATIONS = "/applications/**";

}
