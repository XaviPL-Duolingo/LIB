package com.duolingo.app.util;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import com.duolingo.app.interfaces.impl.CategoryImpl;
import com.duolingo.app.interfaces.impl.CourseImpl;
import com.duolingo.app.interfaces.impl.LanguageImpl;
import com.duolingo.app.interfaces.impl.LevelImpl;
import com.duolingo.app.model.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class TestHibernate {

	public static void main(String[] args) {

		LanguageImpl languageImpl = new LanguageImpl();
		CourseImpl courseImpl = new CourseImpl();
		CategoryImpl categoryImpl = new CategoryImpl();
		LevelImpl levelImpl = new LevelImpl();

		List<Language> languageList = languageImpl.getAllLanguages();
		for (Language l: languageList) {
			System.out.println(l.getIdLanguage() + " // " + l.getNameLanguage());
		}

		List<Course> courseList = courseImpl.getAllCoursesByID(27);
		for (Course c: courseList) {
			System.out.println(c.getIdOriginLang().getCodeLanguage() + " // " + c.getIdDestLang().getCodeLanguage());
		}

		levelImpl.insertLevel(1, "ANIM-2 == ES/EN");
		List<Level> levelList = levelImpl.getAllLevelsByID(1);
		System.out.println(levelList.get(0).getIdCategory().getCategoryName() + " == " + levelList.size() + " NIVELES!");
		for (Level l : levelList) {
			System.out.println(l.getCodeLevel() + " // CATEGORY: " + l.getIdCategory().getCategoryName());
		}


	}

}
