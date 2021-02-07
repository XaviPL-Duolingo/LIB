package com.duolingo.app.interfaces.impl;

import com.duolingo.app.interfaces.ILevel;
import com.duolingo.app.model.Category;
import com.duolingo.app.model.Course;
import com.duolingo.app.model.Level;
import com.duolingo.app.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class LevelImpl implements ILevel{

    @Override
    public List<Level> getAllLevelsByID(int idCategory) {
        Transaction t = null;

        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            t = session.beginTransaction();

            List<Level> levelList = session.createQuery("FROM Level WHERE idCategory = " + idCategory).list();

            return levelList;

        }
    }

    @Override
    public void insertLevel(int idCategory, String codeLevel) {

        CategoryImpl categoryImpl = new CategoryImpl();
        Category categoryObj = categoryImpl.getCategoryByID(idCategory);

        Level levelObj = new Level();
        levelObj.setIdCategory(categoryObj);
        levelObj.setCodeLevel(codeLevel);

        Transaction t = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            t = session.beginTransaction();
            session.save(levelObj);
            t.commit();
            System.out.println("Insertado correctamente!");

        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
