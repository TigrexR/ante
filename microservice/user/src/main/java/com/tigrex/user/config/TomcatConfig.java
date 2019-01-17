package com.tigrex.user.config;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TomcatConfig {

    @Bean(name = "tomcatServletWebServer")
    public ServletWebServerFactory tomcatServletWebServer(){
        TomcatServletWebServerFactory tomcatServletWebServerFactory = new TomcatServletWebServerFactory();
//        tomcatServletWebServerFactory.setProtocol("org.apache.coyote.http11.Http11NioProtocol");
        tomcatServletWebServerFactory.setProtocol("org.apache.coyote.http11.Http11Nio2Protocol");
//        tomcatServletWebServerFactory.setProtocol("org.apache.coyote.http11.Http11AprProtocol");
        return tomcatServletWebServerFactory;
    }

}
