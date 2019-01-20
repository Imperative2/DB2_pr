package application;

import application.models.Course;
import application.models.Group;

import java.util.ArrayList;
import java.util.List;

public class StudentCoursesInfoController {
    private final String GET_ALL_STUDENTS_QUERY = "select s.id_indeks, s.imie, s.nazwisko, s.semestr, s.id_kierunku from student s";
    private List<Student> studentList = new ArrayList<>();
    private DatabaseConnection dbConn;
    private StudentCoursesInfoPanel unsubscribeStudentPanel;

    public StudentCoursesInfoController(DatabaseConnection dbConnection,StudentCoursesInfoPanel unsubscribeStudentPanel, TimerAdmissionTime timerAdmissionTime){
        this.dbConn = dbConnection;
        this.unsubscribeStudentPanel = unsubscribeStudentPanel;
    }

    public void updateInfoAboutStudents(){
        List<String[]> queryResults;
        String QUERY = GET_ALL_STUDENTS_QUERY;
        dbConn.connect();
        queryResults = dbConn.querryDatabase(QUERY, 5);

        createStudents(queryResults);

        for(Student student: studentList){
            unsubscribeStudentPanel.addStudentsToModel(studentList);
        }
    }

    private void createStudents(List<String[]> queryResults) {
        for(String[] row : queryResults){
            Student student = new Student(Integer.parseInt(row[0]), row[1], row[2], row[3]);
            studentList.add(student);
        }
    }

    public void updateInfoAboutGroups(){

    }

}
