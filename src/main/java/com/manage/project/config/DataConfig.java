package com.manage.project.config;

//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

//@Setter
//@Getter
//@NoArgsConstructor
//@AllArgsConstructor
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