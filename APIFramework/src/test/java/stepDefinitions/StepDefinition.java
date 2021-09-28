package stepDefinitions;

import static io.restassured.RestAssured.given;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.utils;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;


public class StepDefinition extends utils {
	
	//Declare them as public here
	
	ResponseSpecification resSpec;
	RequestSpecification responseString;
	Response res;
	static String placeid;

	
	
	TestDataBuild t=new TestDataBuild();
	
	@Given("AddPlace payload with {string}	{string}	{string}")
	public void addplace_payload_with(String name, String language, String address) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
	   
	responseString=given().spec(requestSppecification()) //pass the method here
		.body(t.addPlacePayload(name,language,address)); //payload is taken from TestDataBuild
	    
	}

	@When("user calls {string} API with {string} HTTP request")
	public void user_calls_API_with_HTTP_request(String resource, String method) {
	   
		//take the response of the POST method into resSpec
		//call enum here
		//object of APIresources is created to access the values
		
		APIResources resourceapiname= APIResources.valueOf(resource); //constructor will be executed
		System.out.println(resourceapiname.getResource());
		
		resSpec=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		if(method.equalsIgnoreCase("POST"))
		
			//response captured from Addplace
		res=responseString.when().post(resourceapiname.getResource()); //add the object retrieved above
			 
		
		else if (method.equalsIgnoreCase("GET"))
			//response captured from Addplace
			
			res=responseString.when().get(resourceapiname.getResource()); //deriving from enum
		
		
	}
		

	
	@Then("the API call is success with status code {int}")
	public void the_API_call_is_success_with_status_code(Integer int1) {
	 
		
		assertEquals(res.getStatusCode(),200);
	  
	  
	}

	@Then("{string} response is {string}")
	public void response_is(String keyValue, String expectedValue) {
	
		//find ok from the below code as  key value pair
	   
	   assertEquals(getJsonPath(res, keyValue).toString(),expectedValue); //
	  
	   
	}
	
	@Then("verify place_id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
	    //prepare a req spec
		
		placeid=getJsonPath(res,"place_id");
		
		responseString=given().spec(requestSppecification()).queryParam("place_id", placeid);
		
		//invoke the API now with get resource
		
		user_calls_API_with_HTTP_request(resource, "GET"); //call the above method  which will execute for GET
		
		String actualName=getJsonPath(res,"name"); //JsonPath will return the name
		
		assertEquals(actualName,expectedName);
		
	}
//DeletePlace
	
	@Given("DeletePlace payload")
	public void deleteplace_payload() throws IOException {
	    
		//pick the place id created from addplace API
		responseString=	given().spec(requestSppecification()).body(t.deletePlacePayload(placeid));
		
	}



}
