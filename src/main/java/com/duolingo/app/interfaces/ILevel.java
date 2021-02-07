package com.duolingo.app.interfaces;

import com.duolingo.app.model.Level;

import java.util.List;

public interface ILevel {

    public List<Level> getAllLevelsByID(int idCategory);

    public void insertLevel(int idCategory, String codeLevel);

}
