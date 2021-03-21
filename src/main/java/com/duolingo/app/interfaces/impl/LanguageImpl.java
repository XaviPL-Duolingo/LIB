package com.duolingo.app.interfaces.impl;

import com.duolingo.app.interfaces.ILanguage;
import com.duolingo.app.model.Language;
import com.duolingo.app.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class LanguageImpl implements ILanguage{

    @Override
    public List<Language> getAllLanguages() {

        // getAllLanguages()
        // Obtiene todos los LANGUAGES de la DB.

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

    @Override
    public Language getLanguageByID(int idLanguage) {

        // getLanguageByID()
        // Obtiene el LANGUAGE que tenga de ID el valor proporcionado y lo pasa en forma de objeto.

        try(Session session = HibernateUtil.getSessionFactory().openSession()){

            Language l1 = (Language) session.get(Language.class, idLanguage);

            if (l1 != null) {
                return l1;
            }else {
                System.out.println("Error: Ha dado NULL...");
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }


}
