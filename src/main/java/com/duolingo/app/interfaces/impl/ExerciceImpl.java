package com.duolingo.app.interfaces.impl;

import com.duolingo.app.interfaces.IExercice;
import com.duolingo.app.model.Exercice;
import com.duolingo.app.model.WordMatch;
import com.duolingo.app.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.JSONObject;

import java.util.List;

public class ExerciceImpl implements IExercice{

    LevelImpl levelManager = new LevelImpl();
    TypeExerciceImpl typeExerciceManager = new TypeExerciceImpl();

    @Override
    public void insertTypeTestExercice(int idLevel, String[] contentExercice, boolean isHard) {

        JSONObject fileJSON = new JSONObject();
        fileJSON.put("phrToTranslate", contentExercice[0]);
        fileJSON.put("correctAnswer", contentExercice[1]);
        fileJSON.put("wrongAnswer1", contentExercice[2]);
        fileJSON.put("wrongAnswer2", contentExercice[3]);

        Exercice exerciceObj = new Exercice();
        exerciceObj.setIdLevel(levelManager.getLevelByID(idLevel));
        exerciceObj.setIdTypeExercice(typeExerciceManager.getTypeExerciceByID(7));
        exerciceObj.setContentExercice(fileJSON.toString(4));
        exerciceObj.setHard(isHard);

        Transaction t = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            t = session.beginTransaction();
            session.save(exerciceObj);
            t.commit();
            System.out.println("Insertado correctamente!");

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void insertTranslateExercice(int idLevel, String[] contentExercice, boolean isHard, boolean isListen) {

        int idTypeExercice = checkTypeExercice(contentExercice.length, isListen);
        JSONObject fileJSON = new JSONObject();

        fileJSON.put("phrToTranslate", contentExercice[0]);
        for (int i = 1; i < contentExercice.length; i++){
            fileJSON.put("answer"+i, contentExercice[i]);
        }

        Exercice exerciceObj = new Exercice();
        exerciceObj.setIdLevel(levelManager.getLevelByID(idLevel));
        exerciceObj.setIdTypeExercice(typeExerciceManager.getTypeExerciceByID(idTypeExercice));
        exerciceObj.setContentExercice(fileJSON.toString(4));
        exerciceObj.setHard(isHard);

        Transaction t = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            t = session.beginTransaction();
            session.save(exerciceObj);
            t.commit();
            System.out.println("Insertado correctamente!");

        }catch(Exception e){
            e.printStackTrace();
        }


    }

    @Override
    public void insertWordFillExercice(int idLevel, String[] contentExercice, boolean isHard) {

        JSONObject fileJSON = new JSONObject();
        fileJSON.put("phrToComplete", contentExercice[0]);
        fileJSON.put("correctAnswer", contentExercice[1]);
        fileJSON.put("wrongAnswer1", contentExercice[2]);
        fileJSON.put("wrongAnswer2", contentExercice[3]);

        Exercice exerciceObj = new Exercice();
        exerciceObj.setIdLevel(levelManager.getLevelByID(idLevel));
        exerciceObj.setIdTypeExercice(typeExerciceManager.getTypeExerciceByID(6));
        exerciceObj.setContentExercice(fileJSON.toString(4));
        exerciceObj.setHard(isHard);

        Transaction t = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            t = session.beginTransaction();
            session.save(exerciceObj);
            t.commit();
            System.out.println("Insertado correctamente!");

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void insertWordMatchExercice(int idLevel, WordMatch[] contentExercice, boolean isHard) {

        int i = 1;
        JSONObject fileJSON = new JSONObject();
        for (WordMatch wm: contentExercice) {
            JSONObject wordMatchJSON = new JSONObject();
            wordMatchJSON.put("word", wm.getWord());
            wordMatchJSON.put("match", wm.getMatch());
            fileJSON.put("wordMatch"+i, wordMatchJSON);
            i++;
        }

        Exercice exerciceObj = new Exercice();
        exerciceObj.setIdLevel(levelManager.getLevelByID(idLevel));
        exerciceObj.setIdTypeExercice(typeExerciceManager.getTypeExerciceByID(5));
        exerciceObj.setContentExercice(fileJSON.toString(4));
        exerciceObj.setHard(isHard);

        Transaction t = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            t = session.beginTransaction();
            session.save(exerciceObj);
            t.commit();
            System.out.println("Insertado correctamente!");

        }catch(Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public List<Exercice> getAllExercicesByID(int idLevel) {

        Transaction t = null;

        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            t = session.beginTransaction();

            List<Exercice> exerciceList = session.createQuery("FROM Exercice WHERE idLevel = " + idLevel).list();

            return exerciceList;

        }
    }

    @Override
    public Exercice getExerciceByID(int idExercice) {

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Exercice e1 = (Exercice) session.get(Exercice.class, idExercice);

            if (e1 != null) {
                return e1;
            }else {
                System.out.println("Error: Ha dado NULL...");
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public JSONObject parseExerciceData(Exercice exerciceObj) {
        return new JSONObject(exerciceObj.getContentExercice());
    }

    private int checkTypeExercice(int length, boolean isListen){
        int idTypeExercice = 0;
        if (length > 1){
            if (isListen){
                idTypeExercice = 4;
            }else{
                idTypeExercice = 1;
            }
        }else {
            if (isListen){
                idTypeExercice = 3;
            }else {
                idTypeExercice = 2;
            }
        }

        return idTypeExercice;

    }

}
