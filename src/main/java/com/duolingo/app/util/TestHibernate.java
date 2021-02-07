package com.duolingo.app.util;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import com.duolingo.app.interfaces.impl.LanguageImpl;
import com.duolingo.app.model.Language;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.duolingo.app.model.TypeExercice;

public class TestHibernate {

	public static void main(String[] args) {

		LanguageImpl languageImpl = new LanguageImpl();

		List<Language> languageList = languageImpl.getAllLanguages();
		for (Language l: languageList) {
			System.out.println(l.getIdLanguage() + " // " + l.getNameLanguage());
		}

	}

}
