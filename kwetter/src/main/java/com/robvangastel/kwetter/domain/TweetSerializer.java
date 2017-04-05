package com.robvangastel.kwetter.domain;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

/**
 * Created by Rob on 5-4-2017.
 */

public class TweetSerializer extends StdSerializer<Tweet> {

    public TweetSerializer() {
        this(null);
    }

    public TweetSerializer(Class<Tweet> t) {
        super(t);
    }

    @Override
    public void serialize(
            Tweet value, JsonGenerator jgen, SerializerProvider provider)
            throws IOException, JsonProcessingException {

        jgen.writeStartObject();
        jgen.writeNumberField("id", value.getId());
        jgen.writeNumberField("user_id", value.getUser().getId());
        jgen.writeStringField("message", value.getMessage());
        jgen.writeStringField("timestamp", value.getTimeStamp().toString());
        jgen.writeStringField("username", value.getUser().getUsername());
        jgen.writeStringField("email", value.getUser().getEmail());
        jgen.writeEndObject();
    }
}