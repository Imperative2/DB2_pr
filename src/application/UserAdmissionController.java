package application;

import application.models.Course;
import application.models.Group;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class UserAdmissionController {

    private final DatabaseConnection dbConn;
    private final String QUERY_WITHOUT_WHERE_PARAM = "SELECT k.*, gz.*, CONCAT(p.tytul, ' ', p.imie,' ', p.nazwisko) as teacher, CONCAT(g.godzina_rozpoczecia , ' - ', g.godzina_zakonczenia) as godziny_zajec \n" +
            "FROM student s INNER JOIN kurs k ON s.semestr = k.semestr\n" +
            "INNER JOIN grupa_zajeciowa gz ON k.id_kursu = gz.id_kursu \n" +
            "INNER JOIN prowadzacy p ON p.id_prowadzacego = gz.id_prowadzacego\n" +
            "INNER JOIN godziny_zajec g ON gz.id_godziny_zajec = g.id_godziny_zajec\n" +
            "WHERE s.id_indeks = ";
    private final int AMOUNT_OF_COLUMN = 16;
    private Student mainUser;
    private List<Course> courseList = new ArrayList<>();
    private TimerAdmissionTime timerAdmissionTime;

    private UserAdmissionPanel userAdmissionPanel;

    public UserAdmissionController(DatabaseConnection dbConnection,UserAdmissionPanel userAdmissionPanel, TimerAdmissionTime timerAdmissionTime)
    {
        this.dbConn = dbConnection;
        this.userAdmissionPanel = userAdmissionPanel;
        this.userAdmissionPanel.setController(this);
        this.timerAdmissionTime = timerAdmissionTime;
    }

    public void setMainUser(Student mainUser) {
        this.mainUser = mainUser;
    }

    public void updateAdmissionPanel() {
        List<String[]> queryResults;
        String QUERY = QUERY_WITHOUT_WHERE_PARAM + mainUser.getUserId() + ";";
        dbConn.connect();
        queryResults = dbConn.querryDatabase(QUERY, AMOUNT_OF_COLUMN);

        createCoursesAndGroups(queryResults);

        for(Course course: courseList){
            userAdmissionPanel.addCourseToModel(course);
        }

    }
    private void createCoursesAndGroups(List<String[]> queryResults) {
        for(String[] row : queryResults){
            if(!courseIsOnTheList(Integer.parseInt(row[0]))) {
                Course course = new Course(Integer.parseInt(row[0]), row[1], Integer.parseInt(row[2]), row[3], Integer.parseInt(row[4]), row[14]);
                courseList.add(course);
            }
            Group group = new Group(Integer.parseInt(row[6]),Integer.parseInt(row[9]), row[10], row[11],Integer.parseInt(row[12]), row[13], row[15], Integer.parseInt(row[0]));
            getCourseByID(Integer.parseInt(row[0])).addGroups(group);
        }
    }

    private Course getCourseByID(int id) {
        for(Course course:courseList){
            if(course.getId()==id){
                return course;
            }
        }
        return null;
    }

    private boolean courseIsOnTheList(int id) {
        for(Course course:courseList){
            if(course.getId()==id){
                return true;
            }
        }
        return false;
    }


    public void signUpStudentToGroup() {
        if(checkRightToSignUp((Student)mainUser) && studentIsNotInGroup((Student)mainUser, getChosenGroup())){
            dbConn.deleteOrUpdateData("INSERT INTO zapis (id_indeksu, id_grupy) VALUES (" + mainUser.getUserId() + "," +  getChosenGroup().getId() + ")");
            JOptionPane.showMessageDialog(null, "Process succeed!", "Info message!", JOptionPane.INFORMATION_MESSAGE);

        }
        else{
            JOptionPane.showMessageDialog(null, "You are belong to this group or you don't have admission right.", "Something gone wrong!", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean checkRightToSignUp(Student mainUser) {
        if(mainUser.getAdmissionRight().equals("posiada") || timerAdmissionTime.isAfter()){
            return true;
        }else{
            return false;
        }
    }

    private boolean studentIsNotInGroup(Student mainUser, Group group) {
        List<String[]> queryResultsIDGroups, queryResultsIDCourse;
        List<String> id_courses = new ArrayList<>();
        queryResultsIDGroups = dbConn.querryDatabase("Select id_grupy from zapis where id_indeksu = " + mainUser.getUserId() + ";", 1);
        for(String[] id_group:queryResultsIDGroups){
            queryResultsIDCourse = dbConn.querryDatabase("Select id_kursu from grupa_zajeciowa where id_grupy = " + id_group[0], 1);
            for(String[] id_course:queryResultsIDCourse){
                id_courses.add(id_course[0]);
            }
        }
        for(String id_course:id_courses){
            if(Integer.parseInt(id_course) == group.getId_course()){
                return false;
            }
        }
        return true;
    }

    public Group getChosenGroup() {
        return userAdmissionPanel.getChosenGroup();
    }
}
