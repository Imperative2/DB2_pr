package application;

import application.models.Course;
import application.models.Group;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class StudentCoursesInfoPanel extends JPanel {

    private DefaultListModel<Student> modelListStudent = new DefaultListModel<>();
    private DefaultListModel<Course> modelListCourses = new DefaultListModel<>();
    private DefaultListModel<Group> modelListGroups = new DefaultListModel<Group>();
    private StudentCoursesInfoController studentCoursesInfoController;
    private JLabel lblECTS, lblparityOfWeek, lbldayOfWeek, lbllessonsHours,lblProfDrIn;
    private JList<Course> listStudentCourses;
    private JList<Group> listStudentsGroups;

    public StudentCoursesInfoPanel(){
        JScrollPane scrollPane_2 = new JScrollPane();
        scrollPane_2.setBounds(12, 34, 163, 319);
        this.add(scrollPane_2);

        JList<Student> listAllStudents = new JList<>(modelListStudent);
        scrollPane_2.setViewportView(listAllStudents);
        listAllStudents.addListSelectionListener(e -> {
            removeInfoGroup();
                studentCoursesInfoController.getCoursesAdnGroupsBy(listAllStudents.getSelectedValue());
            }
        );

        JScrollPane scrollPane_3 = new JScrollPane();
        scrollPane_3.setBounds(187, 34, 177, 319);
        this.add(scrollPane_3);

        listStudentCourses = new JList<>(modelListCourses);
        scrollPane_3.setViewportView(listStudentCourses);
        listStudentCourses.addListSelectionListener(e->{
            modelListGroups.clear();
            removeInfoGroup();
            if(listStudentCourses.getSelectedValue()!=null) {
                addGroupsToModel(listStudentCourses.getSelectedValue());
            }
        });

        JScrollPane scrollPane_4 = new JScrollPane();
        scrollPane_4.setBounds(376, 34, 177, 319);
        this.add(scrollPane_4);

        listStudentsGroups = new JList<>(modelListGroups);
        scrollPane_4.setViewportView(listStudentsGroups);
        listStudentsGroups.addListSelectionListener(e->{
            if(listStudentsGroups.getSelectedValue()!=null){
                updateInfoGroup(listStudentsGroups.getSelectedValue());
            }
        });

        JButton btnWypisz = new JButton("Wypisz");
        btnWypisz.setForeground(Color.RED);
        btnWypisz.setBounds(656, 328, 97, 25);
        btnWypisz.addActionListener(e -> {
            studentCoursesInfoController.unsubscribeStudentFromGroup(listAllStudents.getSelectedValue(), listStudentsGroups.getSelectedValue());
        });
        this.add(btnWypisz);

        JLabel lblNewLabel = new JLabel("Studenci:");
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 16));
        lblNewLabel.setBounds(12, 13, 84, 16);
        this.add(lblNewLabel);

        JLabel lblNewLabel_2 = new JLabel("Grupy:");
        lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 16));
        lblNewLabel_2.setBounds(380, 13, 56, 16);
        this.add(lblNewLabel_2);

        JLabel lblKursy = new JLabel("Kursy:");
        lblKursy.setFont(new Font("Arial", Font.BOLD, 16));
        lblKursy.setBounds(187, 13, 56, 16);
        this.add(lblKursy);

        JLabel lblNewLabel_3 = new JLabel("ECTS:");
        lblNewLabel_3.setFont(new Font("Arial", Font.PLAIN, 16));
        lblNewLabel_3.setBounds(565, 36, 56, 16);
        this.add(lblNewLabel_3);

        lblECTS = new JLabel("-- . --");
        lblECTS.setFont(new Font("Arial", Font.PLAIN, 16));
        lblECTS.setBounds(637, 36, 56, 16);
        this.add(lblECTS);

        JLabel lblNewLabel_5 = new JLabel("Parzysto\u015B\u0107 tygodnia:");
        lblNewLabel_5.setFont(new Font("Arial", Font.PLAIN, 16));
        lblNewLabel_5.setBounds(565, 80, 157, 16);
        this.add(lblNewLabel_5);

        lblparityOfWeek = new JLabel("-- . --");
        lblparityOfWeek.setFont(new Font("Arial", Font.PLAIN, 16));
        lblparityOfWeek.setBounds(709, 80, 56, 16);
        this.add(lblparityOfWeek);

        JLabel lblNewLabel_7 = new JLabel("Dzie\u0144 zaj\u0119\u0107:");
        lblNewLabel_7.setFont(new Font("Arial", Font.PLAIN, 16));
        lblNewLabel_7.setBounds(565, 120, 97, 16);
        this.add(lblNewLabel_7);

        lbldayOfWeek = new JLabel("-- . --");
        lbldayOfWeek.setFont(new Font("Arial", Font.PLAIN, 16));
        lbldayOfWeek.setBounds(656, 120, 56, 16);
        this.add(lbldayOfWeek);

        JLabel lblNewLabel_9 = new JLabel("Godziny zaj\u0119\u0107:");
        lblNewLabel_9.setFont(new Font("Arial", Font.PLAIN, 16));
        lblNewLabel_9.setBounds(565, 149, 108, 16);
        this.add(lblNewLabel_9);

        lbllessonsHours = new JLabel("-- . --");
        lbllessonsHours.setFont(new Font("Arial", Font.PLAIN, 16));
        lbllessonsHours.setBounds(575, 169, 147, 16);
        this.add(lbllessonsHours);

        JLabel lblProwadzcy = new JLabel("Prowadz\u0105cy:");
        lblProwadzcy.setFont(new Font("Arial", Font.PLAIN, 16));
        lblProwadzcy.setBounds(565, 198, 97, 16);
        this.add(lblProwadzcy);

        lblProfDrIn = new JLabel("-- . --");
        lblProfDrIn.setFont(new Font("Arial", Font.PLAIN, 14));
        lblProfDrIn.setBounds(564, 217, 213, 16);
        this.add(lblProfDrIn);
    }

    public void updateInfoGroup(Group selectedValue) {
        lblECTS.setText(Integer.toString(listStudentCourses.getSelectedValue().getEcts()));
        lbllessonsHours.setText(selectedValue.getLessonTime());
        lblProfDrIn.setText(listStudentCourses.getSelectedValue().getNameMaster());
        lbldayOfWeek.setText(selectedValue.getDayOfWeek());
        lblparityOfWeek.setText(selectedValue.getParityOfWeek());
    }

    public void removeInfoGroup(){
        lblECTS.setText("-- . --");
        lbllessonsHours.setText("-- . --");
        lblProfDrIn.setText("-- . --");
        lbldayOfWeek.setText("-- . --");
        lblparityOfWeek.setText("-- . --");
    }

    private void addGroupsToModel(Course selectedValue) {
        for(Group group: selectedValue.getListGroups()){
            modelListGroups.addElement(group);
        }
    }

    public void addStudentsToModel(List<Student> studentList) {
        modelListStudent.clear();
        for(Student student:studentList){
            modelListStudent.addElement(student);
        }
    }

    public void setController(StudentCoursesInfoController studentCoursesInfoController) {
        this.studentCoursesInfoController = studentCoursesInfoController;
    }

    public void addCourseToModel(List<Course> courses) {
        modelListCourses.clear();
        for(Course course: courses){
            modelListCourses.addElement(course);
        }
    }
}
