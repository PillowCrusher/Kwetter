package com.robvangastel.kwetter.domain;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rob on 6-4-2017.
 */
public class UserSerializer extends StdSerializer<User> {

    public UserSerializer() {
        this(null);
    }

    public UserSerializer(Class<User> t) {
        super(t);
    }

    @Override
    public void serialize(
            User value, JsonGenerator jgen, SerializerProvider provider)
            throws IOException, JsonProcessingException {

        List<User> followers = value.getFollowers();
        List<User> following = value.getFollowing();

        jgen.writeStartObject();
        jgen.writeNumberField("id", value.getId());
        jgen.writeStringField("role", value.getRole().toString());
        jgen.writeStringField("email", value.getEmail());
        jgen.writeStringField("username", value.getUsername());
        jgen.writeStringField("location", value.getLocation());
        jgen.writeStringField("websiteUrl", value.getWebsiteUrl());
        jgen.writeStringField("bio", value.getBio());

        if(!followers.isEmpty()) {
            jgen.writeFieldName("followers");
            jgen.writeStartArray();

            for(User u : followers) {
                jgen.writeStartObject();
                jgen.writeNumberField("id", value.getId());
                jgen.writeStringField("role", value.getRole().toString());
                jgen.writeStringField("email", value.getEmail());
                jgen.writeStringField("username", value.getUsername());
                jgen.writeStringField("location", value.getLocation());
                jgen.writeStringField("websiteUrl", value.getWebsiteUrl());
                jgen.writeStringField("bio", value.getBio());
                jgen.writeEndObject();
            }

            jgen.writeEndArray();
        }

        if(!following.isEmpty()) {
            jgen.writeFieldName("following");
            jgen.writeStartArray();

            for(User u : following) {
                jgen.writeStartObject();
                jgen.writeNumberField("id", value.getId());
                jgen.writeStringField("role", value.getRole().toString());
                jgen.writeStringField("email", value.getEmail());
                jgen.writeStringField("username", value.getUsername());
                jgen.writeStringField("location", value.getLocation());
                jgen.writeStringField("websiteUrl", value.getWebsiteUrl());
                jgen.writeStringField("bio", value.getBio());
                jgen.writeEndObject();
            }

            jgen.writeEndArray();
        }
        jgen.writeEndObject();
    }
}