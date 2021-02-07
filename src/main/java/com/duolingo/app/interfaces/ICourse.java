package com.duolingo.app.interfaces;

import com.duolingo.app.model.Course;

import java.util.List;

public interface ICourse {

    public List<Course> getAllCoursesByID(int idOriginLang, int idDestLang);

    public void insertCourse(int idOriginLang, int idDestLang);

    public Course getCourseByID(int idCourse);

}
