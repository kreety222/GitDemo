package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;


public class Hooks {
	
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException
	{
		StepDefination sdi=new StepDefination();
		if(StepDefination.place_id==null)
		{
			sdi.add_place_payload_with("kreety", "English", "WTC");
			sdi.user_calls_with_http_request("AddPlaceAPI", "POST");
			sdi.verify_place_id_created_maps_to_using("kreety", "getPlaceAPI");
		}
		
	}
}
