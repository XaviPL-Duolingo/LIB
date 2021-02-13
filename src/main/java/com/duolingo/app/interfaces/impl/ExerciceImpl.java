package com.duolingo.app.interfaces.impl;

import com.duolingo.app.interfaces.IExercice;
import com.duolingo.app.model.Exercice;

import java.util.List;

public class ExerciceImpl implements IExercice{

    @Override
    public void createExercice(int idLevel, int idTypeLevel, String[] contentExercice) {

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
