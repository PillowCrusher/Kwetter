package com.robvangastel.kwetter.apiTest;

import com.robvangastel.kwetter.domain.Tweet;
import com.robvangastel.kwetter.domain.User;
import com.robvangastel.kwetter.exception.ErrorMessage;
import com.robvangastel.kwetter.service.TweetService;
import com.robvangastel.kwetter.service.UserService;
import org.glassfish.jersey.client.ClientResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.QueryParam;
import javax.ws.rs.client.*;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

/**
 * Created by robvangastel on 12/03/2017.
 */
public class TweetRestTest {

	private static String baseUrl = "http://localhost:9080/kwetter/api";

	@Before
	public void setUp() {
		String input = "";

		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(baseUrl + "/tweet/?message=hello");

		Invocation.Builder builder = target.request()
				.header(HttpHeaders.AUTHORIZATION, "Basic YWRtaW46cGFzc3dvcmQ=");

		Response response = builder.post(Entity.json(input));
		System.out.println(response.toString());

		try {

		} finally {
			response.close();
			client.close();
		}
	}

	// @POST
	// public Tweet post(@QueryParam("message") String message) throws Exception;
	// Case 1 - Create a tweet with existing User authentication
	@Test
	public void tweetPostTest1() throws Exception {
		String input = "";

		Client client = ClientBuilder.newClient();
		WebTarget target = client.target( baseUrl + "/tweet/?message=hello");

		Invocation.Builder builder = target.request()
				.header(HttpHeaders.AUTHORIZATION, "Basic YWRtaW46cGFzc3dvcmQ=");

		Response response = builder.post(Entity.json(input));
		System.out.println(response.toString());

		try
		{
			Assert.assertEquals(200, response.getStatus());
		}
		finally
		{
			response.close();
			client.close();
		}
	}

	// @POST
	// public Tweet post(@QueryParam("message") String message) throws Exception;
	// Case 2 - Create a tweet without existing User authentication
	@Test
	public void tweetPostTest2() throws Exception {
		String input = "";

		Client client = ClientBuilder.newClient();
		WebTarget target = client.target( baseUrl + "/tweet/?message=hello");

		Invocation.Builder builder = target.request()
				 // Empty Authorization header
				 .header(HttpHeaders.AUTHORIZATION, "Basic Og==");

		Response response = builder.post(Entity.json(input));
		System.out.println(response.toString());

		try
		{
			// Unauthorized 401 code
			Assert.assertEquals(401, response.getStatus());
		}
		finally
		{
			response.close();
			client.close();
		}
	}

	 // @GET
	 // @Path("/{id}")
	 // public Tweet getById(@PathParam("id") long id);
	 // Case 1 - Test existing tweet
	@Test
	public void tweetGetTest1() throws Exception
	{
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(baseUrl + "/tweet/" + 4);
		Response response = target.request().get();
		System.out.println(response.toString());
		try
		{
			Assert.assertEquals(200, response.getStatus());
		}
		finally
		{
			response.close();
			client.close();
		}
	}

	// @GET
	// @Path("/{id}")
	// public Tweet getById(@PathParam("id") long id)
	// Case 2 - Test non-existing tweet expect
	@Test
	public void tweetGetTest2() throws Exception
	{
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(baseUrl + "/tweet/" + 0);
		Response response = target.request().get();
		System.out.println(response.toString());
		try
		{
			Assert.assertEquals(500, response.getStatus());
			ErrorMessage message = response.readEntity(ErrorMessage.class);
			Assert.assertEquals("javax.ws.rs.WebApplicationException: HTTP 404 Not Found", message.getMessage());
		}
		finally
		{
			response.close();
			client.close();
		}
	}
}
