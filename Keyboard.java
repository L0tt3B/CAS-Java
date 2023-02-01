package cas;

public class Keyboard extends Products{
	public String type;
	public String layout;
	public Keyboard(int barcode, String name, String type, String brand, String colour, String connectivity, int quantity,
			float original, float retail, String layout) {
		super(barcode, name, brand, colour, connectivity, quantity, original, retail);
		this.type = type;
		this.layout = layout;
	}
	public String getType() {
		return(this.type);
	}
	public String getLayout() {
		return(this.layout);
	}


}
