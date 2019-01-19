package application;

import application.models.Course;
import application.models.Group;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class UserAdmissionPanel extends JPanel
{

	private DefaultListModel<Course> coursesModel = new DefaultListModel<>();
	private DefaultListModel<Group> groupsModel = new DefaultListModel<>();
	private JLabel labelBrowserForm, labelBrowserTeacher, labelBrowserECTS, labelBrowserWeek, labelBrowserDay,
			labelBrowserTime, labelBrowserRoom, labelFreeSpace;
	private JList<Group> listGroups;
    private JList<Course> listCourses;
    private JButton btnAdmissionSignUp;

	public UserAdmissionPanel()
	{
		createGroupsView();
	}

	private void createGroupsView()
	{

		/*
		 * Scroll Panes
		 */

		JScrollPane coursesScroll = new JScrollPane();
		coursesScroll.setBounds(12, 42, 180, 298);
		this.add(coursesScroll);

		JScrollPane groupsScroll = new JScrollPane();
		groupsScroll.setBounds(250, 42, 180, 298);
		this.add(groupsScroll);

		/*
		 * 
		 * Lists
		 */
		listCourses = new JList<>(coursesModel);

		listGroups = new JList<>(groupsModel);
		listGroups.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		groupsScroll.setViewportView(listGroups);
		listGroups.addListSelectionListener(e -> {
			Group group = listGroups.getSelectedValue();
			if (group != null)
			{
				updateInfoAboutGroups(group, listCourses.getSelectedValue());
			}
		});

		listCourses.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		coursesScroll.setViewportView(listCourses);
		listCourses.addListSelectionListener(e -> {
			Course course = listCourses.getSelectedValue();
			if (course != null)
			{
				groupsModel.removeAllElements();
				for (Group group : course.getListGroups())
				{
					groupsModel.addElement(group);
				}
				if (groupsModel.size() > 0)
					listGroups.setSelectedIndex(0);
			}
		});

		/*
		 * Labels
		 */

		JLabel labelCourses = new JLabel("Kursy:");
		labelCourses.setBounds(12, 28, 50, 12);
		JLabel labelGroups = new JLabel("Grupy:");
		labelGroups.setBounds(250, 28, 50, 12);
		this.add(labelCourses);
		this.add(labelGroups);

		JLabel fixLabelBrowserForm = new JLabel("Forma zaj\u0119\u0107:");
		fixLabelBrowserForm.setFont(new Font("Arial", Font.PLAIN, 16));
		fixLabelBrowserForm.setBounds(437, 48, 95, 16);
		this.add(fixLabelBrowserForm);

		JLabel labelArrow = new JLabel("");
		labelArrow.setBounds(193, 153, 56, 40);
		try
		{
			Image img = ImageIO.read(getClass().getResource("/resources/arrow.png"));
			labelArrow.setIcon(scaleImageAndConvertToIcon(img, labelArrow.getWidth(), labelArrow.getHeight()));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		this.add(labelArrow);

		labelBrowserForm = new JLabel("-- . --");
		labelBrowserForm.setFont(new Font("Arial", Font.PLAIN, 16));
		labelBrowserForm.setBounds(533, 48, 105, 16);
		this.add(labelBrowserForm);

		JLabel fixLabelBrowserTeacher = new JLabel("Prowadz\u0105cy:");
		fixLabelBrowserTeacher.setFont(new Font("Arial", Font.PLAIN, 16));
		fixLabelBrowserTeacher.setBounds(437, 104, 95, 16);
		this.add(fixLabelBrowserTeacher);

		labelBrowserTeacher = new JLabel("-- . --");
		labelBrowserTeacher.setFont(new Font("Arial", Font.PLAIN, 16));
		labelBrowserTeacher.setBounds(533, 104, 209, 16);
		this.add(labelBrowserTeacher);

		JLabel fixLabelBrowserECTS = new JLabel("ECTS:");
		fixLabelBrowserECTS.setFont(new Font("Arial", Font.PLAIN, 16));
		fixLabelBrowserECTS.setBounds(638, 48, 56, 16);
		this.add(fixLabelBrowserECTS);

		labelBrowserECTS = new JLabel("-");
		labelBrowserECTS.setFont(new Font("Arial", Font.BOLD, 16));
		labelBrowserECTS.setBounds(688, 48, 56, 16);
		this.add(labelBrowserECTS);

		JLabel fixLabelBrowserWeek = new JLabel("Parzysto\u015B\u0107 tygodnia:");
		fixLabelBrowserWeek.setFont(new Font("Arial", Font.PLAIN, 16));
		fixLabelBrowserWeek.setBounds(437, 145, 152, 16);
		this.add(fixLabelBrowserWeek);

		labelBrowserWeek = new JLabel("-- . --");
		labelBrowserWeek.setFont(new Font("Arial", Font.PLAIN, 16));
		labelBrowserWeek.setBounds(589, 145, 56, 16);
		this.add(labelBrowserWeek);

		JLabel fixLabelBrowserDay = new JLabel("Dzie\u0144 zaj\u0119\u0107:");
		fixLabelBrowserDay.setFont(new Font("Arial", Font.PLAIN, 16));
		fixLabelBrowserDay.setBounds(437, 177, 95, 16);
		this.add(fixLabelBrowserDay);

		labelBrowserDay = new JLabel("-- . --");
		labelBrowserDay.setFont(new Font("Arial", Font.PLAIN, 16));
		labelBrowserDay.setBounds(533, 177, 112, 16);
		this.add(labelBrowserDay);

		JLabel fixLabelBrowserTime = new JLabel("Godziny zaj\u0119\u0107:");
		fixLabelBrowserTime.setFont(new Font("Arial", Font.PLAIN, 16));
		fixLabelBrowserTime.setBounds(437, 224, 112, 16);
		this.add(fixLabelBrowserTime);

		JLabel fixLabelBrowserRoom = new JLabel("Sala zaj\u0119\u0107:");
		fixLabelBrowserRoom.setFont(new Font("Arial", Font.PLAIN, 16));
		fixLabelBrowserRoom.setBounds(437, 269, 95, 16);
		this.add(fixLabelBrowserRoom);

		labelBrowserTime = new JLabel("-- . --");
		labelBrowserTime.setFont(new Font("Arial", Font.PLAIN, 16));
		labelBrowserTime.setBounds(545, 224, 141, 16);
		this.add(labelBrowserTime);

		labelBrowserRoom = new JLabel("-- . --");
		labelBrowserRoom.setFont(new Font("Arial", Font.PLAIN, 16));
		labelBrowserRoom.setBounds(519, 269, 149, 16);
		this.add(labelBrowserRoom);

		JLabel fixLabelFreeSpace = new JLabel("Wolne miejsca:");
		fixLabelFreeSpace.setFont(new Font("Arial", Font.PLAIN, 16));
		fixLabelFreeSpace.setBounds(450, 300, 120, 16);
		this.add(fixLabelFreeSpace);

		labelFreeSpace = new JLabel("-- . --");
		labelFreeSpace.setFont(new Font("Arial", Font.PLAIN, 16));
		labelFreeSpace.setBounds(560, 300, 95, 16);
		this.add(labelFreeSpace);

		btnAdmissionSignUp = new JButton("Zapisz");
        btnAdmissionSignUp.setBounds(645, 302, 97, 25);
        btnAdmissionSignUp.setVisible(false);
        btnAdmissionSignUp.addActionListener(e->{
//            signUpStudentToGroup(userAdmissionControllerSaves.getChosenGroup());
        });
        this.add(btnAdmissionSignUp);

	}

	public ImageIcon scaleImageAndConvertToIcon(Image sourceImage, int destinationWidth, int destinationHeight)
	{
		Image scaledImage = sourceImage.getScaledInstance(destinationWidth, destinationHeight, Image.SCALE_SMOOTH);
		ImageIcon resultImageIcon = new ImageIcon(scaledImage);
		return resultImageIcon;
	}

	private void updateInfoAboutGroups(Group group, Course course)
	{
		labelBrowserDay.setText(group.getDayOfWeek());
		labelBrowserECTS.setText(Integer.toString(course.getEcts()));
		labelBrowserForm.setText(course.getFormOfClasses());
		labelBrowserRoom.setText(group.getClassroom());
		labelBrowserTeacher.setText(course.getNameMaster());
		labelBrowserTime.setText(group.getLessonTime());
		labelBrowserWeek.setText(group.getParityOfWeek());
		labelFreeSpace.setText(Integer.toString(group.getAmountOfPlaces()));
	}

	public void addCourseToModel(Course course)
	{
		coursesModel.addElement(course);
	}

	public Group getChosenGroup(){
	     return listGroups.getSelectedValue();
    }

    public void setEnabledList(boolean enabled){
	    listGroups.setVisible(enabled);
	    listCourses.setVisible(enabled);
	    btnAdmissionSignUp.setVisible(enabled);
    }
}
