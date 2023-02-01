package cas;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public abstract class Users {

	private String id;
	private String username;
	private String name;
	private Address address;
	private String userType;

	public Users(String id, String username, String name, String houseNo, String post, String city, String userType) {
		id = this.id;
		username = this.username;
		name = this.name;
		this.address = new Address(houseNo, post, city);
		userType = this.userType;
	}

	public String getId() {
		return id;
	}

	public String getUser() {
		return username;
	}

	public String getName() {
		return name;
	}

	public Address getAddress() {
		return address;
	}

	public String getUserType() {
		return userType;
	}

	public static ArrayList<String> getUserNames() {
		File file = new File("UserAccounts.txt");
		String line = "";
		ArrayList<String> ar = new ArrayList<String>();
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			while ((line = br.readLine()) != null) {
				String[] values = line.split(",");
				ar.add(values[1]);
				// System.out.println(ar);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ar;
	}

}
