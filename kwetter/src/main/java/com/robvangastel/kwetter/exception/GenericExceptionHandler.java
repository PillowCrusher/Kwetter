package com.robvangastel.kwetter.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by robvangastel on 15/03/2017.
 */

@Provider
public class GenericExceptionHandler implements ExceptionMapper<Throwable>
{
    @Override
    public Response toResponse(Throwable throwable) {
        ErrorMessage errorMessage = new ErrorMessage();
        setHttpStatus(throwable, errorMessage);
        errorMessage.setMessage(throwable.getMessage());
        StringWriter errorStackTrace = new StringWriter();
        throwable.printStackTrace(new PrintWriter(errorStackTrace));
        errorMessage.setStacktrace(errorStackTrace.toString());

        return Response.status(errorMessage.getStatus())
                .entity(errorMessage)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    private void setHttpStatus(Throwable throwable, ErrorMessage errorMessage) {
        if(throwable instanceof TweetException || throwable instanceof UserException ) {
            errorMessage.setStatus(Response.Status.BAD_REQUEST.getStatusCode());
        } else {
            errorMessage.setStatus(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
        }
    }
}