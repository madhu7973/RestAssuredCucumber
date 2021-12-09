package googleMaps;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import payLoads.PayLoads;

import static io.restassured.RestAssured.*;

public class AddUpdateGetPlaceE2E {

	public static void main(String[] args) {

		RestAssured.baseURI = "https://rahulshettyacademy.com";

		System.out.println("=====createPlace=====");

		String responseAdd = given().log().all().queryParam("key", "qaclick123")

				.headers("Content-Type", "application/json").body(PayLoads.addPlacePayLoad()).when()

				.post("/maps/api/place/add/json").then().log().all().extract().response().asString();

		JsonPath jpAdd = new JsonPath(responseAdd);

		String placeid = jpAdd.get("place_id");

		System.out.println("=====updatePlace=====");

		String responsePut = given().log().all().queryParam("key", "qaclick123").

				header("Content-Type", "application/json").body(PayLoads.putPlacePayLoad(placeid)).when().

				put("/maps/api/place/update/json").then().log().all().extract().response().asString();

		// JsonPath jpPut = new JsonPath(responsePut);

		System.out.println(responsePut);

		// System.out.println(jpPut.get("msg"));

		System.out.println("=====getPlace=====");

		String responseGet = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeid).

				when().get("/maps/api/place/get/json").then().log().all().extract()

				.response().asString();

		JsonPath jpGet = new JsonPath(responseGet);

		String newAddress = jpGet.get("address");

		System.out.println(newAddress);

	}

}
