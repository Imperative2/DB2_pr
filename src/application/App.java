package application;

import application.models.Group;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Color;

import javax.swing.DefaultListModel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.beans.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import javax.swing.JOptionPane;

import java.awt.SystemColor;
import javax.swing.JTabbedPane;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Controllers.AdminPanelRightsController;
import Controllers.UserAdmissionController;

import javax.swing.JTextPane;
import javax.swing.JComboBox;
import javax.swing.JLayeredPane;
import javax.swing.JCheckBox;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JRadioButton;

public class App
{

	private static DatabaseConnection dbConn;
	
	private User mainUser;

	private JFrame mainFrame;
	private CardLayout cards;

	private JPanel loginPanel;
	private final static String LOG_PANEL_ID = "LOGGINPANEL";
	
	private JPanel userPanel;
	private final static String USER_PANEL_ID = "USERPANEL";
	private UserPanelControler userPanelControler;
	private UserAdmissionController userAdmissionController;
	private UserAdmissionController userAdmissionControllerSaves;
	private Map<String,Integer> userComponentsMap;
	private JPanel panelUserGroups;
	private UserPanelGroupsController userPanelGroupsController;
	private UserAdmissionPanel panelUserAdmissionGroups = new UserAdmissionPanel();
	private UserAdmissionPanel panelUserAdmissionSaves = new UserAdmissionPanel();
	private Map<String,Integer> userGroupsComponentsMap;
	
	private TimerAdmissionTime timerAdmissionTime;
	
	private JPanel employeePanel;
	private final static String EMPLOYEE_PANEL_ID = "EMPLOYEEPANEL";
	private EmployeePanelControler employeePanelControler;
	private Map<String,Integer> employeeComponentsMap;
	private JLabel labelAdmissionTime, labelAdmissionRight;
	private JTextField txtYyyymmddHhmmss;
	private JTextField textFieldCoursesId;
	private JTextField textFieldCoursesName;
	private JTextField textFieldCoursesECTS;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;


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
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setResizable(false);
		mainFrame.getContentPane().setLayout(new CardLayout(0, 0));
		
		cards = (CardLayout)(mainFrame.getContentPane().getLayout());

		initLoginPanel();
		
		initUserPanel();

		initEmployeePanel();


	}

	private void initLoginPanel()
	{
		loginPanel = new JPanel();
		loginPanel.setBackground(new Color(0, 255, 102));
		mainFrame.getContentPane().add(loginPanel, LOG_PANEL_ID);
		loginPanel.setLayout(null);

		JLabel fixLabelLogin = new JLabel("Podaj Login:");
		fixLabelLogin.setFont(new Font("Arial", Font.BOLD, 16));
		fixLabelLogin.setBounds(177, 121, 110, 25);
		loginPanel.add(fixLabelLogin);

		JTextField textFieldLogin = new JTextField("pwr235011");
		textFieldLogin.setBounds(285, 123, 266, 22);
		loginPanel.add(textFieldLogin);
		textFieldLogin.setColumns(10);

		JLabel fixLabelPassword = new JLabel("Podaj Has\u0142o:");
		fixLabelPassword.setFont(new Font("Arial", Font.BOLD, 16));
		fixLabelPassword.setBounds(177, 160, 98, 16);
		loginPanel.add(fixLabelPassword);

		JPasswordField passwordField = new JPasswordField("haslo");
		passwordField.setBounds(285, 159, 266, 22);
		loginPanel.add(passwordField);

		JButton btnLogin = new JButton("Zaloguj");
		btnLogin.setFont(new Font("Arial", Font.PLAIN, 16));
		btnLogin.setBounds(285, 205, 97, 25);
		loginPanel.add(btnLogin);

		JButton btnForgotPassword = new JButton("Zapomnia\u0142em has\u0142a");
		btnForgotPassword.setFont(new Font("Arial", Font.PLAIN, 16));
		btnForgotPassword.setBounds(311, 243, 211, 25);
		loginPanel.add(btnForgotPassword);

		JButton btnExit = new JButton("Wyjd\u017A");
		btnExit.setFont(new Font("Arial", Font.PLAIN, 16));
		btnExit.setBounds(450, 206, 97, 25);
		loginPanel.add(btnExit);

		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				if(dbConn.connect() == false)
				{
					JOptionPane.showMessageDialog(mainFrame, "Nie mo?na po??czy? si? do bazy danych",
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
						passwordField.setText("");
						textFieldLogin.setText("");
						mainUser = logControler.getLoggedUser();
						userPanelControler = new UserPanelControler(dbConn, userComponentsMap);
						userPanelControler.setMainUser((Student)mainUser);
						userPanelControler.updateUserPanel(userPanel);
						userAdmissionController = new UserAdmissionController(dbConn, panelUserAdmissionGroups, timerAdmissionTime);
						userAdmissionController.setMainUser((Student)mainUser);
						userAdmissionController.updateAdmissionPanel();
						userAdmissionControllerSaves = new UserAdmissionController(dbConn, panelUserAdmissionSaves, timerAdmissionTime);
						userAdmissionControllerSaves.setMainUser((Student)mainUser);
						userAdmissionControllerSaves.updateAdmissionPanel();
						userPanelGroupsController = new UserPanelGroupsController(dbConn, userGroupsComponentsMap);
						userPanelGroupsController.setMainUser((Student)mainUser);
						userPanelGroupsController.updateUserECTS(panelUserGroups);
						cards.show(mainFrame.getContentPane(), USER_PANEL_ID);
						if(((Student)mainUser).getAdmissionRight().equals("posiada")){
							labelAdmissionRight.setForeground(Color.GREEN);
						}else{
							labelAdmissionRight.setForeground(Color.RED);
						}
						labelAdmissionTime.setText(((Student)mainUser).getAdmissionTime());
						timerAdmissionTime = new TimerAdmissionTime(((Student)mainUser).getGregTime(), labelAdmissionTime, 10000, panelUserAdmissionSaves);
						labelAdmissionRight.setText(((Student)mainUser).getAdmissionRight());
						break;
					}
					case 2:
					{
						passwordField.setText("");
						textFieldLogin.setText("");
						mainUser = logControler.getLoggedUser();
						
						employeePanelControler = new EmployeePanelControler(dbConn, employeeComponentsMap);
						employeePanelControler.setMainUser((Employee)mainUser);
						employeePanelControler.updateEmployeePanel(employeePanel);
						
						cards.show(mainFrame.getContentPane(), EMPLOYEE_PANEL_ID);
						
						break;
					}
					case -1:
					{
						JOptionPane.showMessageDialog(mainFrame, "Z?y login lub has?o",
								"Uwaga!", JOptionPane.ERROR_MESSAGE);
						break;
					}
					default:
					{
						JOptionPane.showMessageDialog(mainFrame, "B??d podczas logowania",
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
				JOptionPane.showMessageDialog(mainFrame, "W celu odzyskanie has?o zg?o? si? do administratora systemu.",
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

	private void initUserPanel()
	{

		userPanel = new JPanel();
		userPanel.setBackground(new Color(204, 255, 102));
		mainFrame.getContentPane().add(userPanel, USER_PANEL_ID);
		userPanel.setLayout(null);
		
		userComponentsMap = new HashMap<>();

		JLabel fixLabelName = new JLabel("Imi\u0119:");
		fixLabelName.setFont(new Font("Arial", Font.PLAIN, 16));
		fixLabelName.setBounds(12, 25, 33, 16);
		userPanel.add(fixLabelName,0);
		userComponentsMap.put("fixLabelName", 0);

		JLabel labelName = new JLabel("name");
		labelName.setFont(new Font("Arial", Font.PLAIN, 16));
		labelName.setBounds(66, 25, 95, 16);
		userComponentsMap.put("labelName", 1);
		userPanel.add(labelName,1);

		JLabel fixLabelSurname = new JLabel("Nazwisko:");
		fixLabelSurname.setFont(new Font("Arial", Font.PLAIN, 16));
		fixLabelSurname.setBounds(186, 25, 81, 16);
		userComponentsMap.put("fixLabelSurname", 2);
		userPanel.add(fixLabelSurname,2);

		JLabel labelSurname = new JLabel("Surname");
		labelSurname.setFont(new Font("Arial", Font.PLAIN, 16));
		labelSurname.setBounds(266, 25, 160, 16);
		userComponentsMap.put("labelSurname", 3);
		userPanel.add(labelSurname,3);

		JLabel fixLabelStudentIndex = new JLabel("Indeks:");
		fixLabelStudentIndex.setFont(new Font("Arial", Font.PLAIN, 16));
		fixLabelStudentIndex.setBounds(12, 54, 56, 16);
		userComponentsMap.put("fixLabelStudentIndex", 4);
		userPanel.add(fixLabelStudentIndex,4);

		JLabel labelStudentIndex = new JLabel("studentIndex");
		labelStudentIndex.setFont(new Font("Arial", Font.PLAIN, 16));
		labelStudentIndex.setBounds(66, 54, 95, 16);
		userComponentsMap.put("labelStudentIndex", 5);
		userPanel.add(labelStudentIndex,5);

		JLabel fixLabelFieldStudy = new JLabel("Kierunek:");
		fixLabelFieldStudy.setFont(new Font("Arial", Font.PLAIN, 16));
		fixLabelFieldStudy.setBounds(186, 54, 81, 16);
		userComponentsMap.put("fixLabelFieldStudy", 6);
		userPanel.add(fixLabelFieldStudy,6);

		JLabel labelFieldStudy = new JLabel("field of study");
		labelFieldStudy.setFont(new Font("Arial", Font.PLAIN, 16));
		labelFieldStudy.setBounds(266, 54, 95, 16);
		userComponentsMap.put("labelFieldStudy", 7);
		userPanel.add(labelFieldStudy,7);

		JLabel fixLabelSemester = new JLabel("Semestr:");
		fixLabelSemester.setFont(new Font("Arial", Font.PLAIN, 16));
		fixLabelSemester.setBounds(385, 54, 72, 16);
		userComponentsMap.put("fixLabelSemester", 8);
		userPanel.add(fixLabelSemester,8);

		JLabel labelSemester = new JLabel("studentSemester");
		labelSemester.setFont(new Font("Arial", Font.PLAIN, 16));
		labelSemester.setBounds(457, 54, 81, 16);
		userComponentsMap.put("labelSemester", 9);
		userPanel.add(labelSemester,9);

		JLabel fixLabelTime = new JLabel("Czas:");
		fixLabelTime.setFont(new Font("Arial", Font.BOLD, 16));
		fixLabelTime.setBounds(546, 25, 56, 16);
		userPanel.add(fixLabelTime);

		JLabel labelTime = new JLabel("11-11-2019 16:34:16");
		labelTime.setFont(new Font("Arial", Font.BOLD, 16));
		labelTime.setBounds(595, 25, 187, 16);
		userPanel.add(labelTime);
		
		MyClock myClock = new MyClock(labelTime);
		Timer timer = new Timer();
		timer.schedule(myClock, 800);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(12, 83, 770, 370);
		userPanel.add(tabbedPane);

		tabbedPane.addTab("Zapisy", null, panelUserAdmissionSaves, null);
		panelUserAdmissionSaves.setLayout(null);

		
		
		

		
		
		
		
		
		
		
		DefaultListModel<UserGroupModel> listModel = new DefaultListModel<>();

		
		
		userGroupsComponentsMap = new HashMap<>();
		
		panelUserGroups = new JPanel();
		tabbedPane.addTab("Zapisany do grup", null, panelUserGroups, null);
		panelUserGroups.setLayout(null);
		
		
		
		
		tabbedPane.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent arg0)
			{
				listModel.clear();
				UserGroupModel emptyModel = new UserGroupModel(dbConn);
				List<String[]> elements = emptyModel.getData();
				userPanelGroupsController.updateUserECTS(panelUserGroups);
				for(String[] element : elements)
				{
					if(element.length > 10)
					{
						if(element[10].equals(mainUser.getUserId()))
						{
							UserGroupModel item = new UserGroupModel(element);
							listModel.addElement(item);
						}
					}

				}	
				
			}
		});
		
		JLabel fixLabelUserECTS = new JLabel("Liczba punkt\u00F3w ECTS:");
		fixLabelUserECTS.setFont(new Font("Arial", Font.PLAIN, 16));
		fixLabelUserECTS.setBounds(12, 13, 158, 16);
		panelUserGroups.add(fixLabelUserECTS,0);
		userGroupsComponentsMap.put("fixLabelUserECTS", 0);
		
		JLabel labelUserECTS = new JLabel("30");
		labelUserECTS.setFont(new Font("Arial", Font.PLAIN, 16));
		labelUserECTS.setBounds(182, 14, 35, 16);
		panelUserGroups.add(labelUserECTS,1);
		userGroupsComponentsMap.put("labelUserECTS", 1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 42, 180, 298);
		panelUserGroups.add(scrollPane);

		JList<UserGroupModel> list = new JList<>(listModel);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(list);
		list.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e)
			{
					UserGroupModel groupModel = list.getSelectedValue();
					if(groupModel != null)
					{
						groupModel.addComponentsMap(userGroupsComponentsMap);
						groupModel.updateGroupModel(panelUserGroups);	
					}	
			}
		});
		
		JLabel fixLabelGroupCourseName = new JLabel("Nazwa kursu:");
		fixLabelGroupCourseName.setFont(new Font("Arial", Font.PLAIN, 16));
		fixLabelGroupCourseName.setBounds(204, 68, 101, 16);
		panelUserGroups.add(fixLabelGroupCourseName,2);
		userGroupsComponentsMap.put("fixLabelGroupCourseName", 2);
		
		JLabel labelGroupCourseName = new JLabel("---.---");
		labelGroupCourseName.setFont(new Font("Arial", Font.BOLD, 16));
		labelGroupCourseName.setBounds(301, 68, 201, 16);
		panelUserGroups.add(labelGroupCourseName,3);
		userGroupsComponentsMap.put("labelGroupCourseName", 3);
		
		JLabel fixLabelGroupForm = new JLabel("Forma zaj\u0119\u0107:");
		fixLabelGroupForm.setFont(new Font("Arial", Font.PLAIN, 16));
		fixLabelGroupForm.setBounds(204, 107, 101, 16);
		panelUserGroups.add(fixLabelGroupForm,4);
		userGroupsComponentsMap.put("fixLabelGroupForm", 4);
		
		
		JLabel labelGroupForm = new JLabel("---.---");
		labelGroupForm.setFont(new Font("Arial", Font.PLAIN, 16));
		labelGroupForm.setBounds(306, 107, 101, 16);
		panelUserGroups.add(labelGroupForm,5);
		userGroupsComponentsMap.put("labelGroupForm", 5);
		
		JLabel fixLabelGroupECTS = new JLabel("ECTS:");
		fixLabelGroupECTS.setFont(new Font("Arial", Font.PLAIN, 16));
		fixLabelGroupECTS.setBounds(519, 68, 56, 16);
		panelUserGroups.add(fixLabelGroupECTS,6);
		userGroupsComponentsMap.put("fixLabelGroupECTS", 6);
		
		
		JLabel labelGroupECTS = new JLabel("--.--");
		labelGroupECTS.setFont(new Font("Arial", Font.BOLD, 16));
		labelGroupECTS.setBounds(571, 68, 76, 16);
		panelUserGroups.add(labelGroupECTS,7);
		userGroupsComponentsMap.put("labelGroupECTS", 7);
		
		JLabel fixLabelGroupCode = new JLabel("Kod grupy:");
		fixLabelGroupCode.setFont(new Font("Arial", Font.PLAIN, 16));
		fixLabelGroupCode.setBounds(469, 107, 83, 18);
		panelUserGroups.add(fixLabelGroupCode,8);
		userGroupsComponentsMap.put("fixLabelGroupCode", 8);
		
		JLabel labelGroupCode = new JLabel("---.---");
		labelGroupCode.setFont(new Font("Arial", Font.PLAIN, 16));
		labelGroupCode.setBounds(549, 107, 83, 18);
		panelUserGroups.add(labelGroupCode,9);
		userGroupsComponentsMap.put("labelGroupCode", 9);
		
		
		JLabel fixLabelGroupTeacher = new JLabel("Prowadz\u0105cy:");
		fixLabelGroupTeacher.setFont(new Font("Arial", Font.PLAIN, 16));
		fixLabelGroupTeacher.setBounds(204, 146, 101, 16);
		panelUserGroups.add(fixLabelGroupTeacher,10);
		userGroupsComponentsMap.put("fixLabelGroupTeacher", 10);
		
		JLabel labelGroupTeacher = new JLabel("---.---");
		labelGroupTeacher.setFont(new Font("Arial", Font.PLAIN, 16));
		labelGroupTeacher.setBounds(301, 146, 283, 16);
		panelUserGroups.add(labelGroupTeacher,11);
		userGroupsComponentsMap.put("labelGroupTeacher", 11);
		
		JLabel fixLabelGroupDay = new JLabel("Dzie\u0144 zaj\u0119c:");
		fixLabelGroupDay.setFont(new Font("Arial", Font.PLAIN, 16));
		fixLabelGroupDay.setBounds(421, 185, 95, 16);
		panelUserGroups.add(fixLabelGroupDay,12);
		userGroupsComponentsMap.put("fixLabelGroupDay", 12);
		
		JLabel labelGroupDay = new JLabel("---.---");
		labelGroupDay.setFont(new Font("Arial", Font.PLAIN, 16));
		labelGroupDay.setBounds(510, 185, 119, 16);
		panelUserGroups.add(labelGroupDay,13);
		userGroupsComponentsMap.put("labelGroupDay", 13);
		
		
		JLabel fixLabelGroupTime = new JLabel("Godziny Zaj\u0119\u0107");
		fixLabelGroupTime.setFont(new Font("Arial", Font.PLAIN, 16));
		fixLabelGroupTime.setBounds(421, 225, 193, 16);
		panelUserGroups.add(fixLabelGroupTime,14);
		userGroupsComponentsMap.put("fixLabelGroupTime", 14);
		
		JLabel labelGroupTime = new JLabel("---.---");
		labelGroupTime.setFont(new Font("Arial", Font.PLAIN, 16));
		labelGroupTime.setBounds(528, 225, 150, 16);
		panelUserGroups.add(labelGroupTime,15);
		userGroupsComponentsMap.put("labelGroupTime", 15);
		
		JLabel fixLabelGroupWeek = new JLabel("Parzysto\u015B\u0107 tygodnia:");
		fixLabelGroupWeek.setFont(new Font("Arial", Font.PLAIN, 16));
		fixLabelGroupWeek.setBounds(204, 185, 151, 16);
		panelUserGroups.add(fixLabelGroupWeek,16);
		userGroupsComponentsMap.put("fixLabelGroupWeek", 16);
		
		JLabel labelGroupWeek = new JLabel("--.--");
		labelGroupWeek.setFont(new Font("Arial", Font.PLAIN, 16));
		labelGroupWeek.setBounds(351, 185, 56, 16);
		panelUserGroups.add(labelGroupWeek,17);
		userGroupsComponentsMap.put("labelGroupWeek", 17);
		
		JLabel fixLabelGroupRoom = new JLabel("Sala zaj\u0119ciowa:");
		fixLabelGroupRoom.setFont(new Font("Arial", Font.PLAIN, 16));
		fixLabelGroupRoom.setBounds(204, 225, 119, 16);
		panelUserGroups.add(fixLabelGroupRoom,18);
		userGroupsComponentsMap.put("fixLabelGroupRoom", 18);
		
		JLabel labelGroupRoom = new JLabel("---.---");
		labelGroupRoom.setFont(new Font("Arial", Font.PLAIN, 16));
		labelGroupRoom.setBounds(314, 225, 95, 16);
		panelUserGroups.add(labelGroupRoom,19);
		userGroupsComponentsMap.put("labelGroupRoom", 19);
		
		
		JButton buttonGroupUnsub = new JButton("Wypisz");
		buttonGroupUnsub.setBounds(642, 289, 97, 25);
		panelUserGroups.add(buttonGroupUnsub,20);
		buttonGroupUnsub.setVisible(false);
		userGroupsComponentsMap.put("buttonGroupUnsub", 20);
		
		buttonGroupUnsub.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				UserGroupModel groupModel = list.getSelectedValue();
				String zapisId = groupModel.getZapisId();
				System.out.println(zapisId);
				JButton button = (JButton)e.getSource();

				if(JOptionPane.showConfirmDialog(null, "Czy na pewno chcesz si� wypisa� z tej grupy?", "Je�te� pewnien?", JOptionPane.OK_CANCEL_OPTION)==0){

					String sql_query = "DELETE FROM `zapis` WHERE `zapis`.`id_zapisu` =  " + zapisId + ";";
					dbConn.deleteOrUpdateData(sql_query);
					button.setVisible(false);

					listModel.clear();
					UserGroupModel emptyModel = new UserGroupModel(dbConn);
					List<String[]> elements = emptyModel.getData();
					for(String[] element : elements)
					{
						if(element.length > 10)
						{
							if(element[10].equals(mainUser.getUserId()))
							{
								UserGroupModel item = new UserGroupModel(element);
								listModel.addElement(item);
							}
						}

					}
					userPanelGroupsController.updateUserECTS(panelUserGroups);
					JOptionPane.showMessageDialog(mainFrame, "Pomy�lnie usuni�to zapis.",
							"Informacja!", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});

		tabbedPane.addTab("Przegl�daj daj grupy", null, panelUserAdmissionGroups, null);
		panelUserAdmissionGroups.setLayout(null);

		JLabel fixLabelAdmissionTime = new JLabel("Czas zapisow:");
		fixLabelAdmissionTime.setFont(new Font("Arial", Font.PLAIN, 16));
		fixLabelAdmissionTime.setBounds(10, 10, 120, 16);
		panelUserAdmissionSaves.add(fixLabelAdmissionTime,19);

		JLabel fixLabelAdmissionRight = new JLabel("Prawo do zapisow:");
		fixLabelAdmissionRight.setFont(new Font("Arial", Font.PLAIN, 16));
		fixLabelAdmissionRight.setBounds(350, 10, 150, 16);
		panelUserAdmissionSaves.add(fixLabelAdmissionRight,19);

		labelAdmissionTime = new JLabel("-- . --");
		labelAdmissionTime.setFont(new Font("Arial", Font.BOLD, 16));
		labelAdmissionTime.setBounds(113, 10, 200, 16);
		panelUserAdmissionSaves.add(labelAdmissionTime,19);

		labelAdmissionRight = new JLabel("-- . --");
		labelAdmissionRight.setFont(new Font("Arial", Font.BOLD, 16));
		labelAdmissionRight.setBounds(500, 10, 95, 16);
		panelUserAdmissionSaves.add(labelAdmissionRight,19);

	}

	private void initEmployeePanel()
	{
		employeePanel = new JPanel();
		employeePanel.setBackground(new Color(255, 102, 102));
		mainFrame.getContentPane().add(employeePanel, EMPLOYEE_PANEL_ID);
		employeePanel.setLayout(null);
		
		employeeComponentsMap = new HashMap<>();
		
		JLabel fixLabelName = new JLabel("Imi\u0119:");
		fixLabelName.setFont(new Font("Arial", Font.PLAIN, 16));
		fixLabelName.setBounds(12, 23, 40, 16);
		employeePanel.add(fixLabelName,0);
		employeeComponentsMap.put("fixLabelName", 0);
		
		JLabel labelName = new JLabel("Name");
		labelName.setFont(new Font("Arial", Font.PLAIN, 16));
		labelName.setBounds(50, 24, 105, 16);
		employeePanel.add(labelName,1);
		employeeComponentsMap.put("labelName", 1);
		
		JLabel fixLabelSurname = new JLabel("Nazwisko:");
		fixLabelSurname.setFont(new Font("Arial", Font.PLAIN, 16));
		fixLabelSurname.setBounds(167, 24, 81, 16);
		employeePanel.add(fixLabelSurname,2);
		employeeComponentsMap.put("fixLabelSurname", 2);
		
		JLabel labelSurname = new JLabel("Surname");
		labelSurname.setFont(new Font("Arial", Font.PLAIN, 16));
		labelSurname.setBounds(247, 24, 105, 16);
		employeePanel.add(labelSurname,3);
		employeeComponentsMap.put("labelSurname", 3);
		
		JLabel fixLabelTime = new JLabel("Czas:");
		fixLabelTime.setFont(new Font("Arial", Font.BOLD, 16));
		fixLabelTime.setBounds(500, 23, 56, 16);
		employeePanel.add(fixLabelTime);
		
		JLabel labelTime = new JLabel("11-11-2019 16:43:12");
		labelTime.setFont(new Font("Arial", Font.BOLD, 16));
		labelTime.setBounds(563, 24, 161, 16);
		employeePanel.add(labelTime);
		
		MyClock myClock = new MyClock(labelTime);
		Timer timer = new Timer();
		timer.schedule(myClock, 800);
		
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setBounds(12, 57, 770, 396);
		employeePanel.add(tabbedPane_1);
		
		AdminPanelRights panelRights = new AdminPanelRights(mainFrame);
		tabbedPane_1.addTab("Prawa i terminy zapis�w student�w", null, panelRights, null);
		
		AdminPanelRightsController adminPanelRightsController = new AdminPanelRightsController(dbConn, panelRights);
		adminPanelRightsController.loadStudents();

		panelRights.setController(adminPanelRightsController);
		
		
		
		
		
		
		
		
		
		
		JPanel panelGroups = new JPanel();
		tabbedPane_1.addTab("Zarz�dzaj grupami", null, panelGroups, null);
		
		JScrollPane scrollPane_8 = new JScrollPane();
		scrollPane_8.setBounds(12, 28, 173, 325);
		
		JList list_8 = new JList();
		scrollPane_8.setViewportView(list_8);
		
		JScrollPane scrollPane_9 = new JScrollPane();
		scrollPane_9.setBounds(197, 28, 173, 325);
		
		JList list_9 = new JList();
		scrollPane_9.setViewportView(list_9);
		
		JLabel lblKurs = new JLabel("Kursy:");
		lblKurs.setFont(new Font("Arial", Font.BOLD, 16));
		lblKurs.setBounds(12, 13, 56, 16);
		
		JLabel lblNewLabel_1 = new JLabel("Grupy:");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_1.setBounds(197, 13, 56, 16);
		
		JButton btnStwrzNowGrup = new JButton("Stw\u00F3rz now\u0105 grup\u0119");
		btnStwrzNowGrup.setBounds(526, 9, 145, 25);
		
		JButton btnModyfikuj_1 = new JButton("Modyfikuj ");
		btnModyfikuj_1.setBounds(656, 328, 97, 25);
		
		JButton btnUsuGrup = new JButton("Usu\u0144 grup\u0119");
		btnUsuGrup.setBounds(382, 332, 97, 16);
		btnUsuGrup.setForeground(Color.RED);
		
		JLabel lblNewLabel_11 = new JLabel("Kod Grupy:");
		lblNewLabel_11.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel_11.setBounds(382, 49, 78, 16);
		
		textField_3 = new JTextField();
		textField_3.setBounds(464, 47, 116, 22);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_12 = new JLabel("Prowadz\u0105cy:");
		lblNewLabel_12.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel_12.setBounds(382, 92, 103, 16);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(474, 90, 123, 22);
		
		JLabel lblNewLabel_13 = new JLabel("Godziny zaj\u0119\u0107:");
		lblNewLabel_13.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel_13.setBounds(382, 137, 103, 16);
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setBounds(484, 135, 133, 22);
		
		JLabel lblNewLabel_14 = new JLabel("Dzie\u0144 tygodnia:");
		lblNewLabel_14.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel_14.setBounds(382, 168, 122, 16);
		
		JComboBox comboBox_4 = new JComboBox();
		comboBox_4.setBounds(494, 170, 123, 22);
		
		JLabel lblNewLabel_15 = new JLabel("Parzysto\u015B\u0107 tygodznia:");
		lblNewLabel_15.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel_15.setBounds(382, 204, 150, 18);
		panelGroups.setLayout(null);
		panelGroups.add(scrollPane_8);
		panelGroups.add(scrollPane_9);
		panelGroups.add(lblKurs);
		panelGroups.add(lblNewLabel_1);
		panelGroups.add(btnStwrzNowGrup);
		panelGroups.add(btnModyfikuj_1);
		panelGroups.add(btnUsuGrup);
		panelGroups.add(lblNewLabel_11);
		panelGroups.add(textField_3);
		panelGroups.add(lblNewLabel_12);
		panelGroups.add(comboBox_2);
		panelGroups.add(lblNewLabel_13);
		panelGroups.add(comboBox_3);
		panelGroups.add(lblNewLabel_14);
		panelGroups.add(comboBox_4);
		panelGroups.add(lblNewLabel_15);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("TP");
		rdbtnNewRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rdbtnNewRadioButton.setBounds(540, 201, 47, 25);
		panelGroups.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("TN");
		rdbtnNewRadioButton_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rdbtnNewRadioButton_1.setBounds(591, 201, 47, 25);
		panelGroups.add(rdbtnNewRadioButton_1);
		
		JRadioButton rdbtnOl = new JRadioButton("OL");
		rdbtnOl.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rdbtnOl.setBounds(642, 201, 47, 25);
		panelGroups.add(rdbtnOl);
		
		JLabel lblNewLabel_16 = new JLabel("Ilo\u015B\u0107 miejsc:");
		lblNewLabel_16.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel_16.setBounds(382, 239, 97, 16);
		panelGroups.add(lblNewLabel_16);
		
		textField_4 = new JTextField();
		textField_4.setText("250");
		textField_4.setBounds(467, 237, 39, 22);
		panelGroups.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblNewLabel_17 = new JLabel("Sala zaj\u0119ciowa:");
		lblNewLabel_17.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel_17.setBounds(382, 276, 116, 16);
		panelGroups.add(lblNewLabel_17);
		
		textField_5 = new JTextField();
		textField_5.setBounds(494, 272, 116, 22);
		panelGroups.add(textField_5);
		textField_5.setColumns(10);
		
		JPanel panelCourses = new JPanel();
		tabbedPane_1.addTab("Zarz�dzaj kursami", null, panelCourses, null);
		panelCourses.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 32, 186, 321);
		panelCourses.add(scrollPane_1);
		
		JList list_1 = new JList();
		scrollPane_1.setViewportView(list_1);
		
		JLabel fixLabelCourses = new JLabel("Kursy:");
		fixLabelCourses.setFont(new Font("Arial", Font.BOLD, 16));
		fixLabelCourses.setBounds(12, 13, 56, 16);
		panelCourses.add(fixLabelCourses);
		
		JButton btnCoursesCreate = new JButton("Stw\u00F3rz nowy kurs");
		btnCoursesCreate.setBounds(395, 9, 168, 25);
		panelCourses.add(btnCoursesCreate);
		
		JLabel fixLabelCoursesId = new JLabel("Id Kursu:");
		fixLabelCoursesId.setFont(new Font("Arial", Font.PLAIN, 16));
		fixLabelCoursesId.setBounds(210, 70, 71, 16);
		panelCourses.add(fixLabelCoursesId);
		
		textFieldCoursesId = new JTextField();
		textFieldCoursesId.setBounds(277, 68, 116, 22);
		panelCourses.add(textFieldCoursesId);
		textFieldCoursesId.setColumns(10);
		
		JLabel fixLabelCoursesName = new JLabel("Nazwa Kursu:");
		fixLabelCoursesName.setFont(new Font("Arial", Font.PLAIN, 16));
		fixLabelCoursesName.setBounds(210, 117, 101, 16);
		panelCourses.add(fixLabelCoursesName);
		
		textFieldCoursesName = new JTextField();
		textFieldCoursesName.setBounds(312, 115, 270, 22);
		panelCourses.add(textFieldCoursesName);
		textFieldCoursesName.setColumns(10);
		
		JLabel fixLabelCoursesSemester = new JLabel("Semestr Kursu:");
		fixLabelCoursesSemester.setFont(new Font("Arial", Font.PLAIN, 16));
		fixLabelCoursesSemester.setBounds(210, 159, 116, 16);
		panelCourses.add(fixLabelCoursesSemester);
		
		JComboBox<String> comboBoxCoursesSemester = new JComboBox<>();
		comboBoxCoursesSemester.setBounds(322, 157, 49, 22);
		panelCourses.add(comboBoxCoursesSemester);
		for(int i=0; i<7; i++)
		{
			comboBoxCoursesSemester.addItem((i+1)+"");
		}
		
		
		JLabel fixLabelCoursesForm = new JLabel("Forma Kursu:");
		fixLabelCoursesForm.setFont(new Font("Arial", Font.PLAIN, 16));
		fixLabelCoursesForm.setBounds(210, 199, 101, 16);
		panelCourses.add(fixLabelCoursesForm);
		
		JComboBox<String> comboBoxCoursesForm = new JComboBox<>();
		comboBoxCoursesForm.setBounds(313, 197, 102, 22);
		panelCourses.add(comboBoxCoursesForm);
		comboBoxCoursesForm.addItem("Wyk�ad");
		comboBoxCoursesForm.addItem("�wiczenia");
		comboBoxCoursesForm.addItem("Laboratorium");
		comboBoxCoursesForm.addItem("Projekt");
		comboBoxCoursesForm.addItem("Seminarium");
		
		JLabel fixLabelCoursesECTS = new JLabel("ECTS:");
		fixLabelCoursesECTS.setFont(new Font("Arial", Font.PLAIN, 16));
		fixLabelCoursesECTS.setBounds(210, 239, 56, 16);
		panelCourses.add(fixLabelCoursesECTS);
		
		textFieldCoursesECTS = new JTextField();
		textFieldCoursesECTS.setBounds(264, 237, 116, 22);
		panelCourses.add(textFieldCoursesECTS);
		textFieldCoursesECTS.setColumns(10);
		
		JButton btnModify = new JButton("Modyfikuj");
		btnModify.setBounds(635, 328, 97, 25);
		panelCourses.add(btnModify);
		
		JButton btnRemove = new JButton("Usu\u0144 Kurs");
		btnRemove.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnRemove.setForeground(Color.RED);
		btnRemove.setBounds(224, 337, 99, 16);
		panelCourses.add(btnRemove);
		
		JPanel panelUnsub = new JPanel();
		tabbedPane_1.addTab("Wypisz studenta", null, panelUnsub, null);
		panelUnsub.setLayout(null);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(12, 34, 163, 319);
		panelUnsub.add(scrollPane_2);
		
		JList list_2 = new JList();
		scrollPane_2.setViewportView(list_2);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(187, 34, 177, 319);
		panelUnsub.add(scrollPane_3);
		
		JList list_3 = new JList();
		scrollPane_3.setViewportView(list_3);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(376, 34, 177, 319);
		panelUnsub.add(scrollPane_4);
		
		JList list_4 = new JList();
		scrollPane_4.setViewportView(list_4);
		
		JButton btnWypisz = new JButton("Wypisz");
		btnWypisz.setForeground(Color.RED);
		btnWypisz.setBounds(656, 328, 97, 25);
		panelUnsub.add(btnWypisz);
		
		JLabel lblNewLabel = new JLabel("Studenci:");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel.setBounds(12, 13, 84, 16);
		panelUnsub.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("Grupy:");
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_2.setBounds(380, 13, 56, 16);
		panelUnsub.add(lblNewLabel_2);
		
		JLabel lblKursy = new JLabel("Kursy:");
		lblKursy.setFont(new Font("Arial", Font.BOLD, 16));
		lblKursy.setBounds(187, 13, 56, 16);
		panelUnsub.add(lblKursy);
		
		JLabel lblNewLabel_3 = new JLabel("ECTS:");
		lblNewLabel_3.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(565, 36, 56, 16);
		panelUnsub.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("13");
		lblNewLabel_4.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel_4.setBounds(637, 36, 56, 16);
		panelUnsub.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Parzysto\u015B\u0107 tygodnia:");
		lblNewLabel_5.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel_5.setBounds(565, 80, 157, 16);
		panelUnsub.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("TP");
		lblNewLabel_6.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel_6.setBounds(709, 80, 56, 16);
		panelUnsub.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Dzie\u0144 zaj\u0119\u0107:");
		lblNewLabel_7.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel_7.setBounds(565, 120, 97, 16);
		panelUnsub.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("wt");
		lblNewLabel_8.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel_8.setBounds(656, 120, 56, 16);
		panelUnsub.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("Godziny zaj\u0119\u0107:");
		lblNewLabel_9.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel_9.setBounds(565, 149, 108, 16);
		panelUnsub.add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("7:30:00 - 9:30:00");
		lblNewLabel_10.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel_10.setBounds(575, 169, 147, 16);
		panelUnsub.add(lblNewLabel_10);
		
		JLabel lblProwadzcy = new JLabel("Prowadz\u0105cy:");
		lblProwadzcy.setFont(new Font("Arial", Font.PLAIN, 16));
		lblProwadzcy.setBounds(565, 198, 97, 16);
		panelUnsub.add(lblProwadzcy);
		
		JLabel lblProfDrIn = new JLabel("Prof. dr. inz Janusz biernat");
		lblProfDrIn.setFont(new Font("Arial", Font.PLAIN, 14));
		lblProfDrIn.setBounds(564, 217, 213, 16);
		panelUnsub.add(lblProfDrIn);
		
		JPanel panelSub = new JPanel();
		panelSub.setLayout(null);
		tabbedPane_1.addTab("Zapisz studenta", null, panelSub, null);
		
		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(12, 34, 163, 319);
		panelSub.add(scrollPane_5);
		
		JList list_5 = new JList();
		scrollPane_5.setViewportView(list_5);
		
		JScrollPane scrollPane_6 = new JScrollPane();
		scrollPane_6.setBounds(187, 34, 177, 319);
		panelSub.add(scrollPane_6);
		
		JList list_6 = new JList();
		scrollPane_6.setViewportView(list_6);
		
		JScrollPane scrollPane_7 = new JScrollPane();
		scrollPane_7.setBounds(376, 34, 177, 319);
		panelSub.add(scrollPane_7);
		
		JList list_7 = new JList();
		scrollPane_7.setViewportView(list_7);
		
		JButton btnZapisz = new JButton("Zapisz");
		btnZapisz.setForeground(new Color(0, 153, 51));
		btnZapisz.setBounds(656, 328, 97, 25);
		panelSub.add(btnZapisz);
		
		JLabel label = new JLabel("Studenci:");
		label.setFont(new Font("Arial", Font.BOLD, 16));
		label.setBounds(12, 13, 84, 16);
		panelSub.add(label);
		
		JLabel label_1 = new JLabel("Grupy:");
		label_1.setFont(new Font("Arial", Font.BOLD, 16));
		label_1.setBounds(380, 13, 56, 16);
		panelSub.add(label_1);
		
		JLabel label_2 = new JLabel("Kursy:");
		label_2.setFont(new Font("Arial", Font.BOLD, 16));
		label_2.setBounds(187, 13, 56, 16);
		panelSub.add(label_2);
		
		JLabel label_3 = new JLabel("ECTS:");
		label_3.setFont(new Font("Arial", Font.PLAIN, 16));
		label_3.setBounds(565, 36, 56, 16);
		panelSub.add(label_3);
		
		JLabel label_4 = new JLabel("13");
		label_4.setFont(new Font("Arial", Font.PLAIN, 16));
		label_4.setBounds(637, 36, 56, 16);
		panelSub.add(label_4);
		
		JLabel label_5 = new JLabel("Parzysto\u015B\u0107 tygodnia:");
		label_5.setFont(new Font("Arial", Font.PLAIN, 16));
		label_5.setBounds(565, 80, 157, 16);
		panelSub.add(label_5);
		
		JLabel label_6 = new JLabel("TP");
		label_6.setFont(new Font("Arial", Font.PLAIN, 16));
		label_6.setBounds(709, 80, 56, 16);
		panelSub.add(label_6);
		
		JLabel label_7 = new JLabel("Dzie\u0144 zaj\u0119\u0107:");
		label_7.setFont(new Font("Arial", Font.PLAIN, 16));
		label_7.setBounds(565, 120, 97, 16);
		panelSub.add(label_7);
		
		JLabel label_8 = new JLabel("wt");
		label_8.setFont(new Font("Arial", Font.PLAIN, 16));
		label_8.setBounds(656, 120, 56, 16);
		panelSub.add(label_8);
		
		JLabel label_9 = new JLabel("Godziny zaj\u0119\u0107:");
		label_9.setFont(new Font("Arial", Font.PLAIN, 16));
		label_9.setBounds(565, 149, 108, 16);
		panelSub.add(label_9);
		
		JLabel label_10 = new JLabel("7:30:00 - 9:30:00");
		label_10.setFont(new Font("Arial", Font.PLAIN, 16));
		label_10.setBounds(575, 169, 147, 16);
		panelSub.add(label_10);
		
		JLabel label_11 = new JLabel("Prowadz\u0105cy:");
		label_11.setFont(new Font("Arial", Font.PLAIN, 16));
		label_11.setBounds(565, 198, 97, 16);
		panelSub.add(label_11);
		
		JLabel label_12 = new JLabel("Prof. dr. inz Janusz biernat");
		label_12.setFont(new Font("Arial", Font.PLAIN, 14));
		label_12.setBounds(564, 217, 213, 16);
		panelSub.add(label_12);
		
		JPanel panel = new JPanel();
		tabbedPane_1.addTab("Grupy", null, panel, null);
		
	}
}
