package cas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class UserPage extends JFrame {
	private JPanel contentPane;
	private JTextField SearchText;
	private DefaultTableModel tableModel;
	private JTable table;
	private JTable basketTable;
	private DefaultTableModel basketTableModel;

	/**
	 * Create the frame.
	 */
	FirstScreen first = new FirstScreen();

	private Customer selectedUser;
	private JTextField cardNoInput;
	private JTextField CVVInput;
	private JTextField emailInput;
	private JLabel totalCostLabel;
	private JLabel firstLineCredit;
	private JLabel firstLinePayPal;
	private JLabel secondLinePayPal;
	private JLabel secondLineCredit;
	private final DecimalFormat df = new DecimalFormat("0.00");

	public UserPage(Customer selectedUser) {
		this.selectedUser = selectedUser;
		setIconImage(Toolkit.getDefaultToolkit().getImage(UserPage.class.getResource("/resources/pear-24.png")));
		setTitle("Pear Technologies");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1224, 527);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel SearchLabel = new JLabel("Search");
		SearchLabel.setBounds(147, 16, 49, 18);
		SearchLabel.setFont(new Font("Sitka Text", Font.PLAIN, 14));

		SearchText = new JTextField();
		SearchText.setBounds(206, 13, 387, 20);
		SearchText.setColumns(10);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		JScrollPane basketScroll = new JScrollPane();
		scrollPane.setBounds(277, 49, 292, 259);

		contentPane.add(SearchLabel);
		contentPane.add(SearchText);

		table = new JTable();
		basketTable = new JTable();
		tableModel = (DefaultTableModel) table.getModel();
		tableModel.setColumnIdentifiers(new Object[] { "Barcode", "Device Name", "Type", "Brand", "Colour",
				"Connectivity", "Quantity", "Retail Price", "Extra" });
		basketTableModel = (DefaultTableModel) basketTable.getModel();
		basketTableModel.setColumnIdentifiers(new Object[] { "Barcode", "Device Name", "Type", "Brand", "Colour",
				"Connectivity", "Quantity", "Retail Price", "Extra" });
		updateStockTable(tableModel);
		updateBasket(basketTableModel);

		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(20, 56, 744, 216);
		contentPane.add(scrollPane);

		basketScroll = new JScrollPane(basketTable);
		basketScroll.setBounds(133, 283, 621, 196);
		contentPane.add(basketScroll);

		JButton goBackButton = new JButton("Go Back");
		goBackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				first.setVisible(true);
				first.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				dispose();
			}
		});
		goBackButton.setBackground(new Color(255, 255, 255));
		goBackButton.setBounds(10, 5, 113, 36);
		contentPane.add(goBackButton);

		JButton addButton = new JButton("Add Item");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int value = table.getSelectedRow();
				List<Products> products = Database.loadProductsFromFile();
				String barcodeStr = tableModel.getDataVector().elementAt(value).firstElement().toString();
				for (Products product : products) {
					if (product.getBar() == Integer.parseInt(barcodeStr)) {
						product.setQuantity(1);
						selectedUser.addToBasket(product);
						updateBasket(basketTableModel);
						updateStockTable(tableModel);
					}
				}
			}
		});
		addButton.setBackground(Color.WHITE);
		addButton.setForeground(new Color(0, 0, 0));
		addButton.setBounds(10, 334, 113, 36);
		contentPane.add(addButton);

		JButton cancelButton = new JButton("Cancel Shop");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedUser.clearBasket();
				updateStockTable(tableModel);
				updateBasket(basketTableModel);
			}
		});
		cancelButton.setBackground(Color.WHITE);
		cancelButton.setForeground(new Color(0, 0, 0));
		cancelButton.setBounds(10, 397, 113, 36);
		contentPane.add(cancelButton);

		JLabel BasketLabel = new JLabel("   Basket");
		BasketLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		BasketLabel.setIcon(new ImageIcon(UserPage.class.getResource("/resources/add-to-basket-3042.png")));
		BasketLabel.setBounds(1094, 3, 106, 36);
		contentPane.add(BasketLabel);

		JTabbedPane paymentPanes = new JTabbedPane(JTabbedPane.TOP);
		paymentPanes.setBounds(799, 45, 401, 434);
		contentPane.add(paymentPanes);

		JPanel paypal = new JPanel();
		paypal.setBackground(new Color(255, 255, 255));
		paymentPanes.addTab("PayPal", null, paypal, null);
		paypal.setLayout(null);

		JLabel paypalLabel = new JLabel("PayPal");
		paypalLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		paypalLabel.setBounds(171, 0, 150, 38);
		paypal.add(paypalLabel);

		emailInput = new JTextField();
		emailInput.setBounds(60, 78, 282, 31);
		paypal.add(emailInput);
		emailInput.setColumns(10);

		JPanel successfulPayment = new JPanel();
		successfulPayment.setBounds(10, 235, 376, 160);
		paypal.add(successfulPayment);
		successfulPayment.setVisible(false);
		successfulPayment.setLayout(null);

		JLabel validationLabel = new JLabel("Enter a valid email.");
		validationLabel.setFont(new Font("Tw Cen MT", Font.PLAIN, 14));
		validationLabel.setForeground(new Color(255, 0, 0));
		validationLabel.setBounds(149, 187, 179, 20);
		paypal.add(validationLabel);
		validationLabel.setVisible(false);

		JButton payButton = new JButton("Pay with Paypal");
		payButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String email = emailInput.getText();
				final String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
						+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
				if (!email.matches(emailPattern)) {
					validationLabel.setVisible(true);
					successfulPayment.setVisible(false);

				} else {
					successfulPayment.setVisible(true);
					validationLabel.setVisible(false);
					checkout();
				}
			}
		});
		payButton.setBackground(new Color(70, 130, 180));
		payButton.setForeground(Color.BLACK);
		payButton.setBounds(60, 130, 282, 31);
		paypal.add(payButton);

		JLabel emailLabel = new JLabel("Email:");
		emailLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		emailLabel.setBounds(60, 58, 55, 20);
		paypal.add(emailLabel);

		JLabel successLabel = new JLabel("Payment Succesful!");
		successLabel.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 15));
		successLabel.setBounds(147, 0, 98, 27);
		successfulPayment.add(successLabel);

		firstLinePayPal = new JLabel("£0 paid by using PayPal,");
		firstLinePayPal.setFont(new Font("Tw Cen MT", Font.PLAIN, 13));
		firstLinePayPal.setBounds(100, 21, 190, 58);
		successfulPayment.add(firstLinePayPal);

		secondLinePayPal = new JLabel("and the delivery address is [Address].");
		secondLinePayPal.setFont(new Font("Tw Cen MT", Font.PLAIN, 13));
		secondLinePayPal.setBounds(91, 58, 247, 21);
		successfulPayment.add(secondLinePayPal);

		JLabel addressLinePayPal = new JLabel("");
		addressLinePayPal.setIcon(new ImageIcon(UserPage.class.getResource("/resources/checkmark-32.png")));
		addressLinePayPal.setBounds(169, 107, 64, 42);
		successfulPayment.add(addressLinePayPal);

		JPanel Credit = new JPanel();
		Credit.setBackground(new Color(255, 255, 255));
		paymentPanes.addTab("Credit", null, Credit, null);
		Credit.setLayout(null);

		JLabel cardNumLabel = new JLabel("Card Number:");
		cardNumLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cardNumLabel.setBounds(10, 69, 99, 20);
		Credit.add(cardNumLabel);

		JLabel payment = new JLabel("Payment");
		payment.setBounds(165, 11, 71, 20);
		payment.setFont(new Font("Tahoma", Font.BOLD, 16));
		Credit.add(payment);

		cardNoInput = new JTextField();
		cardNoInput.setBounds(10, 90, 192, 20);
		Credit.add(cardNoInput);
		cardNoInput.setColumns(10);

		JLabel CVVLabel = new JLabel("CVV Code:");
		CVVLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		CVVLabel.setBounds(10, 121, 82, 20);
		Credit.add(CVVLabel);

		CVVInput = new JTextField();
		CVVInput.setBounds(10, 140, 96, 20);
		Credit.add(CVVInput);
		CVVInput.setColumns(10);

		JPanel panel = new JPanel();
		panel.setBounds(10, 232, 376, 163);
		Credit.add(panel);
		panel.setLayout(null);
		panel.setVisible(false);

		JLabel errorCVLabel = new JLabel("Enter a valid CVV Code.");
		errorCVLabel.setFont(new Font("Tw Cen MT", Font.PLAIN, 13));
		errorCVLabel.setForeground(new Color(255, 0, 0));
		errorCVLabel.setBounds(116, 141, 174, 17);
		Credit.add(errorCVLabel);
		errorCVLabel.setVisible(false);

		JLabel validCardLabel = new JLabel("Enter a valid card number.");
		validCardLabel.setForeground(new Color(255, 0, 0));
		validCardLabel.setFont(new Font("Tw Cen MT", Font.PLAIN, 13));
		validCardLabel.setBounds(212, 90, 174, 17);
		Credit.add(validCardLabel);
		validCardLabel.setVisible(false);

		JButton payingButton = new JButton("Pay");
		payingButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String card = cardNoInput.getText();
				String cvv = CVVInput.getText();
				boolean success = false;
				if (cvv.length() > 3 || cvv.length() < 3) {
					errorCVLabel.setVisible(true);
					if (card.length() > 6 || card.length() < 6) {
						validCardLabel.setVisible(true);
					}
				} else {
					if (card.length() > 6 || card.length() < 6) {
						validCardLabel.setVisible(true);
						errorCVLabel.setVisible(false);

					} else {
						panel.setVisible(true);
						errorCVLabel.setVisible(false);
						validCardLabel.setVisible(false);
						success = true;
					}
				}
				if (success)
					checkout();
			}
		});
		payingButton.setForeground(Color.BLACK);
		payingButton.setBackground(new Color(70, 130, 180));
		payingButton.setBounds(274, 163, 89, 23);
		Credit.add(payingButton);

		JLabel successPaymentCredit = new JLabel("Payment Successful!");
		successPaymentCredit.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		successPaymentCredit.setBounds(117, 0, 175, 28);
		panel.add(successPaymentCredit);

		firstLineCredit = new JLabel("£0 paid using Credit Card,");
		firstLineCredit.setFont(new Font("Tw Cen MT", Font.PLAIN, 13));
		firstLineCredit.setBounds(105, 50, 209, 14);
		panel.add(firstLineCredit);

		secondLineCredit = new JLabel("and delivery address is [Address].");
		secondLineCredit.setFont(new Font("Tw Cen MT", Font.PLAIN, 13));
		secondLineCredit.setBounds(105, 65, 204, 14);
		panel.add(secondLineCredit);

		JLabel addressLineCredit = new JLabel("\r\n");
		addressLineCredit.setIcon(new ImageIcon(UserPage.class.getResource("/resources/checkmark-32.png")));
		addressLineCredit.setBounds(170, 107, 66, 45);
		panel.add(addressLineCredit);

		totalCostLabel = new JLabel("Total:    $" + selectedUser.totalCost());
		totalCostLabel.setFont(new Font("Tw Cen MT", Font.PLAIN, 15));
		totalCostLabel.setBounds(643, 29, 146, 29);
		contentPane.add(totalCostLabel);

		SearchText.addCaretListener(e -> {

			updateStockTable(tableModel);

		});

	}

	private void updateStockTable(DefaultTableModel tableModel) {
		List<Products> products = selectedUser.getProductsInStock();

		String search = SearchText.getText();
		String searchbar = search.toLowerCase();

		tableModel.getDataVector().removeAllElements();

		products.removeIf(e -> (!e.getBrand().toLowerCase().contains(searchbar)));

		for (Products product : products) {
			String[] row = new String[9];
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
			row[7] = Float.toString(product.getRetail());
			if (row[1].equals("mouse")) {
				Mouse moice = (Mouse) product;
				row[8] = Integer.toString(moice.getButtons());
			} else {
				Keyboard keybrd = (Keyboard) product;
				row[8] = keybrd.getLayout();
			}
			tableModel.addRow(row);
		}
		tableModel.fireTableDataChanged();
	}

	private void checkout() {
		Database.checkoutItemsFromStock(selectedUser.getProductsInBasket());
		System.out.println(selectedUser.getProductsInBasket());
		selectedUser.clearBasket();
		updateBasket(basketTableModel);
		updateStockTable(tableModel);
	}

	private void updateBasket(DefaultTableModel basketTableModel) {

		List<Products> products = selectedUser.getProductsInBasket();
		float productsCost = selectedUser.totalCost();
		System.out.println(products);
		basketTableModel.getDataVector().removeAllElements();

		for (Products product : products) {
			String[] row = new String[9];
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
			row[7] = Float.toString(product.getRetail());
			if (row[1].equals("mouse")) {
				Mouse moice = (Mouse) product;
				row[8] = Integer.toString(moice.getButtons());
			} else {
				Keyboard keybrd = (Keyboard) product;
				row[8] = keybrd.getLayout();
			}
			basketTableModel.addRow(row);
			firstLinePayPal.setText("£" + df.format(productsCost) + " paid by using PayPal,");
			Address address = selectedUser.getAddress();
			secondLinePayPal.setText("and the delivery address is " + address.getHouseNumber() + ", "
					+ address.getPostcode() + ", " + address.getCity());
			firstLineCredit.setText("£" + df.format(productsCost) + " paid using Credit Card,");
			secondLineCredit.setText("and delivery address is " + address.getHouseNumber() + ", "
					+ address.getPostcode() + ", " + address.getCity());

			totalCostLabel.setText("Total:    £" + df.format(productsCost));

		}

		basketTableModel.fireTableDataChanged();
	}
}
