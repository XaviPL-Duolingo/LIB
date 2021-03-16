package com.duolingo.app.interfaces;

import com.duolingo.app.model.Exercice;
import com.duolingo.app.model.WordMatch;
import org.json.JSONObject;

import java.util.List;

public interface IExercice {

    public void insertTypeTestExercice(int idLevel, String[] contentExercice, boolean isHard);

    public void insertTranslateExercice(int idLevel, String[] contentExercice, boolean isHard, boolean isListen);

    public void insertWordFillExercice(int idLevel, String[] contentExercice, boolean isHard);

    public void insertWordMatchExercice(int idLevel, WordMatch[] contentExercice, boolean isHard);

    public List<Exercice> getAllExercicesByID(int idLevel);

    public Exercice getExerciceByID(int idExercice);

    public JSONObject parseExerciceData(Exercice exerciceObj);


}
