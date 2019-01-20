package application.panels;

import Controllers.StudentCoursesAndGroupsInfoPanel;

import javax.swing.*;
import java.awt.*;

public class UnsubStudentFromGroupPanel extends StudentCoursesAndGroupsInfoPanel {

    public UnsubStudentFromGroupPanel(){
        super();

        listAllStudents.addListSelectionListener(e -> {
                    removeInfoGroup();
                    unsubStudentFromGroupController.getCoursesAdnGroupsBy(listAllStudents.getSelectedValue());
                }
        );

        JButton btnWypisz = new JButton("Wypisz");
        btnWypisz.setForeground(Color.RED);
        btnWypisz.setBounds(656, 328, 97, 25);
        btnWypisz.addActionListener(e -> {
            unsubStudentFromGroupController.unsubscribeStudentFromGroup(listAllStudents.getSelectedValue(), listStudentsGroups.getSelectedValue());
        });
        this.add(btnWypisz);

    }

}
