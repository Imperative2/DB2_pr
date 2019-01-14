package application;

import application.models.Course;
import application.models.Group;

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

    private UserAdmissionPanel userAdmissionPanel;

    public UserAdmissionController(DatabaseConnection dbConnection,UserAdmissionPanel userAdmissionPanel)
    {
        this.dbConn = dbConnection;
        this.userAdmissionPanel = userAdmissionPanel;
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
            Group group = new Group(Integer.parseInt(row[6]),Integer.parseInt(row[9]), row[10], row[11],Integer.parseInt(row[12]), row[13], row[15]);
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

    public Group getChosenGroup() {
        return userAdmissionPanel.getChosenGroup();
    }
}
