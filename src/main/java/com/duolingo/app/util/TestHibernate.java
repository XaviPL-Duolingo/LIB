package com.duolingo.app.util;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.duolingo.app.model.TypeExercice;

public class TestHibernate {

	public static void main(String[] args) {
		
		Transaction t = null;
		
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
            t = session.beginTransaction();
            
            CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery <TypeExercice> criteria = builder.createQuery(TypeExercice.class);
			criteria.from(TypeExercice.class);
			
			List <TypeExercice> listTypeExercice = session.createQuery(criteria).getResultList();
			
			for (TypeExercice typeExercice : listTypeExercice) {
				System.out.println(typeExercice.getNameTypeExercice());
			}

        }

	}

}
