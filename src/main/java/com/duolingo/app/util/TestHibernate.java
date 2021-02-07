package com.duolingo.app.util;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import com.duolingo.app.interfaces.impl.CourseImpl;
import com.duolingo.app.interfaces.impl.LanguageImpl;
import com.duolingo.app.model.Course;
import com.duolingo.app.model.Language;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.duolingo.app.model.TypeExercice;

public class TestHibernate {

	public static void main(String[] args) {

		LanguageImpl languageImpl = new LanguageImpl();
		CourseImpl courseImpl = new CourseImpl();

		List<Language> languageList = languageImpl.getAllLanguages();
		for (Language l: languageList) {
			System.out.println(l.getIdLanguage() + " // " + l.getNameLanguage());
		}

		List<Course> courseList = courseImpl.getAllCoursesByID(27);
		for (Course c: courseList) {
			System.out.println(c.getIdOriginLang().getCodeLanguage() + " // " + c.getIdDestLang().getCodeLanguage());
		}

		courseImpl.insertCourse(27, 10);

	}

}
