package com.duolingo.app.interfaces;

import com.duolingo.app.model.Exercice;

import java.util.List;

public interface IExercice {

    public void insertTypeTestExercice(int idLevel, String[] contentExercice, boolean isHard);

    public void insertTranslateExercice(int idLevel, String[] contentExercice, boolean isHard, boolean isListen);

    public void insertWordFillExercice(int idLevel, String[] contentExercice, boolean isHard);

    public void insertWordMatchExercice(int idLevel, String[] contentExercice, boolean isHard);

    public List<Exercice> getAllExercicesByID(int idLevel);

    public Exercice getExerciceByID(int idExercice);


}
