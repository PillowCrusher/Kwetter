package com.robvangastel.kwetter.responseTest;

import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.lessThan;

/**
 * Created by robvangastel on 12/04/2017.
 */
public class stressTest {

    @Test
    public void stress_timeline() {

        for(int i = 0; i < 500; i++) {
            when().
                    get("http://localhost:9080/kwetter/api/user/2/tweets").
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
                    when().
                    post("http://localhost:9080/kwetter/api/tweet?message=rwwetwe").
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
                    get("http://localhost:9080/kwetter/api/user/{id}", 2).
                    then().
                    statusCode(200).
                    and().
                    time(lessThan(500L));
        }
    }
}
