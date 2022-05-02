package com.manage.project.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "server")
public class DataConfig {
    private String port;
    private String envconfig;

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getEnvconfig() {
        return envconfig;
    }

    public void setEnvconfig(String envconfig) {
        this.envconfig = envconfig;
    }
}