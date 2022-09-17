package br.edu.infnet.moviesapi.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@Data
@ConfigurationProperties(prefix = "omdb.api")
public class OmdbProperties {
    public String host;
    public String key;
}
