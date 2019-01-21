package application.models;

public class Group {

    private int id, hours, amountOfPlaces, id_course;
    private String dayOfWeek, parityOfWeek, classroom, lessonTime;

    public Group(int id, int hours, String dayOfWeek, String parityOfWeek, int amountOfPlaces, String classroom, String lessonTime, Integer id_course){
        this.id = id;
        this.hours = hours;
        this.dayOfWeek = dayOfWeek;
        this.parityOfWeek = parityOfWeek;
        this.amountOfPlaces = amountOfPlaces;
        this.classroom = classroom;
        this.lessonTime = lessonTime;
        this.id_course = id_course;
    }
    
    public Group()
    {
    	
    }

    @Override
    public String toString() {
        return Integer.toString(id);
    }

    public int getId(){
        return id;
    }
    
    public void setId(int id)
    {
    	this.id = id;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getAmountOfPlaces() {
        return amountOfPlaces;
    }

    public void setAmountOfPlaces(int amountOfPlaces) {
        this.amountOfPlaces = amountOfPlaces;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getParityOfWeek() {
        return parityOfWeek;
    }

    public void setParityOfWeek(String parityOfWeek) {
        this.parityOfWeek = parityOfWeek;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public String getLessonTime() {
        return lessonTime;
    }

    public void setLessonTime(String lessonTime) {
        this.lessonTime = lessonTime;
    }

    public int getId_course() {
        return id_course;
    }

    public void setId_course(int id_course) {
        this.id_course = id_course;
    }
}
