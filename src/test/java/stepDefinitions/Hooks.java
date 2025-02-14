package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlace")
	public void getPlaceId() throws IOException {
		
		StepDefinations m = new StepDefinations();
		if(StepDefinations.place_id==null) {
		m.add_place_payload_with("Adarsh", "Hindi", "Bangaloe,Karnataka");
		m.user_calls_with_post_http_request("AddPlaceAPI", "POST");
		m.verify_place_id_created_maps_to_using("Adarsh", "GetPlaceAPI");
		}
	}

}
