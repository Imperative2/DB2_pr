package application;

import java.awt.Font;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import application.models.StudentModel;

public class AdminPanelRights extends JPanel
{
	
	JScrollPane scrollPane;
	JList<StudentModel> list;
	DefaultListModel<StudentModel> listModel;
// Labels
	JLabel fixLabelRigthsStudents;
	JLabel fixLabelRigthsName;
	JLabel labelRightsName;
	JLabel fixLabelRightsSurname;
	JLabel labelRightsSurname;
	JLabel fixLabelRightsIndex;
	JLabel labelRightsIndex;
	JLabel fixLabelRightsPesel;
	JLabel labelRightsPesel;
	JLabel fixLabelRightsFieldStudy;
	JLabel labelRightsFieldStudy;
	JLabel fixLabelRightsSemester;
	JLabel labelRightsSemester;
	JLabel fixLabelRights;
	JLabel labelRights;
	JLabel fixLabelRightsTime;
	JLabel labelRightsTime;
// Buttons
	JButton btnGiveRight;
	JButton btnGiveTime;
	JButton btnTakeRight;
// textFields
	JTextField tfTime;
	
	
	public AdminPanelRights()
	{
		super.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 37, 173, 316);
		this.add(scrollPane);
		
		listModel = new DefaultListModel<>();

		list = new JList<>(listModel);
		scrollPane.setViewportView(list);

		
		initLabels();
		initButtons();
		initTextFields();
		
		
	}
	
	public void loadList(List<StudentModel> studentsList)
	{
		for(StudentModel student : studentsList)
		{
			listModel.addElement(student);
		}
	}
	
	private void initLabels()
	{
		fixLabelRigthsStudents = new JLabel("Studenci:");
		fixLabelRigthsStudents.setFont(new Font("Arial", Font.BOLD, 16));
		fixLabelRigthsStudents.setBounds(12, 13, 87, 16);
		this.add(fixLabelRigthsStudents);
		
		fixLabelRigthsName = new JLabel("Imi\u0119:");
		fixLabelRigthsName.setFont(new Font("Arial", Font.PLAIN, 16));
		fixLabelRigthsName.setBounds(239, 39, 35, 16);
		this.add(fixLabelRigthsName);
		
		labelRightsName = new JLabel("Karolina");
		labelRightsName.setFont(new Font("Arial", Font.PLAIN, 16));
		labelRightsName.setBounds(286, 38, 131, 16);
		this.add(labelRightsName);
		
		fixLabelRightsSurname = new JLabel("Nazwisko:");
		fixLabelRightsSurname.setFont(new Font("Arial", Font.PLAIN, 16));
		fixLabelRightsSurname.setBounds(429, 39, 76, 16);
		this.add(fixLabelRightsSurname);
		
		labelRightsSurname = new JLabel("Bo\u017Conarodzeniowa");
		labelRightsSurname.setFont(new Font("Arial", Font.PLAIN, 16));
		labelRightsSurname.setBounds(510, 38, 229, 16);
		this.add(labelRightsSurname);
		
		fixLabelRightsIndex = new JLabel("Indeks:");
		fixLabelRightsIndex.setFont(new Font("Arial", Font.PLAIN, 16));
		fixLabelRightsIndex.setBounds(239, 78, 56, 16);
		this.add(fixLabelRightsIndex);
		
		labelRightsIndex = new JLabel("235044");
		labelRightsIndex.setFont(new Font("Arial", Font.PLAIN, 16));
		labelRightsIndex.setBounds(296, 78, 56, 16);
		this.add(labelRightsIndex);
		
		fixLabelRightsPesel = new JLabel("Pesel:");
		fixLabelRightsPesel.setFont(new Font("Arial", Font.PLAIN, 16));
		fixLabelRightsPesel.setBounds(429, 78, 56, 16);
		this.add(fixLabelRightsPesel);
		
		labelRightsPesel = new JLabel("97070312345");
		labelRightsPesel.setFont(new Font("Arial", Font.PLAIN, 16));
		labelRightsPesel.setBounds(482, 78, 105, 16);
		this.add(labelRightsPesel);
		
		fixLabelRightsFieldStudy = new JLabel("Kierunek:");
		fixLabelRightsFieldStudy.setFont(new Font("Arial", Font.PLAIN, 16));
		fixLabelRightsFieldStudy.setBounds(239, 122, 66, 16);
		this.add(fixLabelRightsFieldStudy);
		
		labelRightsFieldStudy = new JLabel("INF");
		labelRightsFieldStudy.setFont(new Font("Arial", Font.PLAIN, 16));
		labelRightsFieldStudy.setBounds(311, 122, 41, 16);
		this.add(labelRightsFieldStudy);
		
		fixLabelRightsSemester = new JLabel("Semestr:");
		fixLabelRightsSemester.setFont(new Font("Arial", Font.PLAIN, 16));
		fixLabelRightsSemester.setBounds(429, 122, 76, 16);
		this.add(fixLabelRightsSemester);
		
		labelRightsSemester = new JLabel("5");
		labelRightsSemester.setFont(new Font("Arial", Font.PLAIN, 16));
		labelRightsSemester.setBounds(499, 122, 56, 16);
		this.add(labelRightsSemester);
		
		fixLabelRights = new JLabel("Prawo do zapis\u00F3w:");
		fixLabelRights.setFont(new Font("Arial", Font.PLAIN, 16));
		fixLabelRights.setBounds(239, 165, 145, 16);
		this.add(fixLabelRights);
		
		labelRights = new JLabel("nie posiada");
		labelRights.setFont(new Font("Arial", Font.PLAIN, 16));
		labelRights.setBounds(381, 165, 156, 16);
		this.add(labelRights);
		
		fixLabelRightsTime = new JLabel("Termin zapis\u00F3w:");
		fixLabelRightsTime.setFont(new Font("Arial", Font.PLAIN, 16));
		fixLabelRightsTime.setBounds(239, 247, 131, 16);
		this.add(fixLabelRightsTime);
		
		labelRightsTime = new JLabel("2019-02-19 19:58:39");
		labelRightsTime.setFont(new Font("Arial", Font.PLAIN, 16));
		labelRightsTime.setBounds(361, 247, 156, 16);
		this.add(labelRightsTime);
	}

	private void initButtons()
	{
		btnGiveRight = new JButton("Nadaj");
		btnGiveRight.setBounds(239, 198, 97, 25);
		this.add(btnGiveRight);
		
		btnGiveTime = new JButton("Nadaj nowy czas zapis\u00F3w");
		btnGiveTime.setBounds(430, 284, 205, 25);
		this.add(btnGiveTime);
		
		btnTakeRight = new JButton("Odbierz");
		btnTakeRight.setBounds(372, 198, 97, 25);
		this.add(btnTakeRight);
	}
	
	private void initTextFields()
	{
		tfTime = new JTextField();
		tfTime.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tfTime.setText("yyyy-MM-dd hh:mm:ss");
		tfTime.setBounds(239, 276, 178, 38);
		this.add(tfTime);
		tfTime.setColumns(10);
	}
}
