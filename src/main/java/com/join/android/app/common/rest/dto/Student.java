package com.join.android.app.common.rest.dto;

/**
 * Created by pengsk on 2014/12/31.
 */
public class Student<E> {
    private String name;
    private E teacher;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public E getTeacher() {
        return teacher;
    }

    public void setTeacher(E teacher) {
        this.teacher = teacher;
    }
}
