package samplePackage;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class TC002_Post_Resquest {
	
	@Test
	public void registration(){
		
		//Specify the Base URI
		RestAssured.baseURI="http://restapi.demoqa.com/customer";
		
		//Request Object
		RequestSpecification httpRequest=RestAssured.given();
		
		JSONObject requestParams=new JSONObject();
		
		requestParams.put("FirstName", "Mohan");
		requestParams.put("LastName", "Sinha");
		requestParams.put("UserName", "json2");
		requestParams.put("Password", "Test@123");
		requestParams.put("Email", "json2@email.com");
		
		httpRequest.header("Content-Type", "application/json");
		
		httpRequest.body(requestParams.toJSONString());
		
		//Response Object
		Response response=httpRequest.request(Method.POST, "/register");
		
		//Print Response in Console Window
		String responseBody=response.getBody().asString();
		System.out.println("Response Body is: "+ responseBody);
		
		//Status Code Validation
		int statusCode=response.getStatusCode();
		System.out.println("Status coed is: "+ statusCode);
		Assert.assertEquals(statusCode, 201);
		
		//Status Line Verification
		String successCode=response.jsonPath().get("SuccessCode");
		Assert.assertEquals(successCode, "OPERATION_SUCCESS");
		
	}

}
