package com.duolingo.app.interfaces.impl;

import com.duolingo.app.interfaces.IExercice;
import com.duolingo.app.model.Exercice;
import com.duolingo.app.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ExerciceImpl implements IExercice{

    @Override
    public void createExercice(int idLevel, int idTypeLevel, String[] contentExercice) {

        Transaction t = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            t = session.beginTransaction();
            // session.save(ex);
            t.commit();
            System.out.println("Insertado correctamente!");

        }catch(Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public List<Exercice> getAllExercicesByLevelID(int idLevel) {
        return null;
    }

    @Override
    public Exercice getExerciceByID(int idExercice) {
        return null;
    }
}
