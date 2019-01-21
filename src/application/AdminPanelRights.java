package application;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Controllers.AdminPanelRightsController;
import application.models.StudentModel;

public class AdminPanelRights extends JPanel
{
	
	private JScrollPane scrollPane;
	private JList<StudentModel> list;
	private DefaultListModel<StudentModel> listModel;
// Labels
	private JLabel fixLabelRigthsStudents;
	private JLabel fixLabelRigthsName;
	private JLabel labelRightsName;
	private JLabel fixLabelRightsSurname;
	private JLabel labelRightsSurname;
	private JLabel fixLabelRightsIndex;
	private JLabel labelRightsIndex;
	private JLabel fixLabelRightsPesel;
	private JLabel labelRightsPesel;
	private JLabel fixLabelRightsFieldStudy;
	private JLabel labelRightsFieldStudy;
	private JLabel fixLabelRightsSemester;
	private JLabel labelRightsSemester;
	private JLabel fixLabelRights;
	private JLabel labelRights;
	private JLabel fixLabelRightsTime;
	private JLabel labelRightsTime;
// Buttons
	private JButton btnGiveRight;
	private JButton btnGiveTime;
	private JButton btnTakeRight;
// textFields
	private JTextField tfTime;
	
	private AdminPanelRightsController controller;
	private JFrame mainFrame;
	
	
	public AdminPanelRights(JFrame frame)
	{
		super.setLayout(null);
		
		this.mainFrame = frame;
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 37, 173, 316);
		this.add(scrollPane);
		
		listModel = new DefaultListModel<>();

		list = new JList<>(listModel);
		scrollPane.setViewportView(list);

		
		initLabels();
		initButtons();
		initTextFields();
		
		
		list.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent arg0)
			{
				btnGiveRight.setEnabled(true);
				btnGiveTime.setEnabled(true);
				btnTakeRight.setEnabled(true);
				
				StudentModel student = list.getSelectedValue();
				update(student);
				
			}
		});
		
		
	}
	
	public void loadList(List<StudentModel> studentsList)
	{
		listModel.removeAllElements();
		for(StudentModel student : studentsList)
		{
			listModel.addElement(student);
		}
	}
	
	public void update(StudentModel student)
	{
		labelRightsName.setText(student.getName()); 
		labelRightsSurname.setText(student.getSurname());
		labelRightsIndex.setText(student.getIndexId());
		labelRightsFieldStudy.setText(student.getFieldOfStudy());
		labelRightsPesel.setText(student.getPesel());
		labelRightsTime.setText(student.getAdmissionTime());
		labelRights.setText(student.getAdmissionRight());
		labelRightsSemester.setText(student.getSemester());
	}
	
	public void setController(AdminPanelRightsController controller)
	{
		this.controller = controller;
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
		
		labelRightsName = new JLabel("_____---_____");
		labelRightsName.setFont(new Font("Arial", Font.PLAIN, 16));
		labelRightsName.setBounds(286, 38, 131, 18);
		this.add(labelRightsName);
		
		fixLabelRightsSurname = new JLabel("Nazwisko:");
		fixLabelRightsSurname.setFont(new Font("Arial", Font.PLAIN, 16));
		fixLabelRightsSurname.setBounds(429, 39, 76, 16);
		this.add(fixLabelRightsSurname);
		
		labelRightsSurname = new JLabel("_____---_____");
		labelRightsSurname.setFont(new Font("Arial", Font.PLAIN, 16));
		labelRightsSurname.setBounds(510, 38, 229, 18);
		this.add(labelRightsSurname);
		
		fixLabelRightsIndex = new JLabel("Indeks:");
		fixLabelRightsIndex.setFont(new Font("Arial", Font.PLAIN, 16));
		fixLabelRightsIndex.setBounds(239, 78, 56, 16);
		this.add(fixLabelRightsIndex);
		
		labelRightsIndex = new JLabel("__---__");
		labelRightsIndex.setFont(new Font("Arial", Font.PLAIN, 16));
		labelRightsIndex.setBounds(296, 78, 56, 18);
		this.add(labelRightsIndex);
		
		fixLabelRightsPesel = new JLabel("Pesel:");
		fixLabelRightsPesel.setFont(new Font("Arial", Font.PLAIN, 16));
		fixLabelRightsPesel.setBounds(429, 78, 56, 18);
		this.add(fixLabelRightsPesel);
		
		labelRightsPesel = new JLabel("_____---_____");
		labelRightsPesel.setFont(new Font("Arial", Font.PLAIN, 16));
		labelRightsPesel.setBounds(482, 78, 105, 18);
		this.add(labelRightsPesel);
		
		fixLabelRightsFieldStudy = new JLabel("Kierunek:");
		fixLabelRightsFieldStudy.setFont(new Font("Arial", Font.PLAIN, 16));
		fixLabelRightsFieldStudy.setBounds(239, 122, 66, 16);
		this.add(fixLabelRightsFieldStudy);
		
		labelRightsFieldStudy = new JLabel("_-_");
		labelRightsFieldStudy.setFont(new Font("Arial", Font.PLAIN, 16));
		labelRightsFieldStudy.setBounds(311, 122, 41, 18);
		this.add(labelRightsFieldStudy);
		
		fixLabelRightsSemester = new JLabel("Semestr:");
		fixLabelRightsSemester.setFont(new Font("Arial", Font.PLAIN, 16));
		fixLabelRightsSemester.setBounds(429, 122, 76, 18);
		this.add(fixLabelRightsSemester);
		
		labelRightsSemester = new JLabel("_");
		labelRightsSemester.setFont(new Font("Arial", Font.PLAIN, 16));
		labelRightsSemester.setBounds(499, 122, 56, 16);
		this.add(labelRightsSemester);
		
		fixLabelRights = new JLabel("Prawo do zapis\u00F3w:");
		fixLabelRights.setFont(new Font("Arial", Font.PLAIN, 16));
		fixLabelRights.setBounds(239, 165, 145, 16);
		this.add(fixLabelRights);
		
		labelRights = new JLabel("_____---_____");
		labelRights.setFont(new Font("Arial", Font.PLAIN, 16));
		labelRights.setBounds(381, 165, 156, 18);
		this.add(labelRights);
		
		fixLabelRightsTime = new JLabel("Termin zapis\u00F3w:");
		fixLabelRightsTime.setFont(new Font("Arial", Font.PLAIN, 16));
		fixLabelRightsTime.setBounds(239, 247, 131, 16);
		this.add(fixLabelRightsTime);
		
		labelRightsTime = new JLabel("_____---_____");
		labelRightsTime.setFont(new Font("Arial", Font.PLAIN, 16));
		labelRightsTime.setBounds(361, 247, 400, 18);
		this.add(labelRightsTime);
	}

	private void initButtons()
	{
		btnGiveRight = new JButton("Nadaj");
		btnGiveRight.setBounds(239, 198, 97, 25);
		this.add(btnGiveRight);
		btnGiveRight.setEnabled(false);
		btnGiveRight.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				StudentModel student =  list.getSelectedValue();
				if(student.getAdmissionRight().equals("posiada"))
				{
					JOptionPane.showMessageDialog(mainFrame, "Student juz posiada prawo do zapis�w",
							"Uwaga!", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					student.setAdmissionRight("posiada");
					controller.updateStudentRight(student, "posiada");
					JOptionPane.showMessageDialog(mainFrame, "Poprawnie zmieniono prawo do zapisu",
							"Uwaga!", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		
		btnGiveTime = new JButton("Nadaj nowy czas zapis\u00F3w");
		btnGiveTime.setBounds(430, 284, 205, 25);
		this.add(btnGiveTime);
		btnGiveTime.setEnabled(false);
		
		btnGiveTime.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				StudentModel student =  list.getSelectedValue();
				String time = tfTime.getText();
				if(time.matches("^\\d{4}-\\d\\d-\\d\\d \\d\\d:\\d\\d:\\d\\d$"))
				{
					student.setAdmissionTime(time);
					controller.updateStudentTime(student, student.getAdmissionTime());
					JOptionPane.showMessageDialog(mainFrame, "Poprawnie zmienieniono czas zapis�w",
							"Uwaga!", JOptionPane.INFORMATION_MESSAGE);
				}
				else
				{
					JOptionPane.showMessageDialog(mainFrame, "Z�y czas",
							"Uwaga!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		
		btnTakeRight = new JButton("Odbierz");
		btnTakeRight.setBounds(372, 198, 97, 25);
		this.add(btnTakeRight);
		btnTakeRight.setEnabled(false);
		
		btnTakeRight.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				StudentModel student =  list.getSelectedValue();
				if(student.getAdmissionRight().equals("brak"))
				{
					JOptionPane.showMessageDialog(mainFrame, "Student nie posiada ju� prawa do zapis�w",
							"Uwaga!", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					student.setAdmissionRight("brak");
					controller.updateStudentRight(student, "brak");
					JOptionPane.showMessageDialog(mainFrame, "Poprawnie zmieniono prawo do zapisu",
							"Uwaga!", JOptionPane.INFORMATION_MESSAGE);
				}

				
			}
		});
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
