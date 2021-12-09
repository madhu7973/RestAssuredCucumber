package pojo;

import java.util.ArrayList;
import java.util.HashMap;
import static org.hamcrest.Matchers.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;

public class AddPlace {

	public static AddPlacePayLoad appl = new AddPlacePayLoad();
	AddPlace_LocationPojo aplp = new AddPlace_LocationPojo();
	public static String placeID;

	@Test(priority = 1)
	public void addPlace() {

		ArrayList<String> al = new ArrayList<String>();
		al.add("shoe park");
		al.add("shop");

//		HashMap<Object, Object> hm = new HashMap<Object, Object>();
//		hm.put("lat", "-38.383494");
//		hm.put("lng", "33.427362");

//		appl.setLocation(hm);
		aplp.setLat(-38.383494);
		aplp.setLng(33.427362);
		appl.setLocation(aplp);
		appl.setTypes(al);
		appl.setAccuracy("50");
		appl.setAddress("#12, 13th Cross AK Halli");
		appl.setLanguage("Kannada-KA");
		appl.setName("Krishna Krupa");
		appl.setPhone_number("(+91) 983 893 3937");
		appl.setWebsite("http://google.com");

		RestAssured.baseURI = "https://rahulshettyacademy.com";
		Response resp = given()
				.queryParam("key", "qaclick123")
				.contentType(ContentType.JSON)
				.body(appl).
			when()
				.post("/maps/api/place/add/json").
			then()
				.statusCode(200)
				.assertThat()
				.body("status", equalTo("OK"))
				.extract()
				.response();
		placeID = resp.path("place_id");

	}
	
	  @Test (priority = 2) public void getPlace() {
	  
	  RestAssured.baseURI = "https://rahulshettyacademy.com";
	  
	 		given() 
			  .queryParam("key", "qaclick123")
			  .queryParam("place_id", placeID)
			  .expect()
			  .defaultParser(Parser.JSON).
			when()
				.get("/maps/api/place/get/json").
			then()
				.statusCode(200)
				.assertThat()
				.body("name", equalTo("Krishna Krupa"));
			
	  } 
}
