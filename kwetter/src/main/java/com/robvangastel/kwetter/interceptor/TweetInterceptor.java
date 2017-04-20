package com.robvangastel.kwetter.interceptor;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

/**
 * Created by Rob on 7-4-2017.
 */
public class TweetInterceptor {

    @AroundInvoke
    public Object aroundInvoke(InvocationContext context) throws Exception {
        Object[] parameters = context.getParameters();
        String message = (String) parameters[0];
        message = message.replaceAll("shit", "kak");
        message = message.replaceAll("fuck", "potverdorie");
        parameters[0] = message;
        context.setParameters(parameters);
        return context.proceed();
    }
}
