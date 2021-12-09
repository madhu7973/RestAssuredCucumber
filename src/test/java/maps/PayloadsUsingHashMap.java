package maps;

import static io.restassured.RestAssured.given;
import org.hamcrest.core.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class PayloadsUsingHashMap {
	
	public HashMap<Object, Object> hm1 = new HashMap<Object, Object>();
	public String placeID;
	
	@Test(priority = 1)
	public void addPlace() {
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		HashMap<String, String> hm2 = new HashMap<String, String>();
		hm2.put("lat", "-38.383494");
		hm2.put("lng", "33.427362");
		hm1.put("location", hm2);
		
		hm1.put("accuracy", "50");
		hm1.put("name", "Frontline house");
		hm1.put("phone_number", "(+91) 983 893 3937");
		hm1.put("address", "29, side layout, cohen 09");
		
		ArrayList<String> al = new ArrayList<String>();
		al.add("shoe park");
		al.add("shop");
		hm1.put("types", al);
		
		hm1.put("website", "http://google.com");
		hm1.put("language", "Kannada-KA");
		
	Response resp =  given()
			.queryParam("key", "qaclick123")
			.header("Content-Type", "application/json")
			.body(hm1).
		when()
			.post("/maps/api/place/add/json").
		then()
			.statusCode(200)
			.extract()
			.response();
	resp.prettyPrint();
	placeID = resp.path("place_id");
		
	}
	
	@Test(priority = 2)
	public void getPlace() {
		given()
			.queryParam("key", "qaclick123")
			.queryParam("place_id", placeID).
		when()
			.get("/maps/api/place/get/json").
		then()
			.statusCode(200)
			.assertThat()
			.body("name", Is.is("Frontline house"));
	}
}
