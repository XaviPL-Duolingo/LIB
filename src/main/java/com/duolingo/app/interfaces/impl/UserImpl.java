package com.duolingo.app.interfaces.impl;

import com.duolingo.app.interfaces.IUser;
import com.duolingo.app.model.Rank;
import com.duolingo.app.model.User;
import com.duolingo.app.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserImpl implements IUser{

    @Override
    public User getUserData(String KEYID_USERNAME) {

        Transaction t = null;

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            t = session.beginTransaction();
            List<User> userList = session.createQuery("FROM User WHERE username = '" + KEYID_USERNAME +"'").list();
            return userList.get(0);

        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean loginUser(String userName, String password) {

        Transaction t = null;

        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            t = session.beginTransaction();
            Long query = ((Long) session.createQuery("SELECT COUNT(*) FROM User WHERE username = '" + userName + "' AND password = '" + password + "'").uniqueResult());
            int result = query.intValue();
            if (result == 1){
                return true;
            }

        }catch (Exception e){
            e.printStackTrace();
        }

            return false;
    }

    @Override
    public boolean registerUser(String userName, String email, String pass, int idOriginLang) {

        LanguageImpl languageManager = new LanguageImpl();
        RankImpl rankManager = new RankImpl();

        // Datos proporcionados por el usuario

        User newUser = new User();
        newUser.setUsername(userName);
        newUser.setEmail(email);
        newUser.setPassword(pass);

        // Datos por defecto al crear usuario

        newUser.setAvatar("https://icon-library.com/images/default-profile-icon/default-profile-icon-24.jpg");
        newUser.setIdRank(rankManager.getRankByID(1));
        newUser.setElo(0);
        newUser.setXp(0);
        newUser.setMoney(0);

        Transaction t = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            t = session.beginTransaction();
            session.save(newUser);
            t.commit();
            System.out.println("Insertado correctamente!");
            return true;

        }catch(Exception e){
            e.printStackTrace();
        }

        return false;

    }

    @Override
    public boolean changeProfilePic(String KEY_USER, Object image) {
        return false;
    }

    @Override
    public List<User> getRanking(int idRank) {

        Transaction t = null;

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            t = session.beginTransaction();
            List<User> userList = session.createQuery("FROM User WHERE idRank = " + idRank + " ORDER BY elo DESC ").list();
            return userList;

        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean deleteUser(String KEYID_USERNAME) {
        return false;
    }


}
