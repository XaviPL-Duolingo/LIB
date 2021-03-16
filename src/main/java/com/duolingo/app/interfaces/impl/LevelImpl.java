package com.duolingo.app.interfaces.impl;

import com.duolingo.app.interfaces.ILevel;
import com.duolingo.app.model.Category;
import com.duolingo.app.model.Level;
import com.duolingo.app.model.User;
import com.duolingo.app.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Set;

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

    @Override
    public Level getLevelByID(int idLevel) {

        try(Session session = HibernateUtil.getSessionFactory().openSession()){

            Level l1 = (Level) session.get(Level.class, idLevel);

            if (l1 != null) {
                return l1;
            }else {
                System.out.println("Error: Ha dado NULL...");
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Level getUserNextLevel(int idUser, int idCategory) {

        UserImpl userManager = new UserImpl();
        User userObj = userManager.getUserByID(idUser);
        Set<Level> userLevels = userObj.getUserLevels();

        List<Level> categoryLevels = getAllLevelsByID(idCategory);
        if (categoryLevels != null){
            for (Level l : categoryLevels) {
                if (!userLevels.contains(l)){
                    return l;
                }
            }
        }

        return null;
    }
}
