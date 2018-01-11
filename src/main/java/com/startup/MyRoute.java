package com.startup;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.log4j.spi.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by proy on 24/07/15.
 */
@Component
public class MyRoute extends RouteBuilder {
    private Logger logger = org.slf4j.LoggerFactory.getLogger(MyRoute.class);
    @Override
    public void configure() throws Exception {
        for(int i=0; i<50; i++){
            from("seda:test"+i + "?blockWhenFull=true").startupOrder(i)
                    .log(LoggingLevel.INFO, logger, "("+i+")+   -> ${body}")
                    .setBody().simple("ref:helloWorld");
        }
    }
}
