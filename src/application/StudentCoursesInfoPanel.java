package application;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class StudentCoursesInfoPanel extends JPanel {

    DefaultListModel<Student> modelListStudent = new DefaultListModel<>();

    public StudentCoursesInfoPanel(){
        JScrollPane scrollPane_2 = new JScrollPane();
        scrollPane_2.setBounds(12, 34, 163, 319);
        this.add(scrollPane_2);

        JList<Student> listAllStudents = new JList<>(modelListStudent);
        scrollPane_2.setViewportView(listAllStudents);

        JScrollPane scrollPane_3 = new JScrollPane();
        scrollPane_3.setBounds(187, 34, 177, 319);
        this.add(scrollPane_3);

        JList listStudentCourses = new JList();
        scrollPane_3.setViewportView(listStudentCourses);

        JScrollPane scrollPane_4 = new JScrollPane();
        scrollPane_4.setBounds(376, 34, 177, 319);
        this.add(scrollPane_4);

        JList listStudentsGroups = new JList();
        scrollPane_4.setViewportView(listStudentsGroups);

        JButton btnWypisz = new JButton("Wypisz");
        btnWypisz.setForeground(Color.RED);
        btnWypisz.setBounds(656, 328, 97, 25);
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

        JLabel lblNewLabel_4 = new JLabel("13");
        lblNewLabel_4.setFont(new Font("Arial", Font.PLAIN, 16));
        lblNewLabel_4.setBounds(637, 36, 56, 16);
        this.add(lblNewLabel_4);

        JLabel lblNewLabel_5 = new JLabel("Parzysto\u015B\u0107 tygodnia:");
        lblNewLabel_5.setFont(new Font("Arial", Font.PLAIN, 16));
        lblNewLabel_5.setBounds(565, 80, 157, 16);
        this.add(lblNewLabel_5);

        JLabel lblNewLabel_6 = new JLabel("TP");
        lblNewLabel_6.setFont(new Font("Arial", Font.PLAIN, 16));
        lblNewLabel_6.setBounds(709, 80, 56, 16);
        this.add(lblNewLabel_6);

        JLabel lblNewLabel_7 = new JLabel("Dzie\u0144 zaj\u0119\u0107:");
        lblNewLabel_7.setFont(new Font("Arial", Font.PLAIN, 16));
        lblNewLabel_7.setBounds(565, 120, 97, 16);
        this.add(lblNewLabel_7);

        JLabel lblNewLabel_8 = new JLabel("wt");
        lblNewLabel_8.setFont(new Font("Arial", Font.PLAIN, 16));
        lblNewLabel_8.setBounds(656, 120, 56, 16);
        this.add(lblNewLabel_8);

        JLabel lblNewLabel_9 = new JLabel("Godziny zaj\u0119\u0107:");
        lblNewLabel_9.setFont(new Font("Arial", Font.PLAIN, 16));
        lblNewLabel_9.setBounds(565, 149, 108, 16);
        this.add(lblNewLabel_9);

        JLabel lblNewLabel_10 = new JLabel("7:30:00 - 9:30:00");
        lblNewLabel_10.setFont(new Font("Arial", Font.PLAIN, 16));
        lblNewLabel_10.setBounds(575, 169, 147, 16);
        this.add(lblNewLabel_10);

        JLabel lblProwadzcy = new JLabel("Prowadz\u0105cy:");
        lblProwadzcy.setFont(new Font("Arial", Font.PLAIN, 16));
        lblProwadzcy.setBounds(565, 198, 97, 16);
        this.add(lblProwadzcy);

        JLabel lblProfDrIn = new JLabel("Prof. dr. inz Janusz biernat");
        lblProfDrIn.setFont(new Font("Arial", Font.PLAIN, 14));
        lblProfDrIn.setBounds(564, 217, 213, 16);
        this.add(lblProfDrIn);
    }

    public void addStudentsToModel(List<Student> studentList) {
        for(Student student:studentList){
            modelListStudent.addElement(student);
        }
    }
}
