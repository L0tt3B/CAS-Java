package cas;

public class Address {
	private String city;
	private String houseNumber;
	private String postcode;

	public Address(String city, String houseNumber, String postcode) {
		super();
		this.city = city;
		this.houseNumber = houseNumber;
		this.postcode = postcode;
	}

	public String getCity() {
		return city;
	}

	public String getHouseNumber() {
		return houseNumber;
	}

	public String getPostcode() {
		return postcode;
	}

}
