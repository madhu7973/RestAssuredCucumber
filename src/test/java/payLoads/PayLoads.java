package payLoads;

public class PayLoads {
	
	public static String address = "32124 Happy Hollow Rd, Middleport, OH, 45760";
	
	public static String addPlacePayLoad() {
		return "{\r\n" + 
				"  \"location\": {\r\n" + 
				"    \"lat\": -38.383494,\r\n" + 
				"    \"lng\": 33.427362\r\n" + 
				"  },\r\n" + 
				"  \"accuracy\": 50,\r\n" + 
				"  \"name\": \"Frontline house\",\r\n" + 
				"  \"phone_number\": \"(+91) 983 893 3937\",\r\n" + 
				"  \"address\": \"29, side layout, cohen 09\",\r\n" + 
				"  \"types\": [\r\n" + 
				"    \"shoe park\",\r\n" + 
				"    \"shop\"\r\n" + 
				"  ],\r\n" + 
				"  \"website\": \"http://google.com\",\r\n" + 
				"  \"language\": \"French-IN\"\r\n" + 
				"}";
	}
	
	public static String putPlacePayLoad(String placeID) {
		return "{\r\n" + 
				"\"place_id\":\""+placeID+"\",\r\n" + 
				"\"address\":\""+address+"\",\r\n" + 
				"\"key\":\"qaclick123\"\r\n" + 
				"}";
	}
	
	public static String mockCourseResponse() {
		return "{\r\n" + 
				"  \"dashboard\": {\r\n" + 
				"    \"purchaseAmount\": 3035,\r\n" + 
				"    \"website\": \"rahulshettyacademy.com\"\r\n" + 
				"  },\r\n" + 
				"  \"courses\": [\r\n" + 
				"    {\r\n" + 
				"      \"title\": \"Selenium Python\",\r\n" + 
				"      \"price\": 50,\r\n" + 
				"      \"copies\": 6\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"title\": \"Cypress\",\r\n" + 
				"      \"price\": 40,\r\n" + 
				"      \"copies\": 4\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"title\": \"RPA\",\r\n" + 
				"      \"price\": 45,\r\n" + 
				"      \"copies\": 10\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"title\": \"API\",\r\n" + 
				"      \"price\": 55,\r\n" + 
				"      \"copies\": 30\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"title\": \"Appium\",\r\n" + 
				"      \"price\": 25,\r\n" + 
				"      \"copies\": 19\r\n" + 
				"    }\r\n" + 
				"    \r\n" + 
				"  ]\r\n" + 
				"}";
	}
	
	public static String addBookPayLoad(String isbn, String aisle)
	{
		return "{\r\n" + 
				"    \"name\": \"Automation\",\r\n" + 
				"    \"isbn\": \""+isbn+"\",\r\n" + 
				"    \"aisle\": \""+aisle+"\",\r\n" + 
				"    \"author\": \"madhu7973\"\r\n" + 
				"}";
	}
	
	public static String deleteBook(String bookid) {
		return"{\r\n" + 
				"    \"ID\": \""+bookid+"\"\r\n" + 
				"}";
	}

}
