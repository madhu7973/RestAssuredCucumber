package pojo;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;

public class OAuth2 {

	@Test
	public void OAuthUsingPojo() {
		String url = "https://rahulshettyacademy.com/getCourse.php?state=verifyfjdss&"
				+ "code=4%2F4AG-BeJ6ijEKy_7vqbMIk3BWrvzw6bQ0nCmhf1AbFdkhF2h3Gt3EtZycOC64SPSMpBpE5b1t96hcfOCEvYFaViM"
				+ "&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none#";

		// splitting the above url into two parts and picking the second part
		String partialURL = url.split("code=")[1];

		// splitting the "partialURL" into two parts and picking the first part
		String code = partialURL.split("&scope")[0];

		// accessing the access_code
		System.out.println("=====getting access code=====");
		String accessTokenResp = given().urlEncodingEnabled(false).queryParam("code", code)
				.queryParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				.queryParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
				.queryParam("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
				.queryParam("grant_type", "authorization_code").
			when()
				.post("https://www.googleapis.com/oauth2/v4/token")
				.asString();

		JsonPath jp1 = new JsonPath(accessTokenResp);
		String accessToken = jp1.getString("access_token");
		System.out.println("access token is: " + accessToken);

		// accessing the resources
		System.out.println("=====accessed resources=====");
		OAuth2Pojo OA2P = given()
				.queryParam("access_token", accessToken)
				.expect()
				.defaultParser(Parser.JSON).
			when()
				.get("https://rahulshettyacademy.com/getCourse.php")
				.as(OAuth2Pojo.class);

		System.out.println(OA2P.getLinkedIn());
		System.out.println(OA2P.getInstructor());
		
		//hard coding the index 
		OA2P.getCourses().getApi().get(0).getCourseTitle();

		// List<OAuth2WebAutomation> auto = OA2P.getCourses().getWebAutomation();

		//printing the price of course Protractor
		int proPrice = 0;
		for (int i = 0; i < OA2P.getCourses().getWebAutomation().size(); i++) {
			if (OA2P.getCourses().getWebAutomation().get(i).getCourseTitle().equalsIgnoreCase("Protractor")) {
				int price = Integer.parseInt(OA2P.getCourses().getWebAutomation().get(i).getPrice());
				proPrice = price;
			}
		}
		Assert.assertEquals(proPrice, 40);

		// Printing all the course titles of WebAutomation
		ArrayList<String> expected = new ArrayList<String>();
		expected.add("Selenium Webdriver Java");
		expected.add("Cypress");
		expected.add("Protractor");

		ArrayList<String> actual = new ArrayList<String>();
		
		for (int i = 0; i < OA2P.getCourses().getWebAutomation().size(); i++) {
			actual.add(OA2P.getCourses().getWebAutomation().get(i).getCourseTitle());
		}
		Assert.assertEquals(actual, expected);

		// Printing the total price of all the courses of WebAutomation
		int totalPrice = 0;
		for (int i = 0; i < OA2P.getCourses().getWebAutomation().size(); i++) {
			int price = Integer.parseInt(OA2P.getCourses().getWebAutomation().get(i).getPrice());
			totalPrice = price + totalPrice;
			// System.out.println(totalPrice);
		}
		Assert.assertEquals(totalPrice, 130);
	}
}
