package oAuth;

import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class OAuth2 {

	public static void main(String[] args) {
		String url = "https://rahulshettyacademy.com/getCourse.php?state=verifyfjdss&"
				+ "code=4%2F3wGvy2DSOK3QUJ9SoVpqJ7VSbzUDNHv3oAundIu1BUGd3XiX3svL3Jg77ex9bkAclCK0yXLFeSb2rqOQt0ADplU"
				+ "&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none#";

		String partialURL = url.split("code=")[1];
		String code = partialURL.split("&scope")[0];

		// accessing the access_code
		System.out.println("=====getting access code=====");
		String accessTokenResp = given().urlEncodingEnabled(false).queryParam("code", code)
				.queryParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				.queryParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
				.queryParam("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
				.queryParam("grant_type", "authorization_code").when().log().all()
				.post("https://www.googleapis.com/oauth2/v4/token").asString();

		JsonPath jp1 = new JsonPath(accessTokenResp);
		String accessToken = jp1.getString("access_token");
		System.out.println("access token is: " + accessToken);

		// accessing the resources
		System.out.println("=====accessed resources=====");
		String resResp = given().queryParam("access_token", accessToken).when().log().all()
				.get("https://rahulshettyacademy.com/getCourse.php").asString();
		JsonPath jp2 = new JsonPath(resResp);
		jp2.prettyPrint();

	}

}
