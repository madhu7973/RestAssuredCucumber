package localJira;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class E2EJiraAPITesting {

	public static void main(String[] args) {

		RestAssured.baseURI = "http://localhost:8080/";
		SessionFilter sf = new SessionFilter();

		// Creating a session ID //44C7AE3E706603E30716CA9F03089A4F

		System.out.println("*****Create Session*****");
		given().header("Content-Type", "application/json")
				.body("{\r\n" + "    \"username\": \"madhu7973\",\r\n" + "    \"password\": \"Madhu123$\"\r\n" + "}")
				.filter(sf).when().post("rest/auth/1/session").then().log().all();

		/*
		 * Creating a issue "id": "10006", "key": "RES-7"
		 */

		/*
		 * given().header("Content-Type", "application/json").body("{\r\n" +
		 * "    \"fields\": {\r\n" + "        \"project\": {\r\n" +
		 * "            \"key\": \"RES\"\r\n" + "        },\r\n" +
		 * "        \"summary\": \"RestAssured-5\",\r\n" +
		 * "        \"description\": \"Creating issue via eclipse\",\r\n" +
		 * "        \"issuetype\": {\r\n" + "            \"name\": \"Bug\"\r\n" +
		 * "        }\r\n" + "    }\r\n" +
		 * "}").filter(sf).when().post("rest/api/2/issue").then().log().all().assertThat
		 * ().statusCode(201);
		 */

		System.out.println("*****Add Comment*****");
		// Add comment to issue. String

		String commResp = given().pathParam("key", "10007").header("Content-Type", "application/json")
				.body("{\r\n" + "    \"body\": \"Comment from eclipse\",\r\n" + "    \"visibility\": {\r\n"
						+ "        \"type\": \"role\",\r\n" + "        \"value\": \"Administrators\"\r\n" + "    }\r\n"
						+ "}")
				.filter(sf).when().post("rest/api/2/issue/{key}/comment").then().log().all().extract().response()
				.asString();

		JsonPath jpComment = new JsonPath(commResp);
		String commentID = jpComment.getString("id");

		/*
		 * System.out.println("*****Assign Issue*****"); // Assign issue to user.
		 * given().pathParam("key", "10007").header("Content-Type",
		 * "application/json").body("{\r\n" + "    \"name\": \"madhu7973\"\r\n" +
		 * "}").filter(sf).when().put("rest/api/2/issue/{key}/assignee").then().log().
		 * all().assertThat().statusCode(204);
		 */

		// Delete issue.
		/*
		 * given().pathParam("key",
		 * "10006").filter(sf).when().delete("rest/api/2/issue/{key}").
		 * then().log().all().assertThat().statusCode(204);
		 */

		/*
		 * System.out.println("*****Add Attachment*****"); //Add attachment
		 * given().pathParam("key", "10007").header("Content-Type",
		 * "multipart/form-data"). header("X-Atlassian-Token", "no-check").filter(sf).
		 * multiPart("file", new
		 * File("C:\\Users\\Madhusudan Devaraju\\Desktop\\FileUploadPopup.txt")).
		 * when().post("/rest/api/2/issue/{key}/attachments").then().log().all().
		 * assertThat().statusCode(200);
		 */

		System.out.println("*****Get Issue****");
		// Get Issue
		String respGetIssue = given().pathParam("key", "10007").queryParams("fields", "comment, status").filters(sf)
				.when().get("rest/api/2/issue/{key}").then().extract().response().asString();

		JsonPath jpGetIssue = new JsonPath(respGetIssue);
		jpGetIssue.prettyPrint();
		int commCount = jpGetIssue.getInt("fields.comment.comments.size()");

		for (int i = 0; i < commCount; i++) {

			if (jpGetIssue.getString("fields.comment.comments.get(" + i + ").id").equalsIgnoreCase(commentID)) {
				System.out.println("comment added successfully");

			}

		}

	}
}