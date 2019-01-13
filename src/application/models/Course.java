package application.models;

import java.util.ArrayList;
import java.util.List;

public class Course {

    private int ects,id, semester;
    private String formOfClasses;
    private String name, nameMaster;
    private List<Group> listGroups = new ArrayList<Group>();

    public Course(int id, String name, int semester, String formOfClasses, int ects, String nameMaster){
        this.id = id;
        this.name = name;
        this.semester = semester;
        this.formOfClasses = formOfClasses;
        this.ects = ects;
    }

    public void addGroups(Group group) {
        listGroups.add(group);
    }

    public List<Group> getListGroups(){
        return listGroups;
    }

    @Override
    public String toString() {
        return name + " " + formOfClasses;
    }
}
