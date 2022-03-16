package com.spring.security;

import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.web.embedded.tomcat.ConfigurableTomcatWebServerFactory;
import org.springframework.boot.web.server.Ssl;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;

// 自定义TLS(传输层安全性)协议的实现
public class MyTlsConfigurer implements WebServerFactoryCustomizer<ConfigurableTomcatWebServerFactory> {

    private final ServerProperties serverProperties;

    public MyTlsConfigurer(ServerProperties serverProperties) {
        this.serverProperties = serverProperties;
    }

    @Override
    public void customize(ConfigurableTomcatWebServerFactory factory) {
        // 从ServerProperties中获取SSL
        final Ssl ssl = this.serverProperties.getSsl();
        if (null != ssl) {
            boolean changed = false;
            if ((null != ssl.getKeyStore()) && !ssl.isEnabled()) {
                ssl.setEnabled(true);
                changed = true;
            }
            if (null != ssl.getTrustStore() && null == ssl.getClientAuth()) {
                ssl.setClientAuth(Ssl.ClientAuth.NEED);
                changed = true;
            }
            if (changed) {
                factory.setSsl(ssl);
            }
        }
    }
}
