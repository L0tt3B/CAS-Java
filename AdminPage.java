package cas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class AdminPage extends JFrame {

	FirstScreen first = new FirstScreen();

	private JPanel contentPane;
	private JTextField barcodeInput;
	private JTextField brandInput;
	private JTextField colourInput;
	private JTextField qntyInput;
	private JTextField originalInput;
	private JTextField retailInput;
	private JTable table;
	private JScrollPane adminScroll;
	private JTextField buttonInput;
	private DefaultTableModel tableModel;
	private final DecimalFormat decimal = new DecimalFormat("0.00");

	public AdminPage(Admin selectedUser) {
		setTitle("Pear Technologies");
		setIconImage(Toolkit.getDefaultToolkit().getImage(AdminPage.class.getResource("/resources/pear-24.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 745, 462);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		List<String> stockList = Database.getStock();
		String[] stockArray = new String[stockList.size()];
		for (int i = 0; i < stockArray.length; i++) {
			stockArray[i] = stockList.get(i);
		}

		JLabel barcodeLabel = new JLabel("Barcode:");
		barcodeLabel.setBounds(5, 320, 55, 14);

		JLabel brandLabel = new JLabel("Brand:");
		brandLabel.setBounds(5, 351, 49, 14);

		JLabel colourLabel = new JLabel("Colour:");
		colourLabel.setBounds(5, 382, 49, 14);

		barcodeInput = new JTextField();
		barcodeInput.setBounds(64, 317, 96, 20);
		barcodeInput.setColumns(10);

		brandInput = new JTextField();
		brandInput.setBounds(64, 348, 96, 20);
		brandInput.setColumns(10);

		colourInput = new JTextField();
		colourInput.setBounds(64, 379, 96, 20);
		colourInput.setColumns(10);

		JCheckBox wireCheck = new JCheckBox("Wireless?");
		wireCheck.setBounds(166, 378, 99, 23);

		JLabel qntyLabel = new JLabel("Quantity in Stock:");
		qntyLabel.setBounds(178, 320, 123, 14);

		qntyInput = new JTextField();
		qntyInput.setBounds(284, 317, 96, 20);
		qntyInput.setColumns(10);

		originalInput = new JTextField();
		originalInput.setBounds(284, 348, 96, 20);
		originalInput.setColumns(10);

		JLabel originalLabel = new JLabel("Original Cost:");
		originalLabel.setBounds(178, 351, 82, 14);

		JLabel retailLabel = new JLabel("Retail Price:");
		retailLabel.setBounds(284, 379, 83, 14);

		retailInput = new JTextField();
		retailInput.setBounds(283, 394, 96, 20);
		retailInput.setColumns(10);

		JComboBox keyTypes = new JComboBox();
		keyTypes.setModel(new DefaultComboBoxModel(new String[] { "gaming", "standard" }));
		keyTypes.setBounds(489, 347, 99, 22);

		JLabel typeLabel1 = new JLabel("Type:");
		typeLabel1.setBounds(439, 350, 49, 17);

		JLabel buttonLabel = new JLabel("No. of Buttons:");
		buttonLabel.setBounds(389, 397, 112, 14);

		buttonInput = new JTextField();
		buttonInput.setBounds(489, 394, 99, 20);
		buttonInput.setColumns(10);

		JLabel typeLabel = new JLabel("Type:");
		typeLabel.setBounds(424, 347, 49, 22);

		JComboBox mouseTypes = new JComboBox();
		mouseTypes.setModel(new DefaultComboBoxModel(new String[] { "standard", "flexible" }));
		mouseTypes.setBounds(483, 347, 96, 22);

		JLabel keyLabel = new JLabel("Keyboard Layout:");
		keyLabel.setBounds(399, 395, 136, 18);

		JComboBox keyLayouts = new JComboBox();
		keyLayouts.setModel(new DefaultComboBoxModel(new String[] { "UK", "US" }));
		keyLayouts.setBounds(511, 393, 55, 22);

		contentPane.add(typeLabel);
		contentPane.add(mouseTypes);
		contentPane.add(keyLabel);
		contentPane.add(keyLayouts);
		contentPane.add(keyTypes);
		contentPane.add(typeLabel1);
		contentPane.add(buttonLabel);
		contentPane.add(buttonInput);

		typeLabel.setVisible(false);
		mouseTypes.setVisible(false);
		keyLabel.setVisible(false);
		keyLayouts.setVisible(false);

		JComboBox deviceType = new JComboBox();
		deviceType.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (deviceType.getSelectedItem().equals("Mouse")) {
					keyTypes.setVisible(true);
					typeLabel1.setVisible(true);
					buttonLabel.setVisible(true);
					buttonInput.setVisible(true);
					typeLabel.setVisible(false);
					mouseTypes.setVisible(false);
					keyLabel.setVisible(false);
					keyLayouts.setVisible(false);

				} else if (deviceType.getSelectedItem().equals("Keyboard")) {
					keyTypes.setVisible(false);
					typeLabel1.setVisible(false);
					buttonLabel.setVisible(false);
					buttonInput.setVisible(false);
					typeLabel.setVisible(true);
					mouseTypes.setVisible(true);
					keyLabel.setVisible(true);
					keyLayouts.setVisible(true);

				}
			}
		});

		deviceType.setBounds(609, 301, 112, 20);
		deviceType.setModel(new DefaultComboBoxModel(new String[] { "Mouse", "Keyboard" }));

		JLabel deviceTypeLabel = new JLabel("Type:");
		deviceTypeLabel.setBounds(567, 304, 52, 14);
		contentPane.setLayout(null);

		contentPane.add(barcodeLabel);
		contentPane.add(barcodeInput);
		contentPane.add(qntyLabel);
		contentPane.add(colourLabel);
		contentPane.add(colourInput);
		contentPane.add(wireCheck);
		contentPane.add(brandLabel);
		contentPane.add(brandInput);
		contentPane.add(originalLabel);
		contentPane.add(retailLabel);
		contentPane.add(retailInput);
		contentPane.add(originalInput);
		contentPane.add(deviceType);
		contentPane.add(qntyInput);
		contentPane.add(deviceTypeLabel);

		table = new JTable();
		tableModel = (DefaultTableModel) table.getModel();
		tableModel.setColumnIdentifiers(new Object[] { "Barcode", "Device Name", "Type", "Brand", "Colour",
				"Connectivity", "Quantity", "Original Price", "Retail Price", "Extra" });

		updateStockTable(tableModel);

		adminScroll = new JScrollPane(table);
		adminScroll.setBounds(5, 46, 716, 247);

		contentPane.add(adminScroll);

		JLabel pearLogo = new JLabel("");
		pearLogo.setBounds(347, 11, 29, 24);

		pearLogo.setIcon(new ImageIcon(AdminPage.class.getResource("/resources/pear-24.png")));
		contentPane.add(pearLogo);

		JLabel adminLabel = new JLabel("Admin");
		adminLabel.setBounds(10, 22, 71, 20);
		adminLabel.setFont(new Font("Sitka Text", Font.PLAIN, 15));
		contentPane.add(adminLabel);

		JButton addButton = new JButton("Add Item");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Integer checkBarcodeInput = Integer.parseInt(barcodeInput.getText());
				} catch (NumberFormatException c) {
					JOptionPane.showMessageDialog(new JFrame(), "Barcode Input must be an Integer", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (!(barcodeInput.getText().length() == 6)) {
					JOptionPane.showMessageDialog(new JFrame(), "Barcode Must Be 6 digits Long", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				String barValue = barcodeInput.getText();
				if (!(colourInput.getText().toString().matches("[A-Za-z]+"))) {
					JOptionPane.showMessageDialog(new JFrame(), "Colour input must only contain letters.", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				String colourVal = colourInput.getText();
				if (!(brandInput.getText().toString().matches("[A-Za-z]+"))) {
					JOptionPane.showMessageDialog(new JFrame(), "Enter a valid brand.", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				String brandVal = brandInput.getText();
				String connectivity;

				if (wireCheck.isSelected()) {
					connectivity = "wireless";
				} else {
					connectivity = "wired";
				}

				try {
					String retailFormat = decimal.format(Double.valueOf(retailInput.getText().toString()));
				} catch (NumberFormatException c) {
					JOptionPane.showMessageDialog(new JFrame(), "Enter valid 0.00 format for retail price.", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				String retailVal = decimal.format(Double.valueOf(retailInput.getText()));
				try {
					Float retailNum = Float.parseFloat(retailInput.getText());
				} catch (NumberFormatException c) {
					JOptionPane.showMessageDialog(new JFrame(), "Retail only accepts integer values.", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				try {
					String originalFormat = decimal.format(Double.valueOf(originalInput.getText().toString()));
				} catch (NumberFormatException c) {
					JOptionPane.showMessageDialog(new JFrame(), "Enter valid 0.00 format for original price.", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				String origVal = decimal.format(Double.valueOf(originalInput.getText()));
				try {
					Float originalNum = Float.parseFloat(originalInput.getText());
				} catch (NumberFormatException c) {
					JOptionPane.showMessageDialog(new JFrame(), "Original price only accepts integers.", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				try {
					Float quantityCheck = Float.parseFloat(qntyInput.getText());
				} catch (NumberFormatException c) {
					JOptionPane.showMessageDialog(new JFrame(), "Quantity only accepts integers.", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				String qntyVal = qntyInput.getText();
				String nameValue = "";

				if (deviceType.getSelectedItem().equals("Mouse")) {
					nameValue = "mouse";
					Object mouseType = keyTypes.getSelectedItem();
					String buttonsNo = buttonInput.getText().toString();
					try {
						Integer buttonsNum = Integer.parseInt(buttonInput.getText());
					} catch (NumberFormatException c) {
						JOptionPane.showMessageDialog(new JFrame(), "Button needs an integer value.", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
					String[] emptyFields = { barValue, nameValue, buttonsNo, colourVal, brandVal, connectivity,
							retailVal, origVal, qntyVal };
					for (String checking : emptyFields) {
						if (checking.equals("")) {
							JOptionPane.showMessageDialog(new JFrame(), "All fields must contain input.", "Error",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
					}

				} else if (deviceType.getSelectedItem().equals("Keyboard")) {
					nameValue = "keyboard";
					String[] emptyFields = { barValue, colourVal, brandVal, connectivity, retailVal, origVal, qntyVal };
					for (String checking : emptyFields) {
						if (checking.equals("")) {
							JOptionPane.showMessageDialog(new JFrame(), "All fields require input.", "Error",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
					}
					Object keyType = mouseTypes.getSelectedItem();
					Object keyLayout = keyLayouts.getSelectedItem();
					System.out.println(keyType);
					System.out.println(keyLayout);

				}

				List<Products> stockList = Database.loadProductsFromFile();
				boolean inStock = Products.productAlreadyInStock(Integer.parseInt(barValue));
				if (inStock) {
					for (Products product : stockList) {
						if (product.getBar() == Integer.parseInt(barValue)) {
							product.setQuantity(product.getQuantity() + Integer.parseInt(qntyVal));
						}
					}
					Database.saveProductsToFile(stockList);
					updateStockTable(tableModel);
					return;
				}

				if (deviceType.getSelectedItem().equals("Mouse")) {
					System.out.println("adding mouse");
					Object mouseType = keyTypes.getSelectedItem();
					String buttonsNo = buttonInput.getText();
					Mouse meese = new Mouse(Integer.parseInt(barValue), nameValue, mouseType.toString(), brandVal,
							colourVal, connectivity, Integer.parseInt(qntyVal), Float.parseFloat(origVal),
							Float.parseFloat(retailVal), Integer.parseInt(buttonsNo));
					stockList.add(meese);
				} else if (deviceType.getSelectedItem().equals("Keyboard")) {
					System.out.println("adding keyboard");
					Object keyType = mouseTypes.getSelectedItem();
					Object keyLayout = keyLayouts.getSelectedItem();
					Keyboard key = new Keyboard(Integer.parseInt(barValue), nameValue, keyType.toString(), brandVal,
							colourVal, connectivity, Integer.parseInt(qntyVal), Float.parseFloat(origVal),
							Float.parseFloat(retailVal), keyLayout.toString());

					stockList.add(key);
				}
				// System.out.println(stockList);
				Database.saveProductsToFile(stockList);
				updateStockTable(tableModel);
			}
		});
		addButton.setForeground(new Color(255, 255, 255));
		addButton.setBackground(new Color(205, 92, 92));
		addButton.setBounds(632, 393, 89, 23);
		contentPane.add(addButton);

		JButton backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				first.setVisible(true);
				first.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				dispose();
			}
		});
		backButton.setBounds(632, 11, 89, 24);
		contentPane.add(backButton);

		List<Products> a = Database.loadProductsFromFile();
		Database.saveProductsToFile(a);
		updateStockTable(tableModel);

	}

	private void updateStockTable(DefaultTableModel tableModel) {
		List<Products> products = Database.loadProductsFromFile();

		tableModel.getDataVector().removeAllElements();

		for (Products product : products) {
			String[] row = new String[10];
			row[0] = Integer.toString(product.getBar());
			row[1] = product.getName();
			if (row[1].equals("mouse")) {
				Mouse moice = (Mouse) product;
				row[2] = moice.getType();
			} else {
				Keyboard keybrd = (Keyboard) product;
				row[2] = keybrd.getType();
			}
			row[3] = product.getBrand();
			row[4] = product.getColour();
			row[5] = product.getConnectivity();
			row[6] = Integer.toString(product.getQuantity());
			row[7] = Float.toString(product.getOriginal());
			row[8] = Float.toString(product.getRetail());
			if (row[1].equals("mouse")) {
				Mouse moice = (Mouse) product;
				row[9] = Integer.toString(moice.getButtons());
			} else {
				Keyboard keybrd = (Keyboard) product;
				row[9] = keybrd.getLayout();
			}
			tableModel.addRow(row);
		}
	}
}
