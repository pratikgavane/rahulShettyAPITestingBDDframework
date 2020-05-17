package frmwrk.stepDef;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException {
		PlaceValidationsStepDef m=new PlaceValidationsStepDef();
		if(PlaceValidationsStepDef.place_id==null) {
			
		m.add_Place_Payload_with("shetty", "English", "Banglore");
		m.user_calls_with_http_request("AddPlaceAPI", "GET");
		m.verify_place_Id_created_maps_to_using_getPlaceAPI("shetty", "getPlaceAPI");
		
		}
	}
}
