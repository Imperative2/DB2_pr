package application.panels;

import java.awt.Color;
import java.awt.Font;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Controllers.AdminPanelCoursesController;
import Controllers.AdminPanelGroupController;
import application.DatabaseConnection;
import application.models.CourseModel;
import application.models.Group;
import application.models.GroupModel;
import application.models.TeacherModel;
import application.models.TimeModel;

public class AdminPanelGroup extends JPanel
{

	private JScrollPane scrollPaneCourse;
	private JList<CourseModel> listCourse;
	private DefaultListModel<CourseModel> listModelCourse;
	private JFrame mainFrame;
	private AdminPanelGroupController controller;
	
	private DatabaseConnection dbConn;
	
	private JScrollPane scrollPaneGroup;	
	private JList<GroupModel> listGroup;
	private DefaultListModel<GroupModel> listModelGroup;


	
//Buttons
	ButtonGroup btnGr = new ButtonGroup();
	private JButton btnCreateGroup;
	private JButton btnModifyGroup;
	private JButton btnDeleteGroup;
	private JRadioButton btnTP;
	private JRadioButton btTN;
	private JRadioButton btnOL;
	
//ComboBoxes
	private JComboBox<String> cbDayOfWeek;
	private JComboBox<TeacherModel> cbTeacher;
	private JComboBox<TimeModel> cbTime;
	
//TextFields
	private JTextField tfGroupId;
	private JTextField tfGroupSize;
	private JTextField tfRoom;	
	
	
	
	
	public AdminPanelGroup(JFrame mainFrame,DatabaseConnection dbConn)
	{
		super.setLayout(null);
		this.mainFrame = mainFrame;
		
		this.dbConn = dbConn;
		
		scrollPaneCourse = new JScrollPane();
		scrollPaneCourse.setBounds(12, 28, 173, 325);
		this.add(scrollPaneCourse);
		
		listModelCourse = new DefaultListModel<>();
		
		listCourse = new JList<>(listModelCourse);
		scrollPaneCourse.setViewportView(listCourse);
		
		
		scrollPaneGroup = new JScrollPane();
		scrollPaneGroup.setBounds(197, 28, 173, 325);
		this.add(scrollPaneGroup);
		
		listModelGroup = new DefaultListModel<>();
		
		listGroup = new JList<>(listModelGroup);
		scrollPaneGroup.setViewportView(listGroup);
		
		
		initLabels();
		initButtons();
		initComboBoxes();
		initTextFields();
		
		
		
		listCourse.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e)
			{
				if(listCourse.isSelectionEmpty() == false)
				{
					controller.loadGroups(listCourse.getSelectedValue());
					btnCreateGroup.setEnabled(true);
					
				}

			}
		});
	}
	
	public void loadListCourses(List<CourseModel> coursesList)
	{
		listModelCourse.removeAllElements();
		for(CourseModel course : coursesList)
		{
			listModelCourse.addElement(course);
		}
		listCourse.setVisible(true);
	}
	
	public void loadListGroups(List<GroupModel> groupList)
	{
		listModelGroup.removeAllElements();
		for(GroupModel group : groupList)
		{
			listModelGroup.addElement(group);
		}

	}
	

	
	
	public void setController(AdminPanelGroupController controller)
	{
		this.controller = controller;
	}
	
	private void initLabels()
	{
		JLabel fixLabelCourses = new JLabel("Kursy:");
		fixLabelCourses.setFont(new Font("Arial", Font.BOLD, 16));
		fixLabelCourses.setBounds(12, 13, 56, 16);
		this.add(fixLabelCourses);
		
		JLabel fixLabelGroups = new JLabel("Grupy:");
		fixLabelGroups.setFont(new Font("Arial", Font.BOLD, 16));
		fixLabelGroups.setBounds(197, 13, 56, 16);
		this.add(fixLabelGroups);
		
		JLabel fixLabelGroupId = new JLabel("Kod Grupy:");
		fixLabelGroupId.setFont(new Font("Arial", Font.PLAIN, 16));
		fixLabelGroupId.setBounds(382, 49, 78, 16);
		this.add(fixLabelGroupId);
		
		JLabel fixLabelTeacher = new JLabel("Prowadz\u0105cy:");
		fixLabelTeacher.setFont(new Font("Arial", Font.PLAIN, 16));
		fixLabelTeacher.setBounds(382, 92, 103, 16);
		this.add(fixLabelTeacher);
		
		JLabel fixLabelGroupTime = new JLabel("Godziny zaj\u0119\u0107:");
		fixLabelGroupTime.setFont(new Font("Arial", Font.PLAIN, 16));
		fixLabelGroupTime.setBounds(382, 137, 103, 16);
		this.add(fixLabelGroupTime);
		
		JLabel fixLabelDayOfWeek = new JLabel("Dzie\u0144 tygodnia:");
		fixLabelDayOfWeek.setFont(new Font("Arial", Font.PLAIN, 16));
		fixLabelDayOfWeek.setBounds(384, 173, 122, 18);
		this.add(fixLabelDayOfWeek);
		
		JLabel fixLabelParity = new JLabel("Parzysto\u015B\u0107 tygodznia:");
		fixLabelParity.setFont(new Font("Arial", Font.PLAIN, 16));
		fixLabelParity.setBounds(382, 204, 150, 18);
		this.add(fixLabelParity);
		
		JLabel fixLabelGroupSize = new JLabel("Ilo\u015B\u0107 miejsc:");
		fixLabelGroupSize.setFont(new Font("Arial", Font.PLAIN, 16));
		fixLabelGroupSize.setBounds(382, 239, 97, 16);
		this.add(fixLabelGroupSize);
		
		JLabel fixLabelRoom = new JLabel("Sala zaj\u0119ciowa:");
		fixLabelRoom.setFont(new Font("Arial", Font.PLAIN, 16));
		fixLabelRoom.setBounds(382, 276, 116, 16);
		this.add(fixLabelRoom);
		
	}
	
	private void initButtons()
	{
		btnCreateGroup = new JButton("Stw\u00F3rz now\u0105 grup\u0119");
		btnCreateGroup.setBounds(526, 9, 145, 25);
		this.add(btnCreateGroup);
		btnCreateGroup.setEnabled(false);
		
		btnModifyGroup = new JButton("Modyfikuj ");
		btnModifyGroup.setBounds(656, 328, 97, 25);
		this.add(btnModifyGroup);
		btnModifyGroup.setEnabled(false);
		
		btnDeleteGroup = new JButton("Usu\u0144 grup\u0119");
		btnDeleteGroup.setBounds(382, 332, 97, 16);
		btnDeleteGroup.setForeground(Color.RED);
		this.add(btnDeleteGroup);
		btnDeleteGroup.setEnabled(false);
		
	
		
		btnTP = new JRadioButton("TP");
		btnTP.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnTP.setBounds(540, 201, 47, 25);
		this.add(btnTP);
		btnGr.add(btnTP);
		
		btTN = new JRadioButton("TN");
		btTN.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btTN.setBounds(591, 201, 47, 25);
		this.add(btTN);
		btnGr.add(btTN);
		
		btnOL = new JRadioButton("OL");
		btnOL.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnOL.setBounds(642, 201, 47, 25);
		this.add(btnOL);
		btnGr.add(btnOL);
	}

	private void initComboBoxes()
	{
		cbTeacher = new JComboBox<>();
		cbTeacher.setBounds(474, 90, 123, 22);
		this.add(cbTeacher);
		
		cbTime = new JComboBox<>();
		cbTime.setBounds(484, 135, 133, 22);
		this.add(cbTime);
		
		cbDayOfWeek = new JComboBox<>();
		cbDayOfWeek.setBounds(494, 170, 123, 22);
		this.add(cbDayOfWeek);
		
		loadComboBoxes();
		

	}
	
	private void initTextFields()
	{
		tfGroupId = new JTextField();
		tfGroupId.setBounds(464, 47, 116, 22);
		tfGroupId.setColumns(10);
		tfGroupId.setVisible(true);
		this.add(tfGroupId);
		
		tfGroupSize = new JTextField();
		tfGroupSize.setText("250");
		tfGroupSize.setBounds(467, 237, 39, 22);
		this.add(tfGroupSize);
		tfGroupSize.setVisible(true);
		tfGroupSize.setColumns(10);
		
		tfRoom = new JTextField();
		tfRoom.setBounds(494, 272, 116, 22);
		this.add(tfRoom);
		tfRoom.setVisible(true);
		tfRoom.setColumns(10);
	}

	public void loadComboBoxes()
	{
		cbDayOfWeek.addItem("pn");
		cbDayOfWeek.addItem("wt");
		cbDayOfWeek.addItem("œr");
		cbDayOfWeek.addItem("czw");
		cbDayOfWeek.addItem("pt");
		cbDayOfWeek.addItem("sob");
		cbDayOfWeek.addItem("niedz");
		
		dbConn.connect();
		String querry = "SELECT * FROM `prowadzacy`;";
		List<String[]> teacherResult = dbConn.querryDatabase(querry, 5);
		for(String[] row : teacherResult)
		{
			TeacherModel teacherModel = new TeacherModel();
			teacherModel.setId(row[0]);
			teacherModel.setName(row[1]);
			teacherModel.setSurname(row[2]);
			teacherModel.setTitle(row[3]);
			teacherModel.setEmail(row[4]);
			cbTeacher.addItem(teacherModel);
		}
		
		querry = "SELECT * FROM `godziny_zajec`;";
		List<String[]> timeResult = dbConn.querryDatabase(querry, 2);
		for(String[] row : timeResult)
		{
			TimeModel timeModel = new TimeModel();
			timeModel.setId(row[0]);
			timeModel.setTime(row[1]);
			cbTime.addItem(timeModel);
		}
		
				
	}
	
	
}
