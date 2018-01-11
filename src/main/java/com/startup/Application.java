package com.startup;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.EndpointAutoConfiguration;
import org.springframework.boot.actuate.endpoint.mvc.EndpointHandlerMapping;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

/**
 * Created by proy on 7/07/15.
 */
@Configuration
@EnableAutoConfiguration
@Import(EndpointAutoConfiguration.class)
@SpringBootApplication
public class Application implements CommandLineRunner {
    @org.springframework.context.annotation.Bean
    public String helloWorld() {
        try {
            //Thread.sleep((int)((Math.random()*10000) + 1000));
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "helloWorld";
    }

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private CamelContext camelContext;

    @Override
    public void run(String... strings) throws Exception {
        for(int i=0; i<50; i++){
            new Thread(new Runnable(){
                @Override
                public void run() {
                    PrototypeBean prototypeBean = applicationContext.getBean(PrototypeBean.class);
                    while(true){
                        prototypeBean.start();

                    }
                }
            }).start();
        }

        while(true){
                PrototypeBean prototypeBean = applicationContext.getBean(PrototypeBean.class);
                prototypeBean.start();
            Thread.sleep(1000);
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
