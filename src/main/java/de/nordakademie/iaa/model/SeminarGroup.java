package de.nordakademie.iaa.model;

import javax.persistence.Basic;
import javax.persistence.Entity;

@Entity
public class SeminarGroup extends Group{

    private int maximumNumberOfStudents;

    public SeminarGroup() {}

    public SeminarGroup(String name, int minChangeoverTime, int maximumNumberOfStudents) {
        super(name, minChangeoverTime);
        this.maximumNumberOfStudents = maximumNumberOfStudents;
    }

    @Basic
    public int getMaximumNumberOfStudents() {
        return maximumNumberOfStudents;
    }

    public void setMaximumNumberOfStudents(int maximumNumberOfStudents) {
        this.maximumNumberOfStudents = maximumNumberOfStudents;
    }

    @Override
    public int calculateNumberOfStudents() {
        return maximumNumberOfStudents;
    }
}