package com.antkorwin.xsyncexamples.config;

import com.antkorwin.xsync.XSync;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

/**
 * Created on 20.06.2018.
 *
 * @author Korovin Anatoliy
 */
@Configuration
public class XSyncConfig {

    @Bean
    public XSync<UUID> idXSync(){
        return new XSync<>();
    }

    @Bean
    public XSync<Integer> intXSync(){
        return new XSync<>();
    }
}
