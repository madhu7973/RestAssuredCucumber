package cloudJira;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import io.restassured.path.json.JsonPath;

public class CreateIssue {

	public static void main(String[] args) {
		RestAssured.baseURI = "https://madhu7973.atlassian.net/";
		String response = given().auth().preemptive().basic("madhu7973@gmail.com", "RF7sxk6cc2WrvpbFwwSSF06D")
				.header("Content-Type", "application/json")
				.body("{\r\n" + 
						"    \"fields\": {\r\n" + 
						"        \"project\": {\r\n" + 
						"            \"key\": \"RFS\"\r\n" + 
						"        },\r\n" + 
						"        \"summary\": \"PostmanIssue-2\",\r\n" + 
						"        \"description\": \"Creating issue via eclipse\",\r\n" + 
						"        \"issuetype\": {\r\n" + 
						"            \"name\": \"Bug\"\r\n" + 
						"        }\r\n" + 
						"    }\r\n" + 
						"}")
				.post("rest/api/2/issue").then().extract().response().asString();

		JsonPath jp = new JsonPath(response);
		jp.prettyPrint();
	
	}

}
