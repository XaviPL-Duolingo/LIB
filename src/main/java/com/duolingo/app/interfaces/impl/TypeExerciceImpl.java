package com.duolingo.app.interfaces.impl;

import com.duolingo.app.interfaces.ITypeExercice;
import com.duolingo.app.model.Language;
import com.duolingo.app.model.Level;
import com.duolingo.app.model.TypeExercice;
import com.duolingo.app.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class TypeExerciceImpl implements ITypeExercice{

    @Override
    public List<TypeExercice> getAllTypesExercice() {

        Transaction t = null;

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            t = session.beginTransaction();

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<TypeExercice> criteria = builder.createQuery(TypeExercice.class);
            criteria.from(TypeExercice.class);

            List <TypeExercice> listLanguage = session.createQuery(criteria).getResultList();

            return listLanguage;

        }
    }

    @Override
    public TypeExercice getTypeExerciceByID(int idTypeExercice) {

        try(Session session = HibernateUtil.getSessionFactory().openSession()){

            TypeExercice te1 = (TypeExercice) session.get(TypeExercice.class, idTypeExercice);

            if (te1 != null) {
                return te1;
            }else {
                System.out.println("Error: Ha dado NULL...");
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }
}
