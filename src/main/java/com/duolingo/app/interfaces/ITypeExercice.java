package com.duolingo.app.interfaces;

import com.duolingo.app.model.TypeExercice;

import java.util.List;

public interface ITypeExercice {

    public List<TypeExercice> getAllTypesExercice();

    public TypeExercice getTypeExerciceByID(int idTypeExercice);

}
