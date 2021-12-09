package rsaLibrary;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import payLoads.PayLoads;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;

public class AddBook {

	ArrayList<String> bookID = new ArrayList<String>();

	@Test(dataProvider = "bookData")
	public void addBookToLib(String isbn, String aisle) {
		RestAssured.baseURI = "http://216.10.245.166";
		String addBookResp = given().header("Content-Type", "application/json")
				.body(PayLoads.addBookPayLoad(isbn, aisle)).when().post("/Library/Addbook.php").then().assertThat()
				.statusCode(200).extract().response().asString();

		JsonPath jp = new JsonPath(addBookResp);
		bookID.add(jp.getString("ID"));
		jp.prettyPrint();

	}

	@Test
	public void deleteBook() {
		String deleteBookResp = null;
		for (int i = 0; i < bookID.size(); i++) {
			deleteBookResp = given().header("Content-Type", "application/json").body(PayLoads.deleteBook(bookID.get(i))).when()
					.delete("/Library/DeleteBook.php").then().assertThat().statusCode(200).extract().response()
					.asString();
			JsonPath jp = new JsonPath(deleteBookResp);
			jp.prettyPrint();
		}

	}

	@DataProvider(name = "bookData")
	public Object[][] getData() {
		String bookID[][] = { { "test8", "1717" }, { "test8", "1818" }, { "test8", "1919" } };
		return bookID;
	}
}
