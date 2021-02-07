package com.duolingo.app.interfaces.impl;

import com.duolingo.app.interfaces.ILanguage;
import com.duolingo.app.model.Language;
import com.duolingo.app.model.TypeExercice;
import com.duolingo.app.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class LanguageImpl implements ILanguage{

    @Override
    public List<Language> getAllLanguages() {

        Transaction t = null;

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            t = session.beginTransaction();

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Language> criteria = builder.createQuery(Language.class);
            criteria.from(Language.class);

            List <Language> listLanguage = session.createQuery(criteria).getResultList();

            return listLanguage;

        }
    }


}
