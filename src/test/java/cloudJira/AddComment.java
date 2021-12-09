package cloudJira;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

public class AddComment {

	public static void main(String[] args) {
		RestAssured.baseURI = "https://madhu7973.atlassian.net/";
		//String response = 
		given().auth().preemptive().basic("madhu7973@gmail.com", "RF7sxk6cc2WrvpbFwwSSF06D").
		header("Content-Type", "application/json").body("{\r\n" + 
				"    \"body\": \"comment via eclipse\"\r\n" + 
				"}").when().post("rest/api/2/issue/10005/comment").then().assertThat().statusCode(201);
		
		/*
		 * JsonPath jp = new JsonPath(response);
		 * System.out.println(jp.getString("body"));
		 */
		
	}
}
