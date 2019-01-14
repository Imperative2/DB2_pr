package application.models;

public class Group {

    private int id, hours, amountOfPlaces;
    private String dayOfWeek, parityOfWeek, classroom, lessonTime;

    public Group(int id, int hours, String dayOfWeek, String parityOfWeek, int amountOfPlaces, String classroom, String lessonTime){
        this.id = id;
        this.hours = hours;
        this.dayOfWeek = dayOfWeek;
        this.parityOfWeek = parityOfWeek;
        this.amountOfPlaces = amountOfPlaces;
        this.classroom = classroom;
        this.lessonTime = lessonTime;
    }

    @Override
    public String toString() {
        return Integer.toString(id);
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
}
