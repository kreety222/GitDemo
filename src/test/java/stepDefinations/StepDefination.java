package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.Location;
import resources.APIResources;
import resources.TestdataBuild;
import resources.Utils;

public class StepDefination extends Utils{
	RequestSpecification res;
	ResponseSpecification respspec;
	Response response;
	TestdataBuild data = new TestdataBuild();
	JsonPath js;
	static String place_id;
	
	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException  {
	    // Write code here that turns the phrase above into concrete actions
		System.out.println("hi");
		
		respspec= new ResponseSpecBuilder().expectStatusCode(200).build();
		res=given().spec(requestspecification())
		.body(data.add_place_payload(name, language, address));
	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {
	    // Write code here that turns the phrase above into concrete actions
		APIResources resourcesAPI = APIResources.valueOf(resource);
		System.out.println(resourcesAPI.getResource());
				
		if(method.equalsIgnoreCase("POST"))
			response = res.when().post(resourcesAPI.getResource());
		else if(method.equalsIgnoreCase("GET"))		
			response = res.when().get(resourcesAPI.getResource());
	}
	@Then("the API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
		assertEquals(response.getStatusCode(),200);
	}
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String value) {
	  
		assertEquals(getJsonPath(response,key),value);
	}
	
	@Then("verify place_Id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedname, String resource) throws IOException {
		place_id=getJsonPath(response, "place_id");
		
		res=given().spec(requestspecification()).queryParam("place_id", place_id);
		user_calls_with_http_request(resource,"GET");
			String name=getJsonPath(response,"name");
			assertEquals(name, expectedname);
	}

	@Given("DeletePlace Payload")
	public void delete_place_payload() throws IOException {
		res=given().spec(requestspecification()).body(data.deletePlacePayload(place_id));
		
	}

	
}
