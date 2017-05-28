package com.robvangastel.kwetter;

import com.robvangastel.kwetter.service.TweetService;
import com.robvangastel.kwetter.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.robvangastel.kwetter.bean.kwetterBean;
import com.robvangastel.kwetter.service.AuthService;

@Configuration
@ComponentScan({ "com.robvangastel.kwetter" })
public class Application {
	
	public static void main(String[] args) {
    	AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Application.class);
    	new kwetterBean(ctx.getBean(UserService.class), ctx.getBean(TweetService.class), ctx.getBean(AuthService.class));
        ctx.registerShutdownHook();
    }
}
