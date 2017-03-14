//package com.robvangastel.kwetter.apiTest;
//
//import com.robvangastel.kwetter.domain.User;
//import org.junit.Assert;
//import org.junit.Test;
//
//import javax.ws.rs.client.Client;
//import javax.ws.rs.client.ClientBuilder;
//import javax.ws.rs.client.WebTarget;
//import javax.ws.rs.core.Response;
//
///**
// * Created by robvangastel on 13/03/2017.
// */
//public class UserRestTest {
//
//	@Test
//	public void testResponse() throws Exception
//	{
//		long id = 0l;
//		// fill out a query param and execute a get request
//		Client client = ClientBuilder.newClient();
//		WebTarget target = client.target("http://localhost:9080/api/user/" + id);
//		Response response = target.request().get(); //queryParam("name", "Bill").
//		try
//		{
//			Assert.assertEquals(200, response.getStatus());
//			User user = response.readEntity(User.class);
//			Assert.assertEquals("Admin", user.getUsername());
//		}
//		finally
//		{
//			response.close();
//			client.close();
//		}
//	}
//}
