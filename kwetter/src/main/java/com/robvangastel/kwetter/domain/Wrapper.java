package com.robvangastel.kwetter.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

/**
 * Created by Rob on 6-4-2017.
 */

@JsonAutoDetect
public class Wrapper {
    private transient User data; // transient makes Jackson ignore this

    public long id() { return data.getId();}
    public String role() { return data.getRole().toString(); }
    public String email() { return data.getEmail(); }
    public String username() { return data.getUsername(); }
    public String location() { return data.getLocation(); }
    public String websiteUrl() { return data.getWebsiteUrl(); }
    public String bio() { return data.getBio(); }
}