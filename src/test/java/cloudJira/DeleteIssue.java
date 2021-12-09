package cloudJira;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

public class DeleteIssue {

	public static void main(String[] args) {
		RestAssured.baseURI = "https://madhu7973.atlassian.net/";
		given().auth().preemptive().basic("madhu7973@gmail.com", "RF7sxk6cc2WrvpbFwwSSF06D")
				.header("Content-Type", "application/json").when().log().all().delete("rest/api/2/issue/10005").then()
				.assertThat().statusCode(204);

	}
}
