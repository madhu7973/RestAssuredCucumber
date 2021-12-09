package jsonPathParse;

import io.restassured.path.json.JsonPath;
import payLoads.PayLoads;

public class CourseJsonPathParse {

	public static void main(String[] args) {
		JsonPath jp = new JsonPath(PayLoads.mockCourseResponse());

		// Print No of courses returned by API
		int courseCount = jp.getInt("courses.size()");
		System.out.println(courseCount);

		System.out.println("=====Print Purchase Amount=====");
		System.out.println(jp.getInt("dashboard.purchaseAmount"));

		System.out.println("=====Print Title of the first course=====");
		System.out.println(jp.getString("courses[0].title"));

		System.out.println("=====Print All course titles and their respective Prices=====");
		for (int i = 0; i < courseCount; i++) {
			System.out.println("Course: " + jp.get("courses[" + i + "].title"));
			System.out.println("Price: " + jp.getInt("courses[" + i + "].price"));
			System.out.println("Copies: " + jp.getString("courses[" + i + "].copies"));
		}

		System.out.println("=====Print no of copies sold by API Course=====");
		for (int j = 0; j < courseCount; j++) {
			if ((jp.getString("courses[" + j + "].title")).equalsIgnoreCase("API")) {
				System.out.println("Course " + jp.getString("courses[" + j + "].title"));
				System.out.println("Price " + jp.getInt("courses[" + j + "].price"));
				System.out.println("Copies " + jp.getInt("courses[" + j + "].copies"));
				break;
			}
		}

		System.out.println("=====Verify if Sum of all Course prices matches with Purchase Amount=====");
		// ArrayList<Integer> al = new ArrayList<Integer>();
		int sum = 0;
		for (int k = 0; k < courseCount; k++) {
			// al.add(jp.getInt("courses[" + k + "].price"));
			int courseCost = jp.getInt("courses[" + k + "].price");
			int courseCopies = jp.getInt("courses[" + k + "].copies");
			int amt = courseCost * courseCopies;
			sum += amt;
			// al.add(courseCost*courseCopies);
		}
//		for (int cost : al)
//			sum += cost;
		System.out.println("Calculated sum: " + sum);

		if (jp.getInt("dashboard.purchaseAmount") == sum) {
			System.out.println("Actual sum: " + jp.getInt("dashboard.purchaseAmount"));
			System.out.println("both are equal");
		} else {
			System.out.println("both are not equal");
		}

	}

}
