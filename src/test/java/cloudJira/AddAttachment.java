package cloudJira;

import static io.restassured.RestAssured.given;

import java.io.File;

import io.restassured.RestAssured;

public class AddAttachment {
	public static void main(String[] args) {
		RestAssured.baseURI = "https://madhu7973.atlassian.net/";
		given().pathParam("key", "10007").auth().preemptive().basic("madhu7973@gmail.com", "RF7sxk6cc2WrvpbFwwSSF06D").
		header("Content-Type", "multipart/form-data").header("X-Atlassian-Token", "no-check").
		multiPart("file", new File("C:\\Users\\Madhusudan Devaraju\\Desktop\\Info.txt")).
		when().post("/rest/api/2/issue/{key}/attachments").then().log().all().assertThat().statusCode(200);
	}
	
}
