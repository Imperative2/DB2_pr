package application;

import application.models.Course;
import application.models.Group;

import java.util.ArrayList;
import java.util.List;

public class StudentCoursesInfoController {
    private final String GET_ALL_STUDENTS_QUERY = "select distinct s.id_indeks, s.imie, s.nazwisko, s.semestr, s.id_kierunku from student s";
    private final String QUERY_SELECTED_COURSES_WITHOUT_WHERE_STUDENT_PARAM = "SELECT k.*, gz.*, CONCAT(p.tytul, ' ', p.imie,' ', p.nazwisko) as teacher, CONCAT(g.godzina_rozpoczecia , ' - ', g.godzina_zakonczenia) as godziny_zajec \n" +
            "FROM student s INNER JOIN kurs k ON s.semestr = k.semestr\n" +
            "INNER JOIN grupa_zajeciowa gz ON k.id_kursu = gz.id_kursu \n" +
            "INNER JOIN prowadzacy p ON p.id_prowadzacego = gz.id_prowadzacego\n" +
            "INNER JOIN godziny_zajec g ON gz.id_godziny_zajec = g.id_godziny_zajec\n" +
            "WHERE s.id_indeks = ";
    private List<Student> studentList = new ArrayList<>();
    private List<Course> coursesList = new ArrayList<>();
    private DatabaseConnection dbConn;
    private StudentCoursesInfoPanel unsubscribeStudentPanel;
    private User mainUser;

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

    public void getCoursesAdnGroupsBy(Student student){
        coursesList.clear();
        List<String[]> queryResults;
        String QUERY = QUERY_SELECTED_COURSES_WITHOUT_WHERE_STUDENT_PARAM + student.getIndeks() + ";";
        dbConn.connect();
        queryResults = dbConn.querryDatabase(QUERY, 16);
        createCoursesAndGroups(queryResults);

        unsubscribeStudentPanel.addCourseToModel(coursesList);
    }

    private void createCoursesAndGroups(List<String[]> queryResults) {
        for(String[] row : queryResults){
            if(!courseIsOnTheList(Integer.parseInt(row[0]))) {
                Course course = new Course(Integer.parseInt(row[0]), row[1], Integer.parseInt(row[2]), row[3], Integer.parseInt(row[4]), row[14]);
                coursesList.add(course);
            }
            Group group = new Group(Integer.parseInt(row[6]),Integer.parseInt(row[9]), row[10], row[11],Integer.parseInt(row[12]), row[13], row[15], Integer.parseInt(row[0]));
            getCourseByID(Integer.parseInt(row[0])).addGroups(group);
        }
    }

    private Course getCourseByID(int id) {
        for(Course course:coursesList){
            if(course.getId()==id){
                return course;
            }
        }
        return null;
    }

    private boolean courseIsOnTheList(int id) {
        for(Course course:coursesList){
            if(course.getId()==id){
                return true;
            }
        }
        return false;
    }

    public void updateInfoAboutGroups(){

    }

    public void setMainUser(Employee mainUser) {
        this.mainUser = mainUser;
    }


}
