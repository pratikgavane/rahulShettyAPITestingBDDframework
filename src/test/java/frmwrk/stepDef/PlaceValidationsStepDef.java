package frmwrk.stepDef;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.runner.RunWith;

import frmwrk.resources.APIResources;
import frmwrk.resources.TestDataBuild;
import frmwrk.resources.Utils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;


@RunWith(Cucumber.class)
public class PlaceValidationsStepDef extends Utils{
	ResponseSpecification respSpec;
	RequestSpecification res;
	Response response;
	static String place_id;
	TestDataBuild data =new TestDataBuild();
	
		@Given("add Place Payload with {string} {string} {string}")
		public void add_Place_Payload_with(String name, String language, String address) throws IOException {
			
			res=given().spec(requestSpecification()).body(data.addPlacePayLoad(name, language, address));
		}

		@When("user calls {string} with {string} http request")
		public void user_calls_with_http_request(String resource, String method) {
			//constructor will be called with value of resource which you pass
			APIResources resourceAPI= APIResources.valueOf(resource);
	    	System.out.println(resourceAPI.getResources());
	    	respSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
	    	if(method.equalsIgnoreCase("POST"))
	    	response= res.when().post(resourceAPI.getResources());
	    	else if(method.equalsIgnoreCase("GET")){
	    	response= res.when().get(resourceAPI.getResources());
	    	}
		}

	    @Then("the API call is success with status code {int}")
	    public void the_API_call_is_success_a_with_status_code(Integer int1) {
	    	assertEquals(response.getStatusCode(), int1.intValue());
	    }

	    @And("^\"([^\"]*)\" in response body is \"([^\"]*)\"$")
	    public void something_in_response_body_is_something(String keyValue, String expectedValue) throws Throwable {
	       
	        assertEquals(getJsonPath(response, keyValue),expectedValue); 
	    }
	    
	    @Then("verify place_Id created maps to {string} using {string}")
	    public void verify_place_Id_created_maps_to_using_getPlaceAPI(String expectedName, String resource) throws IOException {
	        place_id = getJsonPath(response, "place_id");
	        res=given().spec(requestSpecification()).queryParam("place_id",place_id);
	        user_calls_with_http_request(resource, "GET");
	        String actualName = getJsonPath(response, "name");
	        assertEquals(expectedName, actualName);
		       
	    }
	    
	    @Given("DeletePlace PayLoad")
	    public void deleteplace_PayLoad() throws IOException {
	       res=  given().spec(requestSpecification()).body(data.deletePlacePayLoad(place_id));
	    }

}
