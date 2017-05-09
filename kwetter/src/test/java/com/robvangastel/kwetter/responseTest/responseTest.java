package com.robvangastel.kwetter.responseTest;

import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.lessThan;

/**
 * Created by robvangastel on 12/04/2017.
 */
public class responseTest {

    private static String baseUrl = "http://localhost:9080/kwetter/api";

    @Test
    public void responsetime_timeline() {

        when().
            get("api/user/{id}/tweets", 2).
        then().
            statusCode(200).
        and().
            time(lessThan(100L));

    }
    //
    @Test
    public void responsetime_sendTweet() {

        given().
            header("Authorization", "Basic YWRtaW46cGFzc3dvcmQ=").
            param("message", "response time tweet").
        when().
            post("http://localhost:9080/kwetter/api/tweet").
        then().
            statusCode(200).
        and().
            time(lessThan(100L));
    }

    @Test
    public void responsetime_getUser() {

        when().
            get("api/user/{id}", 2).
        then().
            statusCode(200).
        and().
            time(lessThan(100L));
    }

    @Test
    public void stress_timeline() {

        for(int i = 0; i < 500; i++) {
            when().
                get("api/user/{id}/tweets", 2).
            then().
                statusCode(200).
            and().
                time(lessThan(500L));
        }

    }

    @Test
    public void stress_sendTweet() {

        for(int i = 0; i < 500; i++) {
            given().
                header("Authorization", "Basic YWRtaW46cGFzc3dvcmQ=").
                param("message", "response time tweet").
            when().
                post("http://localhost:9080/kwetter/api/tweet").
            then().
                statusCode(200).
            and().
                time(lessThan(500L));
        }
    }

    @Test
    public void stress_getUser() {

        for(int i = 0; i < 500; i++) {
            when().
                get("api/user/{id}", 2).
            then().
                statusCode(200).
            and().
                time(lessThan(500L));
        }
    }
}
