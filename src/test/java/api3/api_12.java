package api3;

import static io.restassured.RestAssured.given;

import org.json.simple.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class api_12 {
	@Test(enabled = true)
	public void createUser(ITestContext val)
	{
		RestAssured.baseURI="https://petstore.swagger.io/v2";
		JSONObject obj = new JSONObject();
		obj.put("username", "KingArthur");
		obj.put("firstName", "King");
		obj.put("lastName", "Arthur");
		obj.put("email", "king@arthur.com");
		obj.put("password", "king1234");
		obj.put("phone", "9876543210");
		
		String u_name="KingArthur";
		
		
		
		given()
		.contentType(ContentType.JSON)
		.body(obj.toJSONString()).
	when()
		.post("/user").
	then()
		.statusCode(200)
		.log()
		.all();
		
		val.setAttribute("username", u_name);
	}
	
	@Test(enabled = true, dependsOnMethods="createUser")
	public void login()
	{
		RestAssured.baseURI="https://petstore.swagger.io/v2";
		given().
		when()
			.queryParam("username","KingArthur")
			.queryParam("password","king1234")
			.get("/user/login").
		then()
			.statusCode(200)
			.log()
			.all();
	}
	
	@Test(enabled = true, dependsOnMethods= "login")
	public void edit(ITestContext val)
	{
		RestAssured.baseURI="https://petstore.swagger.io/v2";
		JSONObject obj = new JSONObject();
		obj.put("username", "OptimusPrime");
		obj.put("firstName", "Optimus");
		obj.put("lastName", "Prime");
		obj.put("email", "optimus@prime.com");
		obj.put("password", "prime1234");
		obj.put("phone", "9876543210");
		
		String u_name="OptimusPrime";
		
		
		given()
		.contentType(ContentType.JSON)
		.body(obj.toJSONString()).
	when()
		.put("/user/"+val.getAttribute("username")).
	then()
		.statusCode(200)
		.log()
		.all();
		val.setAttribute("username", u_name);
		
	}
	
	@Test(enabled = true, dependsOnMethods= "edit")
	public void logout()
	{
		RestAssured.baseURI="https://petstore.swagger.io/v2";
		given().
		when()
			.get("/user/logout").
		then()
			.statusCode(200)
			.log()
			.all();
	}
	
	@Test(enabled = true, dependsOnMethods="logout")
	public void delete(ITestContext val)
	{
		RestAssured.baseURI="https://petstore.swagger.io/v2";
		

		
		given().
		when()
		.delete("/user/"+val.getAttribute("username").toString()).
	then()
		.statusCode(200)
		.log()
		.all();
		

	}
	

}
