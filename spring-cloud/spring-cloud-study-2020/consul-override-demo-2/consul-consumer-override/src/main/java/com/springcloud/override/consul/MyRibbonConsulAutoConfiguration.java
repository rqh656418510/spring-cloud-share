package com.springcloud.override.consul;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.cloud.consul.ConditionalOnConsulEnabled;
import org.springframework.cloud.netflix.ribbon.RibbonAutoConfiguration;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.context.annotation.Configuration;

/**
 * 该类主要是将原有入口取代, 因此它的生效逻辑刚好跟 RibbonConsulAutoConfiguration 相反
 * 当 spring.cloud.consul.ribbon.enabled 为 false 时, 这里重写的逻辑生效
 */
@Configuration
@ConditionalOnConsulEnabled
@ConditionalOnBean(SpringClientFactory.class)
@AutoConfigureAfter(RibbonAutoConfiguration.class)
@ConditionalOnExpression("${spring.cloud.consul.ribbon.enabled:true}==false")
@RibbonClients(defaultConfiguration = MyConsulRibbonClientConfiguration.class)
public class MyRibbonConsulAutoConfiguration {

}
