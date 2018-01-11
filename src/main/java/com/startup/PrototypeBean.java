package com.startup;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.seda.SedaEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by proy on 26/07/15.
 */
@Component
@Scope("prototype")
public class PrototypeBean {

    @Autowired
    public ProducerTemplate producerTemplate;

    public static int count = 0;

    PrototypeBean(){
        count++;
    }

    public void start(){
        try {
            int i=(int)(Math.random()*51);
            producerTemplate.sendBody("seda:test" + i, "HI" + count);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
