package com.duolingo.app.interfaces;

import com.duolingo.app.model.Exercice;

import java.util.List;

public interface IExercice {

    public void createExercice(int idLevel, int idTypeLevel, String[] contentExercice);

    public List<Exercice> getAllExercicesByLevelID(int idLevel);

    public Exercice getExerciceByID(int idExercice);

}
