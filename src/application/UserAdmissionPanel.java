package application;

import application.models.Course;
import application.models.Group;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class UserAdmissionPanel extends JPanel {

    DefaultListModel<Course> coursesModel = new DefaultListModel<>();
    DefaultListModel<Group> groupsModel = new DefaultListModel<>();

    public UserAdmissionPanel(){
        createGroupsView();
    }

    private void createGroupsView() {

		/*
				Scroll Panes
		 */

        JScrollPane coursesScroll = new JScrollPane();
        coursesScroll.setBounds(12, 42, 180, 298);
        this.add(coursesScroll);

        JScrollPane groupsScroll = new JScrollPane();
        groupsScroll.setBounds(250, 42, 180, 298);
        this.add(groupsScroll);

		/*

				Lists
		 */

        JList<Group> listGroups = new JList<>(groupsModel);
        listGroups.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        groupsScroll.setViewportView(listGroups);

        JList<Course> listCourses = new JList<>(coursesModel);
        listCourses.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        coursesScroll.setViewportView(listCourses);
        listCourses.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                Course course = listCourses.getSelectedValue();
                if(course!=null){
                    groupsModel.removeAllElements();
                    for(Group group:course.getListGroups()){
                        groupsModel.addElement(group);
                    }
                }
            }
        });

        /*
                Labels
         */

        JLabel labelCourses = new JLabel("Kursy:");
        labelCourses.setBounds(12, 28, 50, 12);
        JLabel labelGroups = new JLabel("Grupy:");
        labelGroups.setBounds(250, 28, 50, 12);
        this.add(labelCourses);
        this.add(labelGroups);

    }

    public void addCourseToModel(Course course) {
        coursesModel.addElement(course);
    }
}
