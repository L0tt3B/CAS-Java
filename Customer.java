package cas;

import java.util.ArrayList;
import java.util.List;

public class Customer extends Users {

	private List<Products> basket;

	public Customer(String id, String username, String name, String houseNo, String post, String city) {
		super(id, username, name, houseNo, post, city, "customer");
		basket = new ArrayList<Products>();
	}

	public float totalCost() {
		float cost = 0;
		for (Products product : basket) {
			cost += product.getRetail() * product.getQuantity();
		}
		return cost;
	}

	public void clearBasket() {
		basket.clear();
	}

	public List<Products> getProductsInStock() {
		List<Products> stock = Database.loadProductsFromFile();
		for (Products stockProduct : stock) {
			for (Products basketProduct : basket) {
				if (stockProduct.getBar() == basketProduct.getBar()) {
					stockProduct.setQuantity(stockProduct.getQuantity() - basketProduct.getQuantity());
				}
			}
		}
		return stock;
	}

	public void addToBasket(Products product) {
		for (Products basketItem : basket) {
			if (basketItem.getBar() == product.getBar()) {
				basketItem.setQuantity(basketItem.getQuantity() + product.getQuantity());
				return;
			}
		}
		basket.add(product);
	}

	public List<Products> getProductsInBasket() {
		return basket;
	}
}
