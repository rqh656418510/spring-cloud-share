package com.springcloud.study.config;

import com.netflix.zuul.FilterFileManager;
import com.netflix.zuul.FilterLoader;
import com.netflix.zuul.groovy.GroovyCompiler;
import com.netflix.zuul.groovy.GroovyFileFilter;
import com.netflix.zuul.monitoring.MonitoringHelper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class GroovyRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        MonitoringHelper.initMocks();
        FilterLoader.getInstance().setCompiler(new GroovyCompiler());
        try{
            FilterFileManager.setFilenameFilter(new GroovyFileFilter());
            FilterFileManager.init(20, "/tmp/groovy");
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}
