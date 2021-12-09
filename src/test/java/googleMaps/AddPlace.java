package googleMaps;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import payLoads.PayLoads;
//import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.*;

public class AddPlace {

	public static void main(String[] args) {

		// given - all the inputs such as parameters, header and body are included here.
		// when - HTTP method and URI are included here.
		// then - assertions such as status code are included here.

		RestAssured.baseURI = "https://rahulshettyacademy.com";

		String response = given().queryParam("key", "qaclick123").header("Content-Type", "application/json")

				.body(PayLoads.addPlacePayLoad()).when().post("/maps/api/place/add/json").

//		then().assertThat().statusCode(200).body("scope", equalTo("APP")).
//			header("Server", "Apache/2.4.18 (Ubuntu)");

				then().extract().response().asString();

		JsonPath jp = new JsonPath(response);

		System.out.println("Response is as below");
		jp.prettyPrint();

		// System.out.println(response);

		// System.out.println(jp.get("place_id"));

	}
}
