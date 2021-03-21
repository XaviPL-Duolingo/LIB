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

        // getAllLevelsByID()
        // Obtiene todos los LEVELS de la CATEGORY con la ID proporcionada.

        Transaction t = null;

        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            t = session.beginTransaction();

            List<Level> levelList = session.createQuery("FROM Level WHERE idCategory = " + idCategory).list();

            return levelList;

        }
    }

    @Override
    public void insertLevel(int idCategory, String codeLevel) {

        // insertLevel()
        // Añade un nuevo LEVEL a la DB con el codigo proporcionado a la CATEGORY con la ID proporcionada

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

        // getLevelByID()
        // Devuevle solo 1 LEVEL que tenga la ID proporcionada en forma de objeto.

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

        // getUserNextLevel()
        // Según la ID del USER y la ID de la CATEGORY comprueba todos los LEVELS que el usuario
        // haya completado y todos los LEVELS disponibles para esa CATEGORY, devuelve el primer LEVEL que no
        // haya completado el usuario.

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
