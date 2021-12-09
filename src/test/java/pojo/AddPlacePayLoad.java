package pojo;

import java.util.ArrayList;
import java.util.HashMap;

public class AddPlacePayLoad {

	public String phone_number;
	public String address;
	public String website;
	public String language;
	public ArrayList<String> types;
	public AddPlace_LocationPojo location;
	public String accuracy;
	public String name;

//	public AddPlace_LocationPojo getLocation() {
//		return location;
//	}
//
//	public void setLocation(AddPlace_LocationPojo location) {
//		this.location = location;
//	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public ArrayList<String> getTypes() {
		return types;
	}

	public void setTypes(ArrayList<String> types) {
		this.types = types;
	}

	public AddPlace_LocationPojo getLocation() {
		return location;
	}

	public void setLocation(AddPlace_LocationPojo location) {
		this.location = location;
	}

	public String getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(String accuracy) {
		this.accuracy = accuracy;
	}

//	public String newPaceDetails() {
//		return this.location + " " + this.accuracy + " " + this.name + " " + this.phone_number + " " + this.address
//				+ " " + this.types + " " + this.website + " " + this.language;
//	}

}
