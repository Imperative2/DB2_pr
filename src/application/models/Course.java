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
        this.nameMaster = nameMaster;
    }

    public void addGroups(Group group) {
        listGroups.add(group);
    }

    public List<Group> getListGroups(){
        return listGroups;
    }

    @Override
    public String toString() {
        return name + " " + formOfClasses.toCharArray()[0];
    }

    public String getFormOfClasses(){
        return formOfClasses;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEcts() {
        return ects;
    }

    public void setEcts(int ects) {
        this.ects = ects;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public String getNameMaster() {
        return nameMaster;
    }

    public void setNameMaster(String nameMaster) {
        this.nameMaster = nameMaster;
    }
}
