package com.robvangastel.kwetter;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.robvangastel.kwetter.configuration.WebConfig;
import com.robvangastel.kwetter.service.KwetterService;

@Configuration
@ComponentScan({ "com.robvangastel.kwetter" })
public class Application {
	
	public static void main(String[] args) {
    	AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Application.class);
    	new WebConfig(ctx.getBean(KwetterService.class));
        ctx.registerShutdownHook();
    }
}
