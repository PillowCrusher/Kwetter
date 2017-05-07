//package com.robvangastel.kwetter.responseTest;
//
//import org.junit.Test;
//
//import static io.restassured.RestAssured.given;
//import static io.restassured.RestAssured.when;
//import static org.hamcrest.Matchers.lessThan;
//
///**
// * Created by robvangastel on 12/04/2017.
// */
//public class responseTest {
//
//    private static String baseUrl = "http://localhost:9080/kwetter/api";
//
//    @Test
//    public void responsetime_timeline() {
//
//        when().
//            get("http://localhost:9080/kwetter/api/user").
//        then().
//            statusCode(200).
//        and().
//            time(lessThan(100L));
//
//    }
//    //
//    @Test
//    public void responsetime_sendTweet() {
//
//        given().
//            header("Authorization", "Basic YWRtaW46cGFzc3dvcmQ=").
//        when().
//            post("http://localhost:9080/kwetter/api/tweet?message=rwwetwe").
//        then().
//            statusCode(200).
//        and().
//            time(lessThan(100L));
//    }
//
//    @Test
//    public void responsetime_getUser() {
//
//        when().
//            get("http://localhost:9080/kwetter/api/user/{id}", 2).
//        then().
//            statusCode(200).
//        and().
//            time(lessThan(100L));
//    }
//}
