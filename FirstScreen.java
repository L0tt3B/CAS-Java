package cas;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class FirstScreen extends JFrame {

	private JPanel contentPane;
	private Users selectedUser;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FirstScreen frame = new FirstScreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws IOException
	 */
	public FirstScreen() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(FirstScreen.class.getResource("/resources/pear-24.png")));
		setTitle("Pear Technologies");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel loginLabel = new JLabel("LOGIN");
		loginLabel.setBounds(183, 81, 69, 23);
		loginLabel.setFont(new Font("SimSun-ExtB", Font.BOLD, 22));

		JComboBox usersComboBox = new JComboBox();
		usersComboBox.setBounds(173, 134, 101, 22);
		List<String> userNamesList = Users.getUserNames();
		String[] userNamesArray = new String[userNamesList.size()];
		for (int i = 0; i < userNamesArray.length; i++) {
			userNamesArray[i] = userNamesList.get(i);
		}

		usersComboBox.setModel(new DefaultComboBoxModel(userNamesArray));

		JLabel userLabel = new JLabel("Username:");
		userLabel.setBounds(102, 138, 61, 14);
		userLabel.setFont(new Font("Tahoma", Font.BOLD, 11));

		JButton signinButton = new JButton("Sign In");
		signinButton.setBounds(185, 174, 67, 23);
		signinButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Users> userList = Database.getUserList();
				// System.out.println(userList);
				selectedUser = userList.get(usersComboBox.getSelectedIndex());
				System.out.println(selectedUser.getUser());
				if (usersComboBox.getSelectedItem().equals(userNamesArray[0])) {
					AdminPage admin = new AdminPage((Admin) selectedUser);
					admin.setVisible(true);
					admin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					dispose();
				} else {
					UserPage user = new UserPage((Customer) selectedUser);

					user.setVisible(true);
					user.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					dispose();
				}
			}
		});

		JLabel pearLogo = new JLabel("");
		pearLogo.setBounds(195, 16, 50, 71);
		pearLogo.setIcon(new ImageIcon(FirstScreen.class.getResource("/resources/pear-32.png")));
		contentPane.setLayout(null);
		contentPane.add(userLabel);
		contentPane.add(usersComboBox);
		contentPane.add(signinButton);
		contentPane.add(loginLabel);
		contentPane.add(pearLogo);
	}
}
