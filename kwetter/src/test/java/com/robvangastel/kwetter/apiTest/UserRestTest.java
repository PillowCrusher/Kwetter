package com.robvangastel.kwetter.apiTest;

import com.robvangastel.kwetter.domain.User;
import com.robvangastel.kwetter.exception.ErrorMessage;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.client.*;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

/**
 * Created by robvangastel on 13/03/2017.
 */
public class UserRestTest {

	private static String baseUrl = "http://localhost:9080/kwetter/api";

	// @GET
	// @Path("/{id}")
	// public User getById(@PathParam("id") long id);
	// Case 1 - Test existing User
	@Test
	public void userGetTest1() throws Exception
	{
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(baseUrl + "/user/" + 1);
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
	// public User getById(@PathParam("id") long id)
	// Case 2 - Test non-existing User
	@Test
	public void userGetTest2() throws Exception
	{
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(baseUrl + "/user/" + 0);
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


	// @GET
	// public User getByEmail(@QueryParam("email") String email);
	// Case 1 - Test existing tweet
	@Test
	public void userGetByEmailTest1() throws Exception
	{
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(baseUrl + "/user/email/?email=user@mail.com");
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
	// public User getById(@PathParam("id") long id)
	// Case 2 - Test non-existing tweet expect
	@Test
	public void userGetByEmailTest2() throws Exception
	{
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(baseUrl + "/user/email/?email=fakeuser@mail.com");
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

	// @PUT
	// @Path("/{id}")
	// public void updateRole(@QueryParam("role") Role role, @PathParam("id") long id) throws Exception;
	// Case 1 - Authorized User changes role of user
	@Test
	public void userUpdateRoleTest1() throws Exception
	{
		String input = "";
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(baseUrl + "/user/1?role=ADMINISTRATOR");
		Invocation.Builder builder = target.request()
				.header(HttpHeaders.AUTHORIZATION, "Basic YWRtaW46cGFzc3dvcmQ=");

		Response response = builder.put(Entity.json(input));
		System.out.println(response.toString());

		try
		{
			Assert.assertEquals(204, response.getStatus());
		}
		finally
		{
			response.close();
			client.close();
		}
	}

	// @PUT
	// @Path("/{id}")
	// public void updateRole(@QueryParam("role") Role role, @PathParam("id") long id) throws Exception;
	// Case 2 - Unauthorized User changes role of user
	@Test
	public void userUpdateRoleTest2() throws Exception
	{
		String input = "";
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(baseUrl + "/user/1?role=ADMINISTRATOR");
		Invocation.Builder builder = target.request()
				// Empty Authorization header
				.header(HttpHeaders.AUTHORIZATION, "Basic Og==");

		Response response = builder.put(Entity.json(input));
		System.out.println(response.toString());

		try
		{
			Assert.assertEquals(401, response.getStatus());
		}
		finally
		{
			response.close();
			client.close();
		}
	}

	// @POST
	// public User post(@QueryParam("email") String email,
	//				 @QueryParam("username") String username,
	// 				 @QueryParam("password") String password) throws Exception;
	// Case 2 - non-existing email and username
	@Test
	public void userPost1Test() throws Exception {
		String input = "";
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(baseUrl + "/user/?email=user1@mail.com&username=user1&password=password");
		Invocation.Builder builder = target.request();

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
	// public User post(@QueryParam("email") String email,
	//				 @QueryParam("username") String username,
	// 				 @QueryParam("password") String password) throws Exception;
	// Case 2 - Existing email and username
	@Test
	public void userPost2Test() throws Exception {
		String input = "";
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(baseUrl + "/user/?email=user@mail.com&username=user&password=password");
		Invocation.Builder builder = target.request();

		Response response = builder.post(Entity.json(input));
		System.out.println(response.toString());

		try
		{
			Assert.assertEquals(500, response.getStatus());
		}
		finally
		{
			response.close();
			client.close();
		}
	}
}
