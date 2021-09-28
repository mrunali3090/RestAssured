package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlace") //add the tag here to let this method below know run it before deleteplace
	
	public void beforeScenario() throws IOException
	{
		//write a code that will return placeid
		//also execute this code only when place id is null
		
		
		StepDefinition step=new StepDefinition();
		
		if(StepDefinition.placeid==null) //since place id is static and can be called directly by classname 
		{
		step.addplace_payload_with("Mrunali", "French", "California"); 
		step.user_calls_API_with_HTTP_request("AddPlaceAPI", "POST");
		step.verify_place_id_created_maps_to_using("Mrunali", "getPlaceAPI");
		}
	}

}
