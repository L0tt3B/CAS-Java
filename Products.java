package cas;

import java.util.List;

public abstract class Products {
	private int barcode;
	private String name;
	private String brand;
	private String colour;
	private String connectivity;
	private int quantity;
	private float original;
	private float retail;

	public Products(int barcode, String name, String brand, String colour, String connectivity, int quantity,
			float original, float retail) {
		this.barcode = barcode;
		this.name = name;
		this.brand = brand;
		this.colour = colour;
		this.connectivity = connectivity;
		this.quantity = quantity;
		this.original = original;
		this.retail = retail;
	}

	public int getBar() {
		return (this.barcode);
	}

	public String getName() {
		return (this.name);
	}

	public String getBrand() {
		return (this.brand);
	}

	public String getColour() {
		return (this.colour);
	}

	public String getConnectivity() {
		return (this.connectivity);
	}

	public int getQuantity() {
		return (this.quantity);
	}

	public float getOriginal() {
		return (this.original);
	}

	public float getRetail() {
		return (this.retail);
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public static boolean productAlreadyInStock(int bar) {
		List<Products> stockList = Database.loadProductsFromFile();
		for (int i = 0; i < stockList.size(); i++) {
			if (stockList.get(i).getBar() == bar) {
				return true;
			}
		}
		return false;
	}

}
