package com.duolingo.app.interfaces.impl;

import com.duolingo.app.interfaces.IRank;
import com.duolingo.app.model.Language;
import com.duolingo.app.model.Rank;
import com.duolingo.app.util.HibernateUtil;
import org.hibernate.Session;

public class RankImpl implements IRank{

    @Override
    public Rank getRankByID(int idRank) {

        try(Session session = HibernateUtil.getSessionFactory().openSession()){

            Rank r1 = (Rank) session.get(Rank.class, idRank);

            if (r1 != null) {
                return r1;
            }else {
                System.out.println("Error: Ha dado NULL...");
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }
}
