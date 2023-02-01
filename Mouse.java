package cas;

public class Mouse extends Products{
	public String type;
	public int buttons;
	public Mouse(int barcode, String name, String type, String brand, String colour, String connectivity, int quantity,
			float original, float retail, int buttons) {
		super(barcode, name, brand, colour, connectivity, quantity, original, retail);
		this.type = type;
		this.buttons = buttons;
	}
	public String getType() {
		return(this.type);
	}
	public int getButtons() {
		return(this.buttons);
	}
}
