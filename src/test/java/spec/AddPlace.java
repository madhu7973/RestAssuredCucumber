package spec;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.HashMap;

import org.hamcrest.core.Is;
import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class AddPlace {

	public HashMap<Object, Object> hm1 = new HashMap<Object, Object>();
	public String placeID;
	
	RequestSpecification reqSpec = new RequestSpecBuilder()
			.setBaseUri("https://rahulshettyacademy.com")
			.addQueryParam("key", "qaclick123")
			.setContentType(ContentType.JSON).build();
	ResponseSpecification resSpec = new ResponseSpecBuilder()
			.expectStatusCode(200)
			.expectContentType(ContentType.JSON)
			.build();
	
	@Test(priority = 1)
	public void addPlace() {
		
		//RestAssured.baseURI = "https://rahulshettyacademy.com";
		HashMap<String, String> hm2 = new HashMap<String, String>();
		hm2.put("lat", "-38.383494");
		hm2.put("lng", "33.427362");
		hm1.put("location", hm2);
		
		hm1.put("accuracy", "50");
		hm1.put("name", "HDK");
		hm1.put("phone_number", "(+91) 983 893 3937");
		hm1.put("address", "#520/2, 35th MR, HDK JP Nagar");
		
		ArrayList<String> al = new ArrayList<String>();
		al.add("shoe park");
		al.add("shop");
		hm1.put("types", al);
		
		hm1.put("website", "http://google.com");
		hm1.put("language", "Kannada-KA");

		
	Response resp =  given()
//			.queryParam("key", "qaclick123")
//			.header("Content-Type", "application/json")
			.spec(reqSpec)
			.body(hm1).
		when()
			.post("/maps/api/place/add/json").
		then()
			.spec(resSpec)
			.extract()
			.response();
	resp.prettyPrint();
	placeID = resp.path("place_id");
		
	}
	
	@Test(priority = 2)
	public void getPlace() {
		given()
			.spec(reqSpec)
			.queryParam("place_id", placeID).
		when()
			.get("/maps/api/place/get/json").
		then()
			.spec(resSpec)
			.assertThat()
			.body("name", Is.is("HDK"));
	}
	
}
