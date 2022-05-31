package com.noway.cardPrintRequestDemo.framework.applicationProperties;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("application")
@Getter
@Setter
public class AppProperties {
    private String type;
}
