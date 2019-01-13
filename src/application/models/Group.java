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
}
