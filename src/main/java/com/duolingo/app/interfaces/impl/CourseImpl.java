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
    public List<Course> getAllCoursesByID(int idOriginLang) {

        Transaction t = null;

        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            t = session.beginTransaction();

            List<Course> courseList = session.createQuery("FROM Course WHERE idOriginLang = " + idOriginLang).list();

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
}
