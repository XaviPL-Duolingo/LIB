package com.duolingo.app.interfaces;

import com.duolingo.app.model.Category;

import java.util.List;

public interface ICategory {

    public List<Category> getAllCategoriesByID(int courseID);

    public void insertCategory(int idCourse, String name);

    public Category getCategoryByID(int idCategory);

}
