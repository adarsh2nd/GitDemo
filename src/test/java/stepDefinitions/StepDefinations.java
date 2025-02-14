package stepDefinitions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;
import pojo.ResponseJson;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefinations extends Utils {

	RequestSpecification res;
	ResponseSpecification resspec;
	Response response;
	static String place_id;
	TestDataBuild data = new TestDataBuild();
	
	public String newMethod() {
		String str ="Bye World";
		return str;
	}
	
	public String anotherNewMethod() {
		String str ="Hello World";
		return str;
	}

	@Given("Add Place payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {
		res = RestAssured.given().spec(requestSpecification()).body(data.addPlacePayload(name, language, address));
	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_post_http_request(String resource, String method) {
		resspec = new ResponseSpecBuilder().expectStatusCode(200).build();
		APIResources apiResource = APIResources.valueOf(resource);
		if (method.equalsIgnoreCase("GET"))
			response = res.when().get(apiResource.getResource());
		else if (method.equalsIgnoreCase("POST"))
			response = res.when().post(apiResource.getResource());

	}

	@Then("the API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(Integer statusCode) {
		assertEquals(response.getStatusCode(), statusCode);
	}

	@Then("{string} in response body is {string}")
	public void status_in_response_body_is_ok(String key, String val) {
		String resp = getJsonPathValue(response, key);
		assertEquals(resp, val);

	}

	@Then("verify place_id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {

		 // requestSpec
	     place_id=getJsonPathValue(response,"place_id");
		 res=RestAssured.given().spec(requestSpecification()).queryParam("place_id",place_id);
		 user_calls_with_post_http_request(resource,"GET");
		  String actualName=getJsonPathValue(response,"name");
		  assertEquals(actualName,expectedName);


	}
	@Given("Delete place payload")
	public void delete_place_payload() throws IOException {
	//String placeid = data.deletePlacePayload(place_id);
	   res = RestAssured.given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
	   
	}


}
