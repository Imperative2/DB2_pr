package application;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import java.awt.SystemColor;
import javax.swing.JTabbedPane;

public class App
{

	private static DatabaseConnection dbConn;
	
	private User mainUser;

	private JFrame mainFrame;
	private CardLayout cards;

	private JPanel loginPanel;
	private static String logPanelID = "LOGGINPANEL";

	private JTextField textFieldLogin;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		dbConn = new DatabaseConnection();

		EventQueue.invokeLater(new Runnable() {
			public void run()
			{
				try
				{
					App window = new App();
					window.mainFrame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public App()
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		mainFrame = new JFrame();
		mainFrame.setBackground(SystemColor.menu);
		mainFrame.setForeground(Color.RED);
		mainFrame.setTitle("Uczelniany system zapis\u00F3w");
		mainFrame.setBounds(100, 100, 800, 500);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.getContentPane().setLayout(new CardLayout(0, 0));
		
		cards = (CardLayout)(mainFrame.getContentPane().getLayout());

		initLoginPanel();

		JPanel userPanel = new JPanel();
		userPanel.setBackground(new Color(204, 255, 102));
		mainFrame.getContentPane().add(userPanel, "name_250624064305667");
		userPanel.setLayout(null);

		JLabel fixLabelName = new JLabel("Imi\u0119:");
		fixLabelName.setFont(new Font("Arial", Font.PLAIN, 16));
		fixLabelName.setBounds(12, 25, 33, 16);
		userPanel.add(fixLabelName);

		JLabel labelName = new JLabel("name");
		labelName.setFont(new Font("Arial", Font.PLAIN, 16));
		labelName.setBounds(66, 25, 95, 16);
		userPanel.add(labelName);

		JLabel fixLabelSurname = new JLabel("Nazwisko:");
		fixLabelSurname.setFont(new Font("Arial", Font.PLAIN, 16));
		fixLabelSurname.setBounds(186, 25, 81, 16);
		userPanel.add(fixLabelSurname);

		JLabel labelSurname = new JLabel("Surname");
		labelSurname.setFont(new Font("Arial", Font.PLAIN, 16));
		labelSurname.setBounds(266, 25, 95, 16);
		userPanel.add(labelSurname);

		JLabel fixLabelStudentIndex = new JLabel("Indeks:");
		fixLabelStudentIndex.setFont(new Font("Arial", Font.PLAIN, 16));
		fixLabelStudentIndex.setBounds(12, 54, 56, 16);
		userPanel.add(fixLabelStudentIndex);

		JLabel labelStudentIndex = new JLabel("studentIndex");
		labelStudentIndex.setFont(new Font("Arial", Font.PLAIN, 16));
		labelStudentIndex.setBounds(66, 54, 95, 16);
		userPanel.add(labelStudentIndex);

		JLabel fixLabelFieldStudy = new JLabel("Kierunek:");
		fixLabelFieldStudy.setFont(new Font("Arial", Font.PLAIN, 16));
		fixLabelFieldStudy.setBounds(186, 54, 81, 16);
		userPanel.add(fixLabelFieldStudy);

		JLabel labelFieldStudy = new JLabel("field of study");
		labelFieldStudy.setFont(new Font("Arial", Font.PLAIN, 16));
		labelFieldStudy.setBounds(266, 54, 95, 16);
		userPanel.add(labelFieldStudy);

		JLabel fixLabelSemester = new JLabel("Semestr:");
		fixLabelSemester.setFont(new Font("Arial", Font.PLAIN, 16));
		fixLabelSemester.setBounds(385, 54, 72, 16);
		userPanel.add(fixLabelSemester);

		JLabel labelSemester = new JLabel("studentSemester");
		labelSemester.setFont(new Font("Arial", Font.PLAIN, 16));
		labelSemester.setBounds(457, 54, 81, 16);
		userPanel.add(labelSemester);

		JLabel fixLabelTime = new JLabel("Czas:");
		fixLabelTime.setFont(new Font("Arial", Font.BOLD, 16));
		fixLabelTime.setBounds(573, 25, 56, 16);
		userPanel.add(fixLabelTime);

		JLabel labelTime = new JLabel("11-11-19 16:34:16");
		labelTime.setFont(new Font("Arial", Font.BOLD, 16));
		labelTime.setBounds(626, 26, 134, 16);
		userPanel.add(labelTime);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 83, 782, 371);
		userPanel.add(tabbedPane);

		JPanel panel = new JPanel();
		tabbedPane.addTab("New tab", null, panel, null);

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_1, null);

		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_3, null);

		JPanel employeePanel = new JPanel();
		employeePanel.setBackground(new Color(255, 51, 51));
		mainFrame.getContentPane().add(employeePanel, "name_256955698370337");

		JLayeredPane layeredPane = new JLayeredPane();
		employeePanel.add(layeredPane);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(-195, 0, 438, 307);
		layeredPane.add(panel_2);
	}

	private void initLoginPanel()
	{
		loginPanel = new JPanel();
		loginPanel.setBackground(new Color(0, 255, 102));
		mainFrame.getContentPane().add(loginPanel, logPanelID);
		loginPanel.setLayout(null);

		JLabel fixLabelLogin = new JLabel("Podaj Login:");
		fixLabelLogin.setFont(new Font("Arial", Font.BOLD, 16));
		fixLabelLogin.setBounds(209, 121, 110, 25);
		loginPanel.add(fixLabelLogin);

		textFieldLogin = new JTextField();
		textFieldLogin.setBounds(331, 123, 266, 22);
		loginPanel.add(textFieldLogin);
		textFieldLogin.setColumns(10);

		JLabel fixLabelPassword = new JLabel("Podaj Has\u0142o:");
		fixLabelPassword.setFont(new Font("Arial", Font.BOLD, 16));
		fixLabelPassword.setBounds(209, 164, 98, 16);
		loginPanel.add(fixLabelPassword);

		passwordField = new JPasswordField();
		passwordField.setBounds(331, 158, 266, 22);
		loginPanel.add(passwordField);

		JButton btnLogin = new JButton("Zaloguj");
		btnLogin.setFont(new Font("Arial", Font.PLAIN, 16));
		btnLogin.setBounds(331, 205, 97, 25);
		loginPanel.add(btnLogin);

		JButton btnForgotPassword = new JButton("Zapomnia\u0142em has\u0142a");
		btnForgotPassword.setFont(new Font("Arial", Font.PLAIN, 16));
		btnForgotPassword.setBounds(357, 243, 211, 25);
		loginPanel.add(btnForgotPassword);

		JButton btnExit = new JButton("Wyjd\u017A");
		btnExit.setFont(new Font("Arial", Font.PLAIN, 16));
		btnExit.setBounds(500, 206, 97, 25);
		loginPanel.add(btnExit);

		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				if(dbConn.connect() == false)
				{
					JOptionPane.showMessageDialog(mainFrame, "Nie mo¿na po³¹czyæ siê do bazy danych",
							"Uwaga!", JOptionPane.ERROR_MESSAGE);
				}
				LoggingControler logControler = new LoggingControler(dbConn);
				String login = textFieldLogin.getText();
				String password = String.valueOf(passwordField.getPassword());
				System.out.println("login: "+ login+" haslo: "+ password);
				int logResult;
				logResult = logControler.login(login, password);
				System.out.println(logResult);
				
				switch(logResult)
				{
					case 1:
					{
						break;
					}
					case 2:
					{
						passwordField.setText("");
						textFieldLogin.setText("");
						mainUser = logControler.getLoggedUser();
						cards.show(mainFrame.getContentPane(), "name_250624064305667");
						
						break;
					}
					case -1:
					{
						JOptionPane.showMessageDialog(mainFrame, "Z³y login lub has³o",
								"Uwaga!", JOptionPane.ERROR_MESSAGE);
						break;
					}
					default:
					{
						JOptionPane.showMessageDialog(mainFrame, "B³¹d podczas logowania",
								"Uwaga!", JOptionPane.ERROR_MESSAGE);
						break;
					}
				}
				
			}
		});

		btnForgotPassword.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e)
			{
				JOptionPane.showMessageDialog(mainFrame, "W celu odzyskanie has³o zg³oœ siê do administratora systemu.",
						"Uwaga!", JOptionPane.WARNING_MESSAGE);
			}
		});

		btnExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e)
			{
				dbConn.disconnect();
				System.exit(0);
			}
		});
	}
}
