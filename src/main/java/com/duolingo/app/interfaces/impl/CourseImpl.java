package com.duolingo.app.interfaces.impl;

import com.duolingo.app.interfaces.ICourse;
import com.duolingo.app.model.Course;
import com.duolingo.app.model.Language;
import com.duolingo.app.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class CourseImpl implements ICourse{

    @Override
    public List<Course> getAllCoursesByID(int idOriginLang, int idDestLang) {

        Transaction t = null;

        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            t = session.beginTransaction();

            List<Course> courseList = null;

            if (idOriginLang == 0){
                courseList = session.createQuery("FROM Course WHERE idDestLang = " + idDestLang).list();
            }else if (idDestLang == 0){
                courseList = session.createQuery("FROM Course WHERE idOriginLang = " + idOriginLang).list();
            }else{
                courseList = session.createQuery("FROM Course WHERE idOriginLang = " + idOriginLang + " AND idDestLang = " + idDestLang).list();
            }

            return courseList;

        }
    }

    @Override
    public void insertCourse(int idOriginLang, int idDestLang) {

        LanguageImpl languageImpl = new LanguageImpl();

        Language originLang = languageImpl.getLanguageByID(idOriginLang);
        Language destLang = languageImpl.getLanguageByID(idDestLang);

        Course courseObj = new Course();
        courseObj.setIdOriginLang(originLang);
        courseObj.setIdDestLang(destLang);

        Transaction t = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            t = session.beginTransaction();
            session.save(courseObj);
            t.commit();
            System.out.println("Insertado correctamente!");

        }catch(Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public Course getCourseByID(int idCourse) {

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Course c1 = (Course) session.get(Course.class, idCourse);

            if (c1 != null) {
                return c1;
            }else {
                System.out.println("Error: Ha dado NULL...");
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }
}
