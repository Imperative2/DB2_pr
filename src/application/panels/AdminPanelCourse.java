package application.panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Controllers.AdminPanelCoursesController;
import Controllers.AdminPanelRightsController;
import application.models.CourseModel;
import application.models.StudentModel;

public class AdminPanelCourse extends JPanel
{
	private JScrollPane scrollPane;
	private JList<CourseModel> list;
	private DefaultListModel<CourseModel> listModel;
	private JFrame mainFrame;
	private AdminPanelCoursesController controller;
	
	//Labels
	JLabel fixLabelCourses;
	JLabel fixLabelCoursesId;
	JLabel fixLabelCoursesName;
	JLabel fixLabelCoursesSemester;
	JLabel fixLabelCoursesForm;
	JLabel fixLabelCoursesECTS;
	
	
	
	//TextFields
	JTextField tfCoursesId;
	JTextField tfCoursesName;
	JTextField tfCoursesECTS;
	
	//Buttons
	JButton btnCoursesCreate;
	JButton btnModify;
	JButton btnRemove;
	
	//comboBoxes
	JComboBox<String> comboBoxCoursesSemester;
	JComboBox<String> comboBoxCoursesForm;
	
	
	
	
	public AdminPanelCourse(JFrame mainFrame)
	{
		super.setLayout(null);
		this.mainFrame = mainFrame;
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 32, 186, 321);
		this.add(scrollPane);
		
		listModel = new DefaultListModel<>();
		
		list = new JList<>(listModel);
		scrollPane.setViewportView(list);
		
		initLabels();
		initTextFields();
		initButtons();
		initComboBoxes();
		
		list.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e)
			{
				if(list.isSelectionEmpty() != true)
				{
					btnModify.setEnabled(true);
					btnRemove.setEnabled(true);
					
					CourseModel course = list.getSelectedValue();
					update(course);
				}
			}
		});
	}
	
	public void loadList(List<CourseModel> coursesList)
	{
		listModel.removeAllElements();
		for(CourseModel student : coursesList)
		{
			listModel.addElement(student);
		}
	}
	
	public void update(CourseModel course)
	{
		tfCoursesId.setText(course.getCourseId());
		tfCoursesName.setText(course.getCourseName());
		comboBoxCoursesSemester.setSelectedItem(course.getSemester());
		comboBoxCoursesForm.setSelectedItem(course.getForm());
		tfCoursesECTS.setText(course.getECTS());
	}
	
	public void setController(AdminPanelCoursesController controller)
	{
		this.controller = controller;
	}
	
	private void initLabels()
	{
		fixLabelCourses = new JLabel("Kursy:");
		fixLabelCourses.setFont(new Font("Arial", Font.BOLD, 16));
		fixLabelCourses.setBounds(12, 13, 56, 16);
		this.add(fixLabelCourses);
		
		fixLabelCoursesId = new JLabel("Id Kursu:");
		fixLabelCoursesId.setFont(new Font("Arial", Font.PLAIN, 16));
		fixLabelCoursesId.setBounds(210, 70, 71, 16);
		this.add(fixLabelCoursesId);
		
		fixLabelCoursesName = new JLabel("Nazwa Kursu:");
		fixLabelCoursesName.setFont(new Font("Arial", Font.PLAIN, 16));
		fixLabelCoursesName.setBounds(210, 117, 101, 16);
		this.add(fixLabelCoursesName);
		
		fixLabelCoursesSemester = new JLabel("Semestr Kursu:");
		fixLabelCoursesSemester.setFont(new Font("Arial", Font.PLAIN, 16));
		fixLabelCoursesSemester.setBounds(210, 159, 116, 16);
		this.add(fixLabelCoursesSemester);
		
		fixLabelCoursesForm = new JLabel("Forma Kursu:");
		fixLabelCoursesForm.setFont(new Font("Arial", Font.PLAIN, 16));
		fixLabelCoursesForm.setBounds(210, 199, 101, 16);
		this.add(fixLabelCoursesForm);
		
		fixLabelCoursesECTS = new JLabel("ECTS:");
		fixLabelCoursesECTS.setFont(new Font("Arial", Font.PLAIN, 16));
		fixLabelCoursesECTS.setBounds(210, 239, 56, 16);
		this.add(fixLabelCoursesECTS);
	}
	
	private void initTextFields()
	{
		tfCoursesId = new JTextField();
		tfCoursesId.setBounds(277, 68, 116, 22);
		this.add(tfCoursesId);
		tfCoursesId.setColumns(10);
		
		tfCoursesName = new JTextField();
		tfCoursesName.setBounds(312, 115, 270, 22);
		this.add(tfCoursesName);
		tfCoursesName.setColumns(10);
		
		tfCoursesECTS = new JTextField();
		tfCoursesECTS.setBounds(264, 237, 116, 22);
		this.add(tfCoursesECTS);
		tfCoursesECTS.setColumns(10);
	}
	
	private void initButtons()
	{
		btnCoursesCreate = new JButton("Stw\u00F3rz nowy kurs");
		btnCoursesCreate.setBounds(395, 9, 168, 25);
		this.add(btnCoursesCreate);
		
		btnCoursesCreate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				CourseModel course = new CourseModel();
				course.setCourseId(tfCoursesId.getText());
				course.setCourseName(tfCoursesName.getText());
				course.setSemester((String)comboBoxCoursesSemester.getSelectedItem());
				course.setForm((String)comboBoxCoursesForm.getSelectedItem());
				course.setECTS(tfCoursesECTS.getText());
				
				controller.createNewCourse(course);
			}
		});
		
		btnModify = new JButton("Modyfikuj");
		btnModify.setBounds(635, 328, 97, 25);
		this.add(btnModify);
		btnModify.setEnabled(false);
		
		btnModify.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				btnRemove.setEnabled(false);
				btnModify.setEnabled(false);
				
				CourseModel course = new CourseModel();
				course.setCourseId(tfCoursesId.getText());
				course.setCourseName(tfCoursesName.getText());
				course.setSemester((String)comboBoxCoursesSemester.getSelectedItem());
				course.setForm((String)comboBoxCoursesForm.getSelectedItem());
				course.setECTS(tfCoursesECTS.getText());

				controller.modifyCourse(course);
				
			}
		});
		
		btnRemove = new JButton("Usu\u0144 Kurs");
		btnRemove.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnRemove.setForeground(Color.RED);
		btnRemove.setBounds(224, 337, 99, 16);
		this.add(btnRemove);
		btnRemove.setEnabled(false);
		
		btnRemove.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				btnRemove.setEnabled(false);
				btnModify.setEnabled(false);
				
				CourseModel course = new CourseModel();
				course.setCourseId(tfCoursesId.getText());

				controller.deleteCourse(course);
				
			}
		});
	}
	
	private void initComboBoxes()
	{
		comboBoxCoursesSemester = new JComboBox<>();
		comboBoxCoursesSemester.setBounds(322, 157, 49, 22);
		this.add(comboBoxCoursesSemester);
		for(int i=0; i<7; i++)
		{
			comboBoxCoursesSemester.addItem((i+1)+"");
		}
		
		comboBoxCoursesForm = new JComboBox<>();
		comboBoxCoursesForm.setBounds(313, 197, 102, 22);
		this.add(comboBoxCoursesForm);
		comboBoxCoursesForm.addItem("Wyk�ad");
		comboBoxCoursesForm.addItem("�wiczenia");
		comboBoxCoursesForm.addItem("Laboratorium");
		comboBoxCoursesForm.addItem("Projekt");
		comboBoxCoursesForm.addItem("Seminarium");
	}
}
