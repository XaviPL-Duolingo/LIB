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

        // insertTypeTextExercice()
        // Inserta un nuevo EXERCICE de tipo TIPUS_TEST al LEVEL que tenga la ID proporcionada,
        // al ejercicio se le asignan los datos pasados en "contentExercice" i "isHard"

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

        // insertTypeTextExercice()
        // Inserta un nuevo EXERCICE de tipo TRANSLATE_OBERT O REORDENA_PARAULES al LEVEL que tenga la ID proporcionada,
        // al ejercicio se le asignan los datos pasados en "contentExercice" i "isHard"
        // El tipo de ejercicio se determina segun los datos pasados en "contentExercice" y el valor de "isListen"

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

        // insertWordFillExercice()
        // Inserta un nuevo EXERCICE de tipo WORD_FILL al LEVEL que tenga la ID proporcionada,
        // al ejercicio se le asignan los datos pasados en "contentExercice" i "isHard"

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

        // insertWordMatchExercice()
        // Inserta un nuevo EXERCICE de tipo WORD_MATCH al LEVEL que tenga la ID proporcionada,
        // al ejercicio se le asignan los datos pasados en "contentExercice" i "isHard"

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

        // getAllExercicesByID()
        // Se obtienen todos los EXERCICES del LEVEL que tenga la ID pasada.

        Transaction t = null;

        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            t = session.beginTransaction();

            List<Exercice> exerciceList = session.createQuery("FROM Exercice WHERE idLevel = " + idLevel).list();

            return exerciceList;

        }
    }

    @Override
    public Exercice getExerciceByID(int idExercice) {

        // getExerciceByID()
        // Obtiene solo 1 EXERCICE con la ID proporcionada y lo pasa en forma de objecto.

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

        // checkTypeExercice()
        // Segun el valor de length (es el tamaña del String[] contentData) se determina si es de tipo
        // TRADUCCIO_OBERT o REORDENA_PARAULES, si un ejercico de TRADUCCIO_OBERT tiene 1 sola respuesta se
        // transformara a REORDENA_PARAULES, segun el boolean "isListen" cambiara a su subtipo de ejercicio.

        int idTypeExercice = 0;
        if (length > 2){
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
