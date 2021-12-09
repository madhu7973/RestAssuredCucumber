package cloudJira;

import io.restassured.RestAssured;
//import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class AssignIssue {

	public static void main(String[] args) {

		RestAssured.baseURI = "https://madhu7973.atlassian.net/";
		 given().auth().preemptive().basic("madhu7973@gmail.com", "RF7sxk6cc2WrvpbFwwSSF06D").
		 header("Content-Type", "application/json").body("{\r\n" + 
		 		"    \"accountId\": \"5c385c57e6047225b6d5944d\"\r\n" + 
		 		"}").when().put("rest/api/2/issue/10005/assignee").then().assertThat().statusCode(204);

	}

}
