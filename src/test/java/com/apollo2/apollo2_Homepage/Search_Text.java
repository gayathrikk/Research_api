package com.apollo2.apollo2_Homepage;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Search_Text {
	
	  @Test(priority=1)
	  public void annotatedtile()
	  {
		    String graphQLBody = "{\n" +
		            "  \"query\": \"brain details\"\n" +
		            "}";
		    Response response5 = RestAssured
		            .given()
		                .auth()
		                .preemptive()
		                .basic("admin", "admin")
		                .contentType(ContentType.JSON) // Assuming the server accepts JSON
		                .body(graphQLBody) // GraphQL query
		            .when()
		                .post("https://apollo2.humanbrain.in/chat/qa_chain");
		    int statusCode5 = response5.getStatusCode();
		    if (statusCode5 == 200) {
		        System.out.println("API request to Research answer passed. Status code: " + statusCode5);
		        // Print response body
		        String responseBody = response5.getBody().asString();
		        System.out.println("Response body: " + responseBody);
		    } else {
		        System.out.println("API request to Research answer failed. Status code: " + statusCode5);
		    }
		    Assert.assertEquals(statusCode5, 200, "API request to Research answer failed");
		}


}
