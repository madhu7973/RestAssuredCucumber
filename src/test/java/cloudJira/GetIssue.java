package cloudJira;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import org.testng.Assert;

public class GetIssue {
	public static void main(String[] args) {
		RestAssured.baseURI = "https://madhu7973.atlassian.net/";

		String respAddComm = given().pathParam("key", "10008").auth().preemptive()
				.basic("madhu7973@gmail.com", "RF7sxk6cc2WrvpbFwwSSF06D").header("Content-Type", "application/json")
				.body("{\r\n" + "    \"body\": \"New Comment from eclipse - 4\"\r\n" + "}").when()
				.post("rest/api/2/issue/{key}/comment").then().extract().response().asString();
		JsonPath jprespAddComm = new JsonPath(respAddComm);
		String newCommID = jprespAddComm.getString("id");
		String newCommBody = jprespAddComm.getString("body");
		System.out.println(newCommID);
		System.out.println(newCommBody);

		String respGetIssue = given().auth().preemptive().basic("madhu7973@gmail.com", "RF7sxk6cc2WrvpbFwwSSF06D")
				.pathParam("key", "10008").queryParam("fields", "comment").when().get("rest/api/2/issue/{key}").then()
				.extract().response().asString();

		JsonPath jpGetIssue = new JsonPath(respGetIssue);
		for (int i = 0; i < jpGetIssue.getInt("fields.comment.comments.size()"); i++) {

			if (jpGetIssue.getString("fields.comment.comments.get(" + i + ").id").equalsIgnoreCase(newCommID)) {
				System.out.println(jpGetIssue.getString("fields.comment.comments.get(" + i + ").id"));
				System.out.println(jpGetIssue.getString("fields.comment.comments.get(" + i + ").body"));
				Assert.assertEquals(jpGetIssue.getString("fields.comment.comments.get(" + i + ").body"), newCommBody);
			}

		}
	}

}
