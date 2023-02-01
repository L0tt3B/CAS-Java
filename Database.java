package cas;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Database {
	public static List<Products> loadProductsFromFile() {
		File file = new File("Stock.txt");
		String line = "";
		ArrayList<Products> data = new ArrayList<Products>();
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(file));
			while ((line = br.readLine()) != null) {
				String[] values = line.split(", ");
				if (values[1].equals("mouse")) {
					Mouse mice = new Mouse(Integer.parseInt(values[0]), values[1], values[2], values[3], values[4],
							values[5], Integer.parseInt(values[6]), Float.parseFloat(values[7]),
							Float.parseFloat(values[8]), Integer.parseInt(values[9]));
					data.add(mice);

				} else {
					Keyboard key = new Keyboard(Integer.parseInt(values[0]), values[1], values[2], values[3], values[4],
							values[5], Integer.parseInt(values[6]), Float.parseFloat(values[7]),
							Float.parseFloat(values[8]), (values[9]));
					data.add(key);
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return data;
	}

	public static void checkoutItemsFromStock(List<Products> basketProducts) {

		List<Products> stock = loadProductsFromFile();

		for (int i = 0; i < basketProducts.size(); i++) {
			for (int j = 0; j < stock.size(); j++) {
				if (basketProducts.get(i).getBar() == stock.get(j).getBar()) {
					if (stock.get(j).getName().equals("mouse")) {
						Mouse temp = (Mouse) stock.get(j);

						System.out.println(basketProducts.get(i).getQuantity());
						temp.setQuantity(temp.getQuantity() - basketProducts.get(i).getQuantity());

						stock.set(j, temp);
					} else {
						Keyboard temp = (Keyboard) stock.get(j);

						System.out.println(basketProducts.get(i).getQuantity());
						temp.setQuantity(temp.getQuantity() - basketProducts.get(i).getQuantity());

						stock.set(j, temp);
					}

				}
			}
		}
		saveProductsToFile(stock);

	}

	public static void saveProductsToFile(List<Products> products) {
		System.out.println("saving to stock");
		String fileContents = "";
		for (Products product : products) {
			fileContents += product.getBar() + ", ";
			fileContents += product.getName() + ", ";
			if (product.getName().equals("mouse")) {
				fileContents += ((Mouse) product).getType() + ", ";
			} else {
				fileContents += ((Keyboard) product).getType() + ", ";
			}
			fileContents += product.getBrand() + ", ";
			fileContents += product.getColour() + ", ";
			fileContents += product.getConnectivity() + ", ";
			fileContents += product.getQuantity() + ", ";
			fileContents += product.getOriginal() + ", ";
			fileContents += product.getRetail() + ", ";
			if (product.getName().equals("mouse")) {
				fileContents += ((Mouse) product).getButtons();
			} else {
				fileContents += ((Keyboard) product).getLayout();
			}

			fileContents += "\n";
		}
		FileWriter myWriter;
		try {
			myWriter = new FileWriter("Stock.txt");
			myWriter.write(fileContents);
			myWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static ArrayList<String> getStock() {
		File file = new File("Stock.txt");
		String line = "";
		ArrayList<String> stocksAr = new ArrayList<String>();
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			while ((line = br.readLine()) != null) {

				String[] values = line.split(", ");
				for (String val : values) {
					System.out.print(val + ", ");
				}
				stocksAr.add(values[1]);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stocksAr;
	}

	public static ArrayList<Users> getUserList() {
		File file = new File("UserAccounts.txt");
		String line = "";
		ArrayList<Users> userAr = new ArrayList<Users>();
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			while ((line = br.readLine()) != null) {
				String[] values = line.split(", ");
				if (values[6].equals("admin")) {
					Admin administration = new Admin(values[0], values[1], values[2], values[3], values[4], values[5],
							values[6]);
					userAr.add(administration);
				} else if (values[6].equals("customer")) {
					Customer custom = new Customer(values[0], values[1], values[2], values[3], values[4], values[5]);
					userAr.add(custom);
				}
			}
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userAr;
	}

	public static ArrayList<String> Address() {
		File file = new File("UserAccounts.txt");
		String line = "";
		ArrayList<String> ar = new ArrayList<String>();
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			while ((line = br.readLine()) != null) {
				String[] values = line.split(",");
				ar.add(values[4]);
				System.out.println(ar);
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
