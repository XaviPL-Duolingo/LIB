package com.duolingo.app.interfaces.impl;

import com.duolingo.app.interfaces.ICategory;
import com.duolingo.app.model.Category;
import com.duolingo.app.model.Course;
import com.duolingo.app.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CategoryImpl implements ICategory{

    @Override
    public List<Category> getAllCategoriesByID(int idCourse) {

        // getAllCategoriesByID()
        // Obtiene todas las CATEGORIES mediante la ID de un COURSE

        Transaction t = null;

        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            t = session.beginTransaction();

            List<Category> categoryList = session.createQuery("FROM Category WHERE idCourse = " + idCourse).list();

            return categoryList;

        }
    }

    @Override
    public void insertCategory(int courseID, String name) {

        // insertCategory()
        // Añade una nueva CATEGORY con el nombre proporcionado al COURSE que tenga la ID proporcionada.

        CourseImpl courseImpl = new CourseImpl();
        Course courseObj = courseImpl.getCourseByID(courseID);

        Category categoryObj = new Category();
        categoryObj.setIdCourse(courseObj);
        categoryObj.setCategoryName(name);

        Transaction t = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            t = session.beginTransaction();
            session.save(categoryObj);
            t.commit();
            System.out.println("Insertado correctamente!");

        }catch(Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public Category getCategoryByID(int idCategory) {

        // getCategoryByID()
        // Obtiene la CATEGORY con la ID proporcionada en forma de objecto.

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Category c1 = (Category) session.get(Category.class, idCategory);

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
