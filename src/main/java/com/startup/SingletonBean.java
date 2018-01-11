package com.startup;

import org.springframework.stereotype.Component;

/**
 * Created by proy on 26/07/15.
 */
@Component
public class SingletonBean {
    public static int count=0;

    SingletonBean(){
        count++;
    }
}
