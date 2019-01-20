package application.panels;

import Controllers.StudentCoursesAndGroupsInfoPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SubStudentToGroupPanel extends StudentCoursesAndGroupsInfoPanel {

    public SubStudentToGroupPanel(){
        super();

        listAllStudents.addListSelectionListener(e -> {
                    removeInfoGroup();
                    subStudentToGroupController.getCoursesAdnGroupsBy(listAllStudents.getSelectedValue());
                }
        );

        JButton btnZapisz = new JButton("Zapisz");
        btnZapisz.setForeground(new Color(0, 153, 51));
        btnZapisz.setBounds(656, 328, 97, 25);
        btnZapisz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                subStudentToGroupController.subscribeStudentToGroup(listAllStudents.getSelectedValue(), listStudentsGroups.getSelectedValue());
            }
        });
        this.add(btnZapisz);
    }
}
