package br.edu.infnet.moviesbattle.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration(proxyBeanMethods = false)
@Data
@ConfigurationProperties(prefix = "movies.api")
public class MoviesApiProperties {
    public String host;
}
